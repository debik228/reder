package Render;

import javax.swing.*;
import java.awt.*;

import static Render.RenderWindow.drawLine;
import static java.lang.Math.PI;
import static java.lang.Math.round;

public class Window extends Canvas implements Runnable {
    private final Thread t;
    private final JFrame frame;
    private final String title = "3D engine";
    private int frames = 0;
    private ActionLimiter fpsLimiter;

    //    private final Point2D[] points;
    private Point3D a  = new Point3D(200,800, 300);
    private Point3D b  = new Point3D(200,1000,300);
    private Point3D c  = new Point3D(200,1000,500);
    private Point3D d  = new Point3D(200,800, 500);
    private Point3D a1 = new Point3D(400,800, 300);
    private Point3D b1 = new Point3D(400,1000,300);
    private Point3D c1 = new Point3D(400,1000,500);
    private Point3D d1 = new Point3D(400,800, 500);

    public Window(){
//         points = new Point2D[]{new Point2D(1000, 500),
//                new Point2D(1000, 400), new Point2D(1100, 400),
//                new Point2D(1000, 400), new Point2D(1100, 400),
//                new Point2D(1000, 400), new Point2D(1100, 400),
//                new Point2D(1000, 400), new Point2D(1100, 400),};
//        points[3].rotateAround(points[0], PI/2);
//        points[4].rotateAround(points[0], PI/2);
//        points[5].rotateAround(points[0], 1*PI);
//        points[6].rotateAround(points[0], 1*PI);
//        points[7].rotateAround(points[0], 3*PI/2);
//        points[8].rotateAround(points[0], 3*PI/2);


        frame = new JFrame(title);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createBufferStrategy(3);
        fpsLimiter = new ActionLimiter(60);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        long startMillis, finnishMillis, lastTitleUpd = System.currentTimeMillis();
        while(true){
            startMillis = System.currentTimeMillis();
            render();
            update();
            finnishMillis = System.currentTimeMillis();
            long frameMillis = finnishMillis - startMillis;
            fpsLimiter.waitToNextAction(frameMillis);
            frames++;
            if(System.currentTimeMillis() - lastTitleUpd > 1000) {
                frame.setTitle(title + " | FPS:" + frames);
                lastTitleUpd = System.currentTimeMillis();
                frames = 0;
            }
        }
    }private void render(){
        var bs = this.getBufferStrategy();
        var g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());

        g.setColor(Color.WHITE);

        g.drawString(frame.getTitle(), 0, 10);

//        drawLine(points[0], points[1], g);
//        drawLine(points[0], points[3], g);
//        drawLine(points[0], points[5], g);
//        drawLine(points[0], points[7], g);
//
//        drawLine(points[1], points[2], g);
//        drawLine(points[3], points[4], g);
//        drawLine(points[5], points[6], g);
//        drawLine(points[7], points[8], g);

        Point2D a  = new Point2D(this.a );
        Point2D b  = new Point2D(this.b );
        Point2D c  = new Point2D(this.c );
        Point2D d  = new Point2D(this.d );
        Point2D a1 = new Point2D(this.a1);
        Point2D b1 = new Point2D(this.b1);
        Point2D c1 = new Point2D(this.c1);
        Point2D d1 = new Point2D(this.d1);

        //back
        g.setColor(Color.CYAN);
        g.fillPolygon(new int[]{a1.getX(), b1.getX(), c1.getX(), d1.getX()}, new int[]{a1.getY(), b1.getY(), c1.getY(), d1.getY()}, 4);
        //bottom
        g.setColor(Color.YELLOW);
        g.fillPolygon(new int[]{a.getX(), a1.getX(), b1.getX(), b.getX()}, new int[]{a.getY(), a1.getY(), b1.getY(), b.getY()}, 4);
        //left
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(new int[]{d.getX(), d1.getX(), a1.getX(), a.getX()}, new int[]{d.getY(), d1.getY(), a1.getY(), a.getY()}, 4);
        //top
        g.setColor(Color.BLUE);
        g.fillPolygon(new int[]{d.getX(), d1.getX(), c1.getX(), c.getX()}, new int[]{d.getY(), d1.getY(), c1.getY(), c.getY()}, 4);
        //right
        g.setColor(new Color(0,200,0));
        g.fillPolygon(new int[]{c.getX(), c1.getX(), b1.getX(), b.getX()}, new int[]{c.getY(), c1.getY(), b1.getY(), b.getY()}, 4);
        //front
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{a.getX(), b.getX(), c.getX(), d.getX()}, new int[]{a.getY(), b.getY(), c.getY(), d.getY()}, 4);

        g.setColor(Color.WHITE);
        drawLine(a,b,g);
        drawLine(b,c,g);
        drawLine(c,d,g);
        drawLine(d,a,g);
        drawLine(a1,b1,g);
        drawLine(b1,c1,g);
        drawLine(c1,d1,g);
        drawLine(d1,a1,g);
        drawLine(a,a1,g);
        drawLine(b,b1,g);
        drawLine(c,c1,g);
        drawLine(d,d1,g);

        g.dispose();
        bs.show();
    }
    private void setBG(Graphics g, Color bgColour){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
    }

    private void update(){
//        var centre = points[0];
//        for(var point:points)
//            point.rotateAround(centre, PI/8);

        var points = new Point3D[]{a,b,c,d,a1,b1,c1,d1};
        var centre = new Point3D(300,900,400);
        for(var p:points) {
            //p.rotateAroundAxisParallelToX(centre, PI / 60);
            p.rotateAroundAxisParallelToX(new Point3D(200,800, 300), PI / 60);
        }
    }
}

package Render;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Math.*;

public class RenderWindow extends Frame {

    public RenderWindow(){
        this.setSize(400, 400);
        this.setVisible(true);
        this.setTitle("ой вей");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g){
        var points = new Point2D[]{new Point2D(200, 200),
                new Point2D(200, 100), new Point2D(300, 100),
                new Point2D(200, 100), new Point2D(300, 100),
                new Point2D(200, 100), new Point2D(300, 100),
                new Point2D(200, 100), new Point2D(300, 100),};
        points[3].rotateAround(points[0], PI/2);
        points[4].rotateAround(points[0], PI/2);
        points[5].rotateAround(points[0], 1*PI);
        points[6].rotateAround(points[0], 1*PI);
        points[7].rotateAround(points[0], 3*PI/2);
        points[8].rotateAround(points[0], 3*PI/2);

        for(int i = 0; i < 720; i++)
            for(var p : points)
                p.rotateAround(points[0], PI/360);

        drawLine(points[0], points[1], g);
        drawLine(points[0], points[3], g);
        drawLine(points[0], points[5], g);
        drawLine(points[0], points[7], g);

        drawLine(points[1], points[2], g);
        drawLine(points[3], points[4], g);
        drawLine(points[5], points[6], g);
        drawLine(points[7], points[8], g);



    }

    public static void drawLine(Point2D p1, Point2D p2, Graphics g){
        g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
}

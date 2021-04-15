package Render;

import static java.lang.Math.*;

public class Point3D {
    private double x,y,z;

    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }

    public void rotateAroundX(double radians){
//        var rotationMatrix = new double[][]{
//                        {cos(radians), 0, -sin(radians)},
//                        {0, 1, 0},
//                        {sin(radians), 0, cos(radians)}};
//
//        x = x*rotationMatrix[0][0] + y*rotationMatrix[0][1] + z*rotationMatrix[0][2];
//        y = x*rotationMatrix[1][0] + y*rotationMatrix[1][1] + z*rotationMatrix[2][1];
//        z = x*rotationMatrix[2][0] + y*rotationMatrix[2][1] + z*rotationMatrix[2][2];

        var p = new Point2D(y, (1080d - z));
        var O = new Point2D(0,1080d);
        p.rotateAround(O, radians);
        this.z = 1080 - p.getAccurateY();
        this.y = p.getAccurateX();

    }

    public void rotateAroundAxisParallelToX(Point3D goThrough, double radians){
        var p = new Point2D(y, (1080d - z));
        var O = new Point2D(goThrough.getY(),(1080d - goThrough.getZ()));
        p.rotateAround(O, radians);
        this.z = 1080 - p.getAccurateY();
        this.y = p.getAccurateX();

    }
}

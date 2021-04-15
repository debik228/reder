package Render;

import static java.lang.Math.*;

public class Point2D {
    private double x, y;

    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D point){
        this.x = point.x;
        this.y = point.y;
    }

    public Point2D(Point3D point){
        double izometricAngle = PI/4;
        double scaleFactor = 0.75;

        this.x = point.getY() + point.getX()*sin(izometricAngle)*scaleFactor;
        this.y = 1080 - point.getZ()-point.getX()*cos(izometricAngle)*scaleFactor;
    }

    public int getX() {
        return (int)round(x);
    }
    public int getY() {
        return (int)round(y);
    }

    public double getAccurateX() {
        return x;
    }
    public double getAccurateY() {
        return y;
    }

    public void rotateAround(Point2D centreOfRotation, double radians){
        double vectorX =  x - centreOfRotation.x,
               vectorY =  y - centreOfRotation.y;

        double cos = cos(radians),
               sin = sin(radians);

        x = centreOfRotation.x - (-vectorX*cos + vectorY*sin);
        y = centreOfRotation.y - (-vectorX*sin - vectorY*cos);
    }

    public double distanceTo(Point2D p){
        return sqrt(pow((x - p.x), 2) + pow((y - p.y), 2));
    }
}

package Test;

import Render.Point2D;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.*;

class Point2DTest {
    @org.junit.jupiter.api.Test
    void rotateAround() {
        var centreOfRotation = new Point2D(200,200);
        var p = new Point2D(300, 100);

        p.rotateAround(centreOfRotation, PI/4);

        assertEquals(341, p.getX());
        assertEquals(200, p.getY());
    }

    @org.junit.jupiter.api.Test
    void rotateAroundManyTimes() {
        var centreOfRotation = new Point2D(200,200);
        var p = new Point2D(300, 100);

        for(int round = 0; round < 2; round++)
            for(int i = 0; i < 720; i++)
                p.rotateAround(centreOfRotation, PI/360);


        assertTrue(abs(300 - p.getX()) < 1);
        assertTrue(abs(100 - p.getY()) < 1);
    }

    @org.junit.jupiter.api.Test
    void distanceTo() {
        Point2D A = new Point2D(100, 200),
                B = new Point2D(400, 300);

        double distance = A.distanceTo(B);

        assertTrue(abs(316.227766017 - distance) < 0.00001);
    }
}
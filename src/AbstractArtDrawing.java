import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {
    public void drawRandomLines() {
        Line[] lines = new Line[10];
        GUI gui = new GUI("Random lines and intersections", 500, 500);
        DrawSurface surface = gui.getDrawSurface();
        for (int i = 0; i < 10; i++) {
            Line randomLine = new Line(generateRandomLine().start(), generateRandomLine().end());
            drawLine(randomLine, surface);
            lines[i] = randomLine;
        }

        for (int j = 0; j < 10; j++) {
            for (int k = j + 1; k < 10; k++) {
                if (lines[j].isIntersecting(lines[k])) {
                    Point inter = new Point(lines[j].intersectionWith(lines[k]).getX(),
                            lines[j].intersectionWith(lines[k]).getY());
                    paintIntersectionPoint(inter, surface);
                }
            }
        }
        for (int m = 0; m < 10; m++) {
            paintMiddle(lines[m].middle(), surface);
        }
        gui.show(surface);
    }


    public Line generateRandomLine() {
        Random random = new Random();
        int x1 = random.nextInt(500) + 1;
        int y1 = random.nextInt(500) + 1;
        int x2 = random.nextInt(500) + 1;
        int y2 = random.nextInt(500) + 1;
        return new Line(x1, y1, x2, y2);
    }

    void drawLine(Line line, DrawSurface d) {
        d.setColor(Color.black);
        d.drawLine((int) (line.start().getX()), (int) (line.start().getY()),
                (int) (line.end().getX()), (int) (line.end().getY()));
    }

    void paintIntersectionPoint(Point p, DrawSurface d) {
        d.setColor(Color.red);
        d.fillCircle((int) p.getX(), (int) p.getY(), 3);
    }

    void paintMiddle(Point p, DrawSurface d) {
        d.setColor(Color.blue);
        d.fillCircle((int) p.getX(), (int) p.getY(), 3);
    }

}



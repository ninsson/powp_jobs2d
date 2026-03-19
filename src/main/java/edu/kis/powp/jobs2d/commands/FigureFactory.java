package edu.kis.powp.jobs2d.commands;

/**
 * Proste "fabryki" zwracające złożone polecenia rysujące figury.
 *
 * - rectangle(x,y,width,height) - rysuje prostokąt o zadanych współrzędnych lewego górnego narożnika i rozmiarach
 * - circle(centerX,centerY,radius,segments) - przybliża okrąg wielokątem o podanej liczbie odcinków
 */
public final class FigureFactory {

    private FigureFactory() { }

    public static DriverCommand rectangle(int x, int y, int width, int height) {
        ComplexCommand c = new ComplexCommand();
        c.add(new SetPositionCommand(x, y));
        c.add(new OperateToCommand(x + width, y));
        c.add(new OperateToCommand(x + width, y + height));
        c.add(new OperateToCommand(x, y + height));
        c.add(new OperateToCommand(x, y));
        return c;
    }

    public static DriverCommand circle(int centerX, int centerY, int radius, int segments) {
        if (segments < 3) segments = 16;
        ComplexCommand c = new ComplexCommand();

        double angleStep = 2 * Math.PI / segments;
        int startX = centerX + (int) Math.round(radius * Math.cos(0));
        int startY = centerY + (int) Math.round(radius * Math.sin(0));
        c.add(new SetPositionCommand(startX, startY));

        for (int i = 1; i <= segments; i++) {
            double angle = i * angleStep;
            int px = centerX + (int) Math.round(radius * Math.cos(angle));
            int py = centerY + (int) Math.round(radius * Math.sin(angle));
            c.add(new OperateToCommand(px, py));
        }
        return c;
    }
}

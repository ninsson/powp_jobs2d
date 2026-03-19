package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.Objects;

/**
 * Polecenie rysujące linię do podanych współrzędnych.
 */
public class OperateToCommand implements DriverCommand {
    private final int x;
    private final int y;

    public OperateToCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(Job2dDriver driver) {
        Objects.requireNonNull(driver, "driver must not be null");
        driver.operateTo(x, y);
    }

    @Override
    public String toString() {
        return "OperateToCommand{" + "x=" + x + ", y=" + y + '}';
    }
}

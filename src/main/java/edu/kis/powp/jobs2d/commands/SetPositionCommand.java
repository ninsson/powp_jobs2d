package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.Objects;

/**
 * Polecenie ustawiające pozycję urządzenia.
 */
public class SetPositionCommand implements DriverCommand {
    private final int x;
    private final int y;

    public SetPositionCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(Job2dDriver driver) {
        Objects.requireNonNull(driver, "driver must not be null");
        driver.setPosition(x, y);
    }

    @Override
    public String toString() {
        return "SetPositionCommand{" + "x=" + x + ", y=" + y + '}';
    }
}

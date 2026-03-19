package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * Reprezentuje polecenie używające Job2dDriver.
 */
public interface DriverCommand {
    void execute(Job2dDriver driver);
}

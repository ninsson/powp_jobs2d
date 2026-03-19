package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Kompozyt poleceń: zestaw DriverCommand wykonywanych kolejno na podanym Job2dDriver.
 */
public class MacroCommand implements DriverCommand {

    private final List<DriverCommand> commands = new ArrayList<>();

    public MacroCommand() {
    }

    public MacroCommand(List<DriverCommand> initial) {
        if (initial != null) {
            commands.addAll(initial);
        }
    }

    public MacroCommand add(DriverCommand cmd) {
        Objects.requireNonNull(cmd, "command must not be null");
        commands.add(cmd);
        return this;
    }

    @SafeVarargs
    public static MacroCommand of(DriverCommand... cmds) {
        MacroCommand m = new MacroCommand();
        if (cmds != null) {
            Arrays.stream(cmds).forEach(m::add);
        }
        return m;
    }

    @Override
    public void execute(Job2dDriver driver) {
        Objects.requireNonNull(driver, "driver must not be null");
        for (DriverCommand cmd : commands) {
            cmd.execute(driver);
        }
    }

    public List<DriverCommand> getCommands() {
        return Collections.unmodifiableList(commands);
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }
}

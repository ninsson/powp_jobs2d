package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Kompozyt poleceń: reprezentuje dowolny ciąg DriverCommand wykonywanych kolejno.
 */
public class ComplexCommand implements DriverCommand {

    private final List<DriverCommand> commands = new ArrayList<>();

    public ComplexCommand() {
    }

    public ComplexCommand(Collection<DriverCommand> initial) {
        if (initial != null) {
            commands.addAll(initial);
        }
    }

    public ComplexCommand add(DriverCommand cmd) {
        Objects.requireNonNull(cmd, "command must not be null");
        commands.add(cmd);
        return this;
    }

    @SafeVarargs
    public static ComplexCommand of(DriverCommand... cmds) {
        ComplexCommand c = new ComplexCommand();
        if (cmds != null) {
            Arrays.stream(cmds).forEach(c::add);
        }
        return c;
    }

    /**
     * Zwraca nowy ComplexCommand będący sekwencją bieżących poleceń, po których
     * następuje podane polecenie \`next\`.
     */
    public ComplexCommand andThen(DriverCommand next) {
        Objects.requireNonNull(next, "next command must not be null");
        ComplexCommand combined = new ComplexCommand(this.commands);
        combined.add(next);
        return combined;
    }

    @Override
    public void execute(Job2dDriver driver) {
        Objects.requireNonNull(driver, "driver must not be null");
        for (DriverCommand cmd : commands) {
            cmd.execute(driver);
        }
    }
}
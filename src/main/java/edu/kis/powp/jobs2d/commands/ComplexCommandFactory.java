package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Fabryka tworząca ComplexCommand z podanego skryptu rysującego,
 * czyli funkcji przyjmującej Job2dDriver (np. FiguresJoe::figureScript1).
 */
public final class ComplexCommandFactory {

    private ComplexCommandFactory() { }

    /**
     * Uruchamia podany skrypt na nagrywaczu i zwraca zarejestrowaną sekwencję poleceń.
     *
     */
    public static ComplexCommand fromScript(Consumer<Job2dDriver> script) {
        Objects.requireNonNull(script, "script must not be null");
        RecordingCommandDriver recorder = new RecordingCommandDriver();
        script.accept(recorder);
        return recorder.getCommandSequence();
    }
}

package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.Objects;

/**
 * Nagrywacz wywołań Job2dDriver tworzący ComplexCommand.
 * Działa jako adapter/recorder: udostępnia ten sam interfejs co driver,
 * ale zamiast rysować, buduje sekwencję poleceń.
 */
public class RecordingCommandDriver implements Job2dDriver {

    private final ComplexCommand complex = new ComplexCommand();
    private int x = 0;
    private int y = 0;

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        complex.add(new SetPositionCommand(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        complex.add(new OperateToCommand(x, y));
        this.x = x;
        this.y = y;
    }

    /**
     * Zwraca nagraną sekwencję poleceń. Zwracany obiekt to ta sama instancja
     * ComplexCommand zapełniona przez nagrywacz.
     */
    public ComplexCommand getCommandSequence() {
        return complex;
    }
}

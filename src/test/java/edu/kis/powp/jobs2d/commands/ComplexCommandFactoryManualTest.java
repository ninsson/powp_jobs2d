package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;
import java.util.ArrayList;
import java.util.List;

public class ComplexCommandFactoryManualTest {
    public static void main(String[] args) {
        ComplexCommand cmd = ComplexCommandFactory.fromScript(FiguresJoe::figureScript1);

        RecordingDriver dr = new RecordingDriver();
        cmd.execute(dr);

        System.out.println("Recorded log:");
        dr.getLog().forEach(System.out::println);
    }

    private static class RecordingDriver implements Job2dDriver {
        private final List<String> log = new ArrayList<>();
        private int x = 0, y = 0;

        @Override
        public void setPosition(int x, int y) {
            this.x = x; this.y = y;
            log.add("setPosition(" + x + "," + y + ")");
        }

        @Override
        public void operateTo(int x, int y) {
            log.add("operateTo from (" + this.x + "," + this.y + ") to (" + x + "," + y + ")");
            this.x = x; this.y = y;
        }

        public List<String> getLog() {
            return new ArrayList<>(log);
        }
    }
}
package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ComplexCommandFactoryTest {

    @Test
    public void fromScriptRecordsCommands() {
        ComplexCommand cmd = ComplexCommandFactory.fromScript(FiguresJoe::figureScript1);

        RecordingDriver driver = new RecordingDriver();
        cmd.execute(driver);

        List<String> log = driver.getLog();
        assertFalse("Recorded command sequence should not be empty", log.isEmpty());
    }

    private static class RecordingDriver implements Job2dDriver {
        private final java.util.List<String> log = new java.util.ArrayList<>();
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

        public java.util.List<String> getLog() {
            return new java.util.ArrayList<>(log);
        }
    }
}

package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MacroCommandTest {

    @Test
    public void executeExecutesAllCommands() {
        MacroCommand macro = MacroCommand.of(
                new SetPositionCommand(1, 2),
                new OperateToCommand(3, 4)
        );

        RecordingDriver driver = new RecordingDriver();
        macro.execute(driver);

        List<String> log = driver.getLog();
        assertEquals(2, log.size());
        assertEquals("setPosition(1,2)", log.get(0));
        assertTrue(log.get(1).contains("operateTo"));
        assertTrue(log.get(1).contains("3"));
        assertTrue(log.get(1).contains("4"));
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

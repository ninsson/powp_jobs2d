package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ComplexCommandTest {

    @Test
    public void executeExecutesAllCommands() {
        ComplexCommand complex = ComplexCommand.of(
                new SetPositionCommand(1, 2),
                new OperateToCommand(3, 4)
        );

        RecordingDriver driver = new RecordingDriver();
        complex.execute(driver);

        List<String> log = driver.getLog();
        assertEquals(2, log.size());
        assertEquals("setPosition(1,2)", log.get(0));
        assertTrue(log.get(1).contains("operateTo"));
        assertTrue(log.get(1).contains("3"));
        assertTrue(log.get(1).contains("4"));
    }

    @Test
    public void andThenReturnsCombinedSequenceWithoutModifyingOriginal() {
        ComplexCommand original = ComplexCommand.of(
                new SetPositionCommand(0, 0),
                new OperateToCommand(1, 1)
        );

        ComplexCommand combined = original.andThen(new OperateToCommand(2, 2));

        RecordingDriver driverOriginal = new RecordingDriver();
        original.execute(driverOriginal);
        List<String> logOriginal = driverOriginal.getLog();
        assertEquals(2, logOriginal.size());

        RecordingDriver driverCombined = new RecordingDriver();
        combined.execute(driverCombined);
        List<String> logCombined = driverCombined.getLog();
        assertEquals(3, logCombined.size());
        assertTrue(logCombined.get(2).contains("2"));
        assertTrue(logCombined.get(2).contains("2"));
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

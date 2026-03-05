package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.AbstractDriver;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.function.Supplier;

/**
 * Adapter dziedziczący po AbstractDriver, delegujący rysowanie do bieżącego Job2dDriver.
 */
public class CurrentDriverAdapter extends AbstractDriver {

    private final Supplier<Job2dDriver> currentDriverSupplier;

    public CurrentDriverAdapter() {
        this(() -> DriverFeature.getDriverManager().getCurrentDriver());
    }

    public CurrentDriverAdapter(Supplier<Job2dDriver> currentDriverSupplier) {
        super(0, 0);
        this.currentDriverSupplier = currentDriverSupplier;
    }

    @Override
    public void operateTo(int x, int y) {
        Job2dDriver driver = currentDriverSupplier.get();

        if (driver == null || driver == this) {
            setPosition(x, y);
            return;
        }

        driver.setPosition(getX(), getY());
        driver.operateTo(x, y);

        setPosition(x, y);
    }

    @Override
    public String toString() {
        return "CurrentDriverAdapter (uses current driver)";
    }
}
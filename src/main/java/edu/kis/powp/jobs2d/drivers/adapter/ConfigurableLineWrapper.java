package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.shape.ILine;
import java.awt.Color;

public class ConfigurableLineWrapper implements ILine {

    private final ILine wrappedLine;
    private float thickness = 1.0f;
    private Color color = Color.BLACK;
    private float dashLength = 5.0f;
    private float gapLength = 5.0f;

    public ConfigurableLineWrapper(ILine line) {
        this.wrappedLine = line;
    }

    @Override
    public void setStartCoordinates(int x, int y) {
        wrappedLine.setStartCoordinates(x, y);
    }

    @Override
    public void setEndCoordinates(int x, int y) {
        wrappedLine.setEndCoordinates(x, y);
    }

    @Override
    public Color getColor() {
        return color;
    }

    public ConfigurableLineWrapper withColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public float getThickness() {
        return thickness;
    }

    public ConfigurableLineWrapper withThickness(float thickness) {
        this.thickness = thickness;
        return this;
    }

    @Override
    public boolean isDotted() {
        return wrappedLine.isDotted();
    }

    @Override
    public int getStartCoordinateX() {
        return wrappedLine.getStartCoordinateX();
    }

    @Override
    public int getEndCoordinateX() {
        return wrappedLine.getEndCoordinateX();
    }

    @Override
    public int getStartCoordinateY() {
        return wrappedLine.getStartCoordinateY();
    }

    @Override
    public int getEndCoordinateY() {
        return wrappedLine.getEndCoordinateY();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object o = super.clone();
        Object clonedInner = wrappedLine.clone();
        if (!(clonedInner instanceof ILine)) {
            throw new CloneNotSupportedException("Wrapped line clone is not ILine");
        }
        ILine clonedLine = (ILine) clonedInner;
        ConfigurableLineWrapper clonedWrapper = new ConfigurableLineWrapper(clonedLine);
        clonedWrapper.thickness = this.thickness;
        clonedWrapper.color = this.color;
        clonedWrapper.dashLength = this.dashLength;
        clonedWrapper.gapLength = this.gapLength;
        return clonedWrapper;
    }

    public ConfigurableLineWrapper withDashPattern(float dashLength, float gapLength) {
        this.dashLength = dashLength;
        this.gapLength = gapLength;
        return this;
    }

    public float getDashLength() {
        return dashLength;
    }

    public float getGapLength() {
        return gapLength;
    }
}

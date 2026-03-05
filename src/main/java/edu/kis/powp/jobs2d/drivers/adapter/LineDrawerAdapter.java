package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DrawerFeature;

/**
 * Adapter umożliwiający wybór rodzaju linii podczas rysowania.
 */
public class LineDrawerAdapter implements Job2dDriver {

    private final DrawPanelController drawerController;
    private int startX = 0, startY = 0;
    private ILine currentLine;

    public LineDrawerAdapter(ILine lineType) {
        this.drawerController = DrawerFeature.getDrawerController();
        this.currentLine = lineType;
    }

    @Override
    public void setPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void operateTo(int x, int y) {
        ILine line = currentLine;
        line.setStartCoordinates(this.startX, this.startY);
        line.setEndCoordinates(x, y);

        drawerController.drawLine(line);

        this.startX = x;
        this.startY = y;
    }

    @Override
    public String toString() {
        return "Line Drawer Adapter";
    }

    public void setLine(ILine line) {
        this.currentLine = line;
    }
}

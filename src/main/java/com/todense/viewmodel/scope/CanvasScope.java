package com.todense.viewmodel.scope;

import com.todense.viewmodel.canvas.Camera;
import com.todense.viewmodel.canvas.Painter;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CanvasScope implements Scope {

    private DoubleProperty canvasWidthProperty = new SimpleDoubleProperty();
    private DoubleProperty canvasHeightProperty = new SimpleDoubleProperty();


    private Painter painter;

    private Camera camera = new Camera();

    public double getCanvasWidth() {
        return canvasWidthProperty.get();
    }

    public DoubleProperty canvasWidthProperty() {
        return canvasWidthProperty;
    }

    public double getCanvasHeight() {
        return canvasHeightProperty.get();
    }

    public DoubleProperty canvasHeightProperty() {
        return canvasHeightProperty;
    }

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public Camera getCamera() {
        return camera;
    }

}

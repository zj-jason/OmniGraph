package com.todense.viewmodel;

import com.todense.viewmodel.canvas.DisplayMode;
import com.todense.viewmodel.canvas.MouseHandler;
import com.todense.viewmodel.canvas.Painter;
import com.todense.viewmodel.canvas.drawlayer.LowerDrawLayer;
import com.todense.viewmodel.canvas.drawlayer.UpperDrawLayer;
import com.todense.viewmodel.popover.PopOverManager;
import com.todense.viewmodel.scope.*;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

import javax.inject.Inject;

public class CanvasViewModel implements ViewModel {

    public final static String repaintRequest = "REPAINT";

    @InjectScope
    GraphScope graphScope;

    @InjectScope
    BackgroundScope backgroundScope;

    @InjectScope
    AlgorithmScope algorithmScope;

    @InjectScope
    CanvasScope canvasScope;

    @InjectScope
    AnimationScope animationScope;

    @InjectScope
    KeysScope keysScope;

    @InjectScope
    InputScope inputScope;

    @Inject
    NotificationCenter notificationCenter;


    private MouseHandler mouseHandler;

    private PopOverManager popOverManager;

    public void initialize(){

        Painter painter = new Painter(animationScope);

        popOverManager = new PopOverManager();

        mouseHandler = new MouseHandler(canvasScope.getCamera(),
                inputScope,
                painter,
                graphScope,
                popOverManager,
                keysScope.getPressedKeys());

        canvasScope.setPainter(painter);

        canvasScope.canvasWidthProperty().addListener((obs, oldVal, newVal) -> painter.repaint());
        canvasScope.canvasHeightProperty().addListener((obs, oldVal, newVal) -> painter.repaint());

        Platform.runLater(() ->{
            LowerDrawLayer lowerDrawLayer = new LowerDrawLayer(inputScope, graphScope);
            UpperDrawLayer upperDrawLayer = new UpperDrawLayer(graphScope, inputScope, canvasScope, backgroundScope, algorithmScope);

            painter.addDrawLayer(lowerDrawLayer);
            painter.addDrawLayer(upperDrawLayer);
            painter.repaint();
        });

        notificationCenter.subscribe("HIDE", (key, payload) -> {
            mouseHandler.hidePopOver();
            mouseHandler.clearSelection();
        });

        notificationCenter.subscribe(repaintRequest,  (key, payload) -> painter.repaint());
        notificationCenter.subscribe(MainViewModel.graphEditRequest,
                (key, payload) -> painter.repaint());

        notificationCenter.subscribe(GraphViewModel.newGraphRequest, (key, payload) -> {
            graphScope.displayModeProperty().set(DisplayMode.DEFAULT);
            painter.repaint();
        });

        notificationCenter.subscribe("ADJUST", (key, payload) ->
                canvasScope.getCamera().adjustToGraph(
                        graphScope.getGraphManager().getGraph(),
                        canvasScope.getCanvasWidth(),
                        canvasScope.getCanvasHeight(),
                        graphScope.getNodeSize()
                )
        );
    }

    public DoubleProperty canvasWidthProperty() {
        return canvasScope.canvasWidthProperty();
    }

    public DoubleProperty canvasHeightProperty() {
        return canvasScope.canvasHeightProperty();
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        canvasScope.getPainter().setGraphicContext(graphicsContext);
    }

    public void setCanvasNode(Node canvasNode){
        popOverManager.setOwner(canvasNode);
    }
}

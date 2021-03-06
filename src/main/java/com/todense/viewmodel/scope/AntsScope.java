package com.todense.viewmodel.scope;

import com.todense.model.graph.Edge;
import com.todense.viewmodel.ants.Ant;
import com.todense.viewmodel.ants.AntColonyVariant;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AntsScope implements Scope {

    private ArrayList<Ant> ants = new ArrayList<>();

    ArrayList<Integer> gbCycle = new ArrayList<>();

    private double[][] pheromones;

    //PARAMETERS
    private IntegerProperty antCountProperty = new SimpleIntegerProperty(5);
    private IntegerProperty neighbourhoodSizeProperty = new SimpleIntegerProperty(15);
    private IntegerProperty rankSizeProperty = new SimpleIntegerProperty(5); // number of ants in ranking that add pheromones
    private DoubleProperty alphaProperty = new SimpleDoubleProperty(1); // pheromone influence
    private DoubleProperty betaProperty = new SimpleDoubleProperty(2); // distance influence
    private DoubleProperty evaporationProperty = new SimpleDoubleProperty(0.1);
    private DoubleProperty exploitationStrengthProperty = new SimpleDoubleProperty(0.9); //exploitation strength
    private DoubleProperty localEvaporationProperty = new SimpleDoubleProperty(0.1);

    //LOCAL SEARCH
    private BooleanProperty with2OptProperty = new SimpleBooleanProperty(true);
    private BooleanProperty with3OptProperty = new SimpleBooleanProperty(false);

    //VISUALS
    private DoubleProperty scaleProperty = new SimpleDoubleProperty(50);
    private BooleanProperty antsAnimationOnProperty = new SimpleBooleanProperty(true);
    private BooleanProperty showPheromonesProperty = new SimpleBooleanProperty(true);
    private ObjectProperty<Color> antColorProperty = new SimpleObjectProperty<>(Color.rgb(200,0,0));
    private ObjectProperty<Color> cycleColorProperty = new SimpleObjectProperty<>(Color.rgb(210,210,30));
    private DoubleProperty antSize = new SimpleDoubleProperty(0.25);
    private ObjectProperty<AntColonyVariant> algorithmProperty = new SimpleObjectProperty<>();


    public double getPheromone(Edge e){
        if(pheromones == null
                || e.getN1().getIndex() >= pheromones.length
                || e.getN2().getIndex() >= pheromones.length)
            return 0;
        return pheromones[e.getN1().getIndex()][e.getN2().getIndex()];
    }

    public void setPheromone(int i, int j, double amount){
        pheromones[i][j] = amount;
    }

    public double getPheromone(int i, int j){
        return pheromones[i][j];
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }

    public Color getAntColor() {
        return antColorProperty.get();
    }

    public ObjectProperty<Color> antColorProperty() {
        return antColorProperty;
    }

    public double getAntSize() {
        return antSize.get();
    }

    public ObjectProperty<AntColonyVariant> algorithmProperty() {
        return algorithmProperty;
    }

    public int getAntCount() {
        return antCountProperty.get();
    }

    public IntegerProperty antCountProperty() {
        return antCountProperty;
    }

    public double getAlpha() {
        return alphaProperty.get();
    }

    public DoubleProperty alphaProperty() {
        return alphaProperty;
    }

    public double getBeta() {
        return betaProperty.get();
    }

    public DoubleProperty betaProperty() {
        return betaProperty;
    }

    public double getEvaporation() {
        return evaporationProperty.get();
    }

    public DoubleProperty evaporationProperty() {
        return evaporationProperty;
    }

    public double getExploitationStrength() {
        return exploitationStrengthProperty.get();
    }

    public DoubleProperty exploitationStrengthProperty() {
        return exploitationStrengthProperty;
    }

    public double getLocalEvaporation() {
        return localEvaporationProperty.get();
    }

    public DoubleProperty localEvaporationProperty() {
        return localEvaporationProperty;
    }

    public double getScale() {
        return scaleProperty.get();
    }

    public DoubleProperty scaleProperty() {
        return scaleProperty;
    }

    public boolean isWith2Opt() {
        return with2OptProperty.get();
    }

    public BooleanProperty with2OptProperty() {
        return with2OptProperty;
    }

    public boolean isWith3Opt() {
        return with3OptProperty.get();
    }

    public BooleanProperty with3OptProperty() {
        return with3OptProperty;
    }

    public void setPheromones(double[][] pheromones) {
        this.pheromones = pheromones;
    }

    public ArrayList<Integer> getGbCycle() {
        return gbCycle;
    }

    public void setGbCycle(ArrayList<Integer> gbCycle) {
        this.gbCycle = gbCycle;
    }

    public boolean isAntsAnimationOn() {
        return antsAnimationOnProperty.get();
    }

    public BooleanProperty antsAnimationOnProperty() {
        return antsAnimationOnProperty;
    }

    public boolean isShowingPheromones() {
        return showPheromonesProperty.get();
    }

    public BooleanProperty showPheromonesProperty() {
        return showPheromonesProperty;
    }

    public Color getCycleColor() {
        return cycleColorProperty.get();
    }

    public ObjectProperty<Color> cycleColorProperty() {
        return cycleColorProperty;
    }

    public int getNeighbourhoodSize() {
        return neighbourhoodSizeProperty.get();
    }

    public IntegerProperty neighbourhoodSizeProperty() {
        return neighbourhoodSizeProperty;
    }

    public int getRankSize() {
        return rankSizeProperty.get();
    }

    public IntegerProperty rankSizeProperty() {
        return rankSizeProperty;
    }
}

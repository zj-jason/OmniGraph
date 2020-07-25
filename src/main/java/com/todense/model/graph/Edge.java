package com.todense.model.graph;

import javafx.scene.paint.Color;

public class Edge {

    private Node n1;
    private Node n2;

    private Color color;

    double weight = 0;
    double length = 0;

    private boolean marked = false;
    private boolean selected = false;
    private boolean visible = true;
    private boolean highlighted = false;

    public Edge(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
        color = Color.rgb(50,130,200);
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setLength() {
        this.length = n1.getPos().distance(n2.getPos());
    }

    public Node getN1() {
        return n1;
    }

    public Node getN2() {
        return n2;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String toString(){
        return n1.getIndex()+"-"+n2.getIndex();
    }
}
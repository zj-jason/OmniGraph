package com.todense.model.graph;

import com.todense.model.EdgeList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int idCounter = 0;

    private List<Node> nodes = new ArrayList<>();
    private EdgeList edges = new EdgeList();

    public String name;

    public Graph(String name){
        this.name=name;
    }

    public Graph(){
        this("UnnamedGraph");
    }

    public Node addNode(){
       return addNode(new Point2D(0, 0));
    }

    public Node addNode(Point2D pt, Color color){
        Node node = addNode(pt);
        node.setColor(color);
        return node;
    }

    public Node addNode(Point2D pt){
        return addNode(pt, idCounter++);
    }

    public Node addNode(Point2D pt, int id){
        Node n = new Node(pt, nodes.size(), id);
        nodes.add(n);
        return n;
    }


    public Edge addEdge(Node n, Node m){
        assert !edges.isEdgeBetween(n, m):
                "Edge "+edges.getEdge(n, m).toString()+" already exist!";
        Edge e = new Edge(n, m);
        edges.add(e);
        return e;
    }

    public Edge addEdge(Node n, Node m, Color color){
        Edge edge = addEdge(n, m);
        edge.setColor(color);
        return edge;
    }

    public void removeEdge(Node n1, Node n2){
        edges.remove(n1, n2);
    }

    public void removeEdge(Edge e){
        edges.remove(e);
    }

    public void removeAllEdges(){
        edges.clear();
        for (Node node : nodes) {
            node.getNeighbours().clear();
        }
    }

    public void removeNode(Node n){
        //decrement indexes
        for (int i = n.getIndex() + 1; i < nodes.size(); i++) {
            nodes.get(i).setIndex( nodes.get(i).getIndex() - 1) ;
        }

        nodes.remove(n);
        new ArrayList<>(n.getNeighbours()).forEach(m -> removeEdge(n, m));
    }


    public void reset(){
        for (Node n : nodes) {
            n.setMarked(false);
            n.setSelected(false);
            n.setVisited(false);
        }
        for (Edge e : edges) {
            e.setMarked(false);
            e.setVisible(true);
        }
    }

    public Graph copy(){
        Graph copyGraph = new Graph(name+"-copy");
        nodes.forEach(n -> copyGraph.addNode(n.getPos()));
        for (Edge edge : edges) {
            int i = edge.getN1().getIndex();
            int j = edge.getN2().getIndex();
            copyGraph.addEdge(copyGraph.getNodes().get(i), copyGraph.getNodes().get(j));
        }
        return copyGraph;
    }

    public Edge getEdge(Node n, Node m){
        return edges.getEdge(n, m);
    }

    public Edge getEdge(int i, int j){
        return getEdge(nodes.get(i), nodes.get(j));
    }

    public Node getNodeById(int id){
        for(Node node : nodes){
            if(node.getID() == id)
                return node;
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public EdgeList getEdges() {
        return edges;
    }

    public int getOrder(){
        return nodes.size();
    }

    public int getSize(){
        return edges.size();
    }

    public String toString() {
        return name;
    }
}

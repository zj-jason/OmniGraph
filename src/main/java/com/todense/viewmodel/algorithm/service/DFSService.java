package com.todense.viewmodel.algorithm.service;

import com.todense.model.graph.Node;
import com.todense.viewmodel.algorithm.AlgorithmService;

import java.util.HashMap;
import java.util.Stack;


public class DFSService extends AlgorithmService {

	private Node startNode;

	public DFSService(Node startNode){
		super(startNode.getGraph());
		this.startNode = startNode;
	}

	@Override
	protected void perform() throws InterruptedException {
		DFS();
	}

	@Override
	protected void onFinished() {
	}

	public void DFS() throws InterruptedException{
		ComponentDFS(startNode);

		for(Node n: graph.getNodes()) {
			if (!n.isVisited()) {
				ComponentDFS(n);
			}
		}
	}

	public void ComponentDFS(Node n) throws InterruptedException {

		Stack<Node> stack = new Stack<>();
		HashMap<Node, Node> prev = new HashMap<>();

		graph.getNodes().forEach((node) -> prev.put(node, null));

		stack.push(n);

		while(!stack.isEmpty()){
			Node m = stack.pop();

			if(m.isVisited())
				continue;

			m.setVisited(true);
			if(prev.get(m) != null){
				graph.getEdge(m, prev.get(m)).setMarked(true);
			}
			painter.sleep();

			for (Node k : m.getNeighbours()) {
				if(!k.isVisited()){
					stack.push(k);
					prev.put(k, m);
				}
			}
		}
	}
}

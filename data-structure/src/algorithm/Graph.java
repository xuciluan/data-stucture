package algorithm;

import java.util.List;

public class Graph {

	private final List<Vertex> vertexs;
	private final List<Edge> edges;
	
	public Graph(List<Vertex> vertexs,List<Edge> edges){
		this.vertexs = vertexs;
		this.edges = edges;
	}

	public List<Vertex> getVertexs() {
		return vertexs;
	}


	public List<Edge> getEdges() {
		return edges;
	}

}

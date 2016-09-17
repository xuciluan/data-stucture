package algorithm;


import java.util.ArrayList;

import java.util.List;


public class TestDijikstraAlgorithm {

	  private static List<Vertex> nodes;
	  private static List<Edge> edges;
	  public static void main(String[] args){
		  nodes = new ArrayList<Vertex>();
		    edges = new ArrayList<Edge>();
		    for (int i = 0; i < 11; i++) {
		    	//���ж���ļ���
		      Vertex location = new Vertex("Node_" + i, "Node_" + i);
		      nodes.add(location);
		    }

		    //��Ӵ�0��1�ı�
		    addLane("Edge_0", 0, 1, 85);
		    addLane("Edge_1", 0, 2, 217);
		    addLane("Edge_2", 0, 4, 173);
		    addLane("Edge_3", 2, 6, 186);
		    addLane("Edge_4", 2, 7, 103);
		    addLane("Edge_5", 3, 7, 183);
		    addLane("Edge_6", 5, 8, 250);
		    addLane("Edge_7", 8, 9, 84);
		    addLane("Edge_8", 7, 9, 167);
		    addLane("Edge_9", 4, 9, 502);
		    addLane("Edge_10", 9, 10, 40);
		    addLane("Edge_11", 1, 10, 600);

		    // Lets check from location Loc_1 to Loc_10
		    Graph graph = new Graph(nodes, edges);
		    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		    //�Ӷ���0����
		    
		    List<Vertex> path = dijkstra.getShortestPath(nodes.get(0), nodes.get(10));
		    
		    for (Vertex vertex : path) {
		      System.out.println(vertex);
		    }
		    System.out.println(dijkstra.getShortDistance(nodes.get(0),nodes.get(10))); 
		    for (Vertex vertex : dijkstra.getSettled()) {
			      System.out.println(vertex);
		    }
		    
	}
	

	  private static void addLane(String laneId, int sourceLocNo, int destLocNo,
	      int duration) {
	    Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
	    edges.add(lane);
	  }
}

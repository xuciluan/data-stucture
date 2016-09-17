package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 迪杰斯特拉算法
 * @author xcl
 *
 */
public class DijkstraAlgorithm {


	private List<Vertex> vertexs;
	private List<Edge>   edges;
	private Set<Vertex> unSettled; //放置当前还未遍历过的点
	private Set<Vertex> settled; //放置已经遍历过的点
	private Map<Vertex,Integer> distance;  //到达该点的最小距离
	private Map<Vertex,Vertex> proccessor ; //放置某个点的最短路径前驱顶点
	
	public DijkstraAlgorithm(Graph graph){
		this.vertexs = graph.getVertexs();
		this.edges = graph.getEdges();
		this.unSettled = new LinkedHashSet<>();
		this.settled = new LinkedHashSet<>();
		this.distance = new HashMap<>();
		this.proccessor = new HashMap<>();
	}
	
	public List<Vertex> getShortestPath(Vertex source,Vertex destination){
		
		unSettled.clear();
		settled.clear();
		proccessor.clear();
		distance.clear();
		
		unSettled.add(source);
		distance.put(source, 0);
		//最短算法的实现步骤
		while(unSettled.size()>0){
			
			//取得当前距离最小的点后
			Vertex target = getMininumNode(unSettled);
			unSettled.remove(target);
			settled.add(target);
			   //遍历它的neighbor，更新到它的neighbor的最短距离，并且它的neighbor加入到遍历的序列中
				List<Vertex> neighbors = getNeighbors(target);
				for(Vertex node:neighbors){
					//得到到达该点的最短距离
					if(getMininumDistanceToNode(node) > 
					getMininumDistanceToNode(target)+getDistance(target,node)){
						
						//此时它的最短距离应该进行更新
						distance.put(node, getMininumDistanceToNode(target)+getDistance(target,node));
						proccessor.put(node, target);
						unSettled.add(node);
					}
				}
			}
	
	     Vertex step = destination;
	     List<Vertex> path = new ArrayList<>();
		if(proccessor.get(step)== null){
			return null;
		}else{
			path.add(step);
			while(proccessor.get(step)!= null){
				path.add(proccessor.get(step));
				step = proccessor.get(step);
			}
			Collections.reverse(path);
			return path;
		}
	}

	/**
	 * 求当前距离最小的点
	 * @param unSettled2
	 * @return
	 */
	private Vertex getMininumNode(Set<Vertex> unSettled2) {
		
		Vertex mininum = null;
		for(Vertex node : unSettled2){
			if(mininum == null){
				mininum = node;
			}else{
				if(getMininumDistanceToNode(node) < getMininumDistanceToNode(mininum)){
					mininum = node;
				}
			}
		}
		return mininum;
	}

	/**
	 * 求两点间的距离
	 * @param target
	 * @param node
	 * @return
	 */
	private Integer getDistance(Vertex target, Vertex node) {
	
		for(Edge edge : edges){
			if(edge.getSource().equals(target) && edge.getDestination().equals(node)){
				return edge.getWeight();
			}
		}
		return null;
	}

	/**
	 * 求到某一个点的最短距离
	 * @param node
	 * @return
	 */
	private Integer getMininumDistanceToNode(Vertex node) {
		Integer dis = distance.get(node);
		if(dis == null){
			//distance.put(node, Integer.MAX_VALUE);
			return Integer.MAX_VALUE;
		}else{
			return dis;
		}
	}

	private List<Vertex> getNeighbors(Vertex target) {
	
		List<Vertex> neighbors = new ArrayList<>();
		//遍历所有的边，找到边的起点是target的点
		for(Edge edge : this.edges){
			if(edge.getSource().equals(target)){
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}
	
	/**
	 * 从一个顶点到另一个顶点的最小距离
	 * @param source
	 * @param target
	 * @return
	 */
	public int getShortDistance(Vertex source,Vertex target){
		if(distance.get(target)== null){
			getDistance(source, target);
		}
		return distance.get(target);
	}
	
	
	public Set<Vertex> getSettled(){
		return settled;
	}
}

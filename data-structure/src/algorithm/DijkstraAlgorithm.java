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
 * �Ͻ�˹�����㷨
 * @author xcl
 *
 */
public class DijkstraAlgorithm {


	private List<Vertex> vertexs;
	private List<Edge>   edges;
	private Set<Vertex> unSettled; //���õ�ǰ��δ�������ĵ�
	private Set<Vertex> settled; //�����Ѿ��������ĵ�
	private Map<Vertex,Integer> distance;  //����õ����С����
	private Map<Vertex,Vertex> proccessor ; //����ĳ��������·��ǰ������
	
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
		//����㷨��ʵ�ֲ���
		while(unSettled.size()>0){
			
			//ȡ�õ�ǰ������С�ĵ��
			Vertex target = getMininumNode(unSettled);
			unSettled.remove(target);
			settled.add(target);
			   //��������neighbor�����µ�����neighbor����̾��룬��������neighbor���뵽������������
				List<Vertex> neighbors = getNeighbors(target);
				for(Vertex node:neighbors){
					//�õ�����õ����̾���
					if(getMininumDistanceToNode(node) > 
					getMininumDistanceToNode(target)+getDistance(target,node)){
						
						//��ʱ������̾���Ӧ�ý��и���
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
	 * ��ǰ������С�ĵ�
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
	 * �������ľ���
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
	 * ��ĳһ�������̾���
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
		//�������еıߣ��ҵ��ߵ������target�ĵ�
		for(Edge edge : this.edges){
			if(edge.getSource().equals(target)){
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}
	
	/**
	 * ��һ�����㵽��һ���������С����
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

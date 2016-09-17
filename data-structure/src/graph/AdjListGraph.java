package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * ������ڽӱ��ʾ��������ͼ
 * @author xcl
 *
 */
public class AdjListGraph {
	

	Node rootNode;
	List<Node> nodes = new ArrayList<>();
	Map<Character,LinkedList<Node>> neihbors= new HashMap<>();
	
	public void addNode(Node node){
		this.nodes.add(node);
		this.neihbors.put(node.label, new LinkedList<>());
	}
	
	public void connectNode(Node node1,Node node2){
		LinkedList<Node> node1Neighbor = this.neihbors.get(node1.label);
		LinkedList<Node> node2Neighbor = this.neihbors.get(node2.label);
		node1Neighbor.add(node2);
		node2Neighbor.add(node1);
		this.neihbors.put(node1.label, node1Neighbor);
		this.neihbors.put(node2.label, node2Neighbor);
	}

	private Node getUnVisitedChildNode(Node node){

		LinkedList<Node> childs = this.neihbors.get(node.label);
		for(Node child : childs){
			if(!child.visited){
				return child;
			}
		}
		return null;
	}
	
	
	public void printfNode(Node node){
		System.out.print(node.label+" ");
	}
	
	public void setRootNode(Node node){
		this.rootNode = node;
	}
	
	public void bfs(){
		//������ȱ���
		//(1)�ȴ�ӡ���ڵ�
		Queue<Node> queue = new LinkedList<>();
		//printfNode(this.rootNode);
		//this.rootNode.visited = true;
		//LinkedList<Node> childNeighbor = neihbors.get(this.rootNode);
		queue.add(this.rootNode);
		while(!queue.isEmpty()){
			//������в�Ϊ�գ���ô���д�ӡ
			Node node = queue.remove();
			printfNode(node);
			node.visited = true;
			//�����У�Ȼ��������ĺ��Ӽ������
			//�ж����ĺ����Ƿ������
			LinkedList<Node> linkedList = neihbors.get(node.label);
			for(Node childNode : linkedList){
				if(!childNode.visited){
					queue.add(childNode);
					childNode.visited = true;
				}
			}
		}
		clearNodeState();
	}
	
	public void dfs(){
		//������ȱ��������õ���stack
		Stack<Node> stack = new Stack<>();
		stack.push(rootNode);
		while(!stack.isEmpty()){
			Node node = stack.peek();
			if(!node.visited){
				printfNode(node);
			    node.visited = true;
			}
			//����������е㲻һ��������Ҫһ��һ�����뺢�ӣ�����һ���Լ��룬��ΪҪ�Ժ��ӽ�����ȱ���
			Node childNode = getUnVisitedChildNode(node);
			if(childNode == null){
				stack.pop();
			}else{
				stack.push(childNode);
				printfNode(childNode);
				childNode.visited = true;
			}
			/*LinkedList<Node> linkedList = neihbors.get(node.label);
			for(Node childNode : linkedList){
				if(!childNode.visited){
					stack.push(childNode);
					childNode.visited = true;
				}
			}*/
			
		}
		clearNodeState();
	}
	
	private void clearNodeState(){
		for(Node node: nodes){
			node.visited = false;
		}
	}
}

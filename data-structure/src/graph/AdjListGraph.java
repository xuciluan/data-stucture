package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 这里的邻接表表示的是无向图
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
		//广度优先遍历
		//(1)先打印根节点
		Queue<Node> queue = new LinkedList<>();
		//printfNode(this.rootNode);
		//this.rootNode.visited = true;
		//LinkedList<Node> childNeighbor = neihbors.get(this.rootNode);
		queue.add(this.rootNode);
		while(!queue.isEmpty()){
			//如果队列不为空，那么进行打印
			Node node = queue.remove();
			printfNode(node);
			node.visited = true;
			//最顶层出列，然后将这个最顶层的孩子加入队列
			//判断它的孩子是否遍历过
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
		//深度优先遍历，采用的是stack
		Stack<Node> stack = new Stack<>();
		stack.push(rootNode);
		while(!stack.isEmpty()){
			Node node = stack.peek();
			if(!node.visited){
				printfNode(node);
			    node.visited = true;
			}
			//这里跟上面有点不一样，这里要一个一个加入孩子，不能一次性加入，因为要对孩子进行深度遍历
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

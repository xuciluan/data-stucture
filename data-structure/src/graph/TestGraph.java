package graph;

public class TestGraph {


    public static void main(String[] args) 
    {
        
    	//�����ڽӱ�
        //Lets create nodes as given as an example in the article
        Node nA=new Node('A');
        Node nB=new Node('B');
        Node nC=new Node('C');
        Node nD=new Node('D');
        Node nE=new Node('E');
        Node nF=new Node('F');

        //Create the graph, add nodes, create edges between nodes
        AdjListGraph g=new AdjListGraph();
        g.addNode(nA);
        g.addNode(nB);
        g.addNode(nC);
        g.addNode(nD);
        g.addNode(nE);
        g.addNode(nF);
        g.setRootNode(nA);
        
        g.connectNode(nA,nB);
        g.connectNode(nA,nC);
        g.connectNode(nA,nD);
        
        g.connectNode(nB,nE);
        g.connectNode(nB,nF);
        g.connectNode(nC,nF);
        
        
        //Perform the traversal of the graph
        System.out.println("�ڽӱ��DFS Traversal of a tree is ------------->");
        g.dfs();
        
        System.out.println("\n�ڽӱ��BFS Traversal of a tree is ------------->");
        g.bfs();    
        
        
        //�����ڽӾ���
        //Create the graph, add nodes, create edges between nodes
        AdjMatrixGraph adjMatrix=new AdjMatrixGraph();
        adjMatrix.addNode(nA);
        adjMatrix.addNode(nB);
        adjMatrix.addNode(nC);
        adjMatrix.addNode(nD);
        adjMatrix.addNode(nE);
        adjMatrix.addNode(nF);
        adjMatrix.setRootNode(nA);
        
        adjMatrix.connectNode(nA,nB);
        adjMatrix.connectNode(nA,nC);
        adjMatrix.connectNode(nA,nD);
        
        adjMatrix.connectNode(nB,nE);
        adjMatrix.connectNode(nB,nF);
        adjMatrix.connectNode(nC,nF);
        
        
        //Perform the traversal of the graph
        System.out.println("\n�ڽӾ����DFS Traversal of a tree is ------------->");
        adjMatrix.dfs();
        
        System.out.println("\n�ڽӾ����BFS Traversal of a tree is ------------->");
        adjMatrix.bfs();         
    }
}

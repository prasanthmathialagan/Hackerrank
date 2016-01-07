import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstSearch
{
	private static final int DEF_EDGE_WEIGHT = 6;
	private static final int DEF_NODE_WEIGHT = 1;

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		// Test cases
		while (T-- > 0)
		{
			Graph g = constructGraph(sc);
			Node startNode = g.getNode(sc.nextInt());

			// BFS O(N + M)
			Graph bfs = runBFS(g, startNode);

			int[] dist = new int[g.getNodes().size() + 1];
			
			// O(n)
			Arrays.fill(dist, -1);

			// O(n)
			for (Node n : bfs.getNodes())
				dist[n.id] = n.value;
			
			for (int i = 1; i < dist.length; i++)
			{
				if(dist[i] == 0) // it is the source node
					continue;
				
				System.out.print(dist[i] + " ");
			}
			
			System.out.println();
		}

		sc.close();
	}
	
	private static Graph constructGraph(Scanner sc) throws Exception
	{
		Graph graph = new Graph();
		
		// Construct nodes
		int N = sc.nextInt();
		for (int i = 1; i <= N; i++)
			graph.addOrGetNode(i, DEF_NODE_WEIGHT);
		
		// Construct edges(un-directed)
		int M = sc.nextInt();
		for (int i = 0; i < M; i++)
		{
			Node start = graph.getNode(sc.nextInt());
			Node end = graph.getNode(sc.nextInt());
			
			// Bi directional edges
			start.addEdge(end, DEF_EDGE_WEIGHT);
			end.addEdge(start, DEF_EDGE_WEIGHT);
		}
		
		return graph;
	}

	public static class Graph
	{
		private List<Node> nodes = new ArrayList<>();
		private List<Edge> edges = new ArrayList<>();
		private Map<Integer, Node> idToNodesMap = new HashMap<>();

		public Node addOrGetNode(int id, int value) throws Exception
		{
			Node node = idToNodesMap.get(id);
			if (node != null)
				return node;
			node = new Node(this, id, value);
			nodes.add(node);
			idToNodesMap.put(id, node);
			return node;
		}

		public Node getNode(int id)
		{
			return idToNodesMap.get(id);
		}

		private void addEdge(Edge e)
		{
			edges.add(e);
		}

		public List<Node> getNodes()
		{
			return new ArrayList<>(nodes);
		}

		public List<Edge> getEdges()
		{
			return new ArrayList<>(edges);
		}

		@Override
		public String toString()
		{
			return "Graph [edges=" + edges + "]";
		}
	}

	public static class Node
	{
		private final Graph graph;
		private final int id;
		private int value;

		// Key is the other end node id
		private Map<Integer, Edge> outgoingEdges = new HashMap<>();
		private Map<Integer, Edge> incomingEdges = new HashMap<>();

		private Node(Graph graph, int id, int value)
		{
			super();
			this.graph = graph;
			this.id = id;
			this.value = value;
		}

		public void addEdge(Node otherEnd, int weight) throws Exception
		{
			if (outgoingEdges.containsKey(otherEnd.id))
			{
				Edge edge = outgoingEdges.get(otherEnd.id);
				if(edge.weight < weight)
					edge.weight = weight;
				return;
			}
			Edge e = new Edge(this, otherEnd, weight);
			outgoingEdges.put(otherEnd.id, e);
			otherEnd.incomingEdges.put(id, e);
			graph.addEdge(e);
		}
		
		public void removeEdge(Edge edge)
		{
			Node otherNode = edge.end;
			outgoingEdges.remove(edge.end.id);
			otherNode.incomingEdges.remove(edge.start.id);
			graph.edges.remove(edge);
		}

		public List<Edge> getIncomingEdges()
		{
			return new ArrayList<>(incomingEdges.values());
		}

		public List<Edge> getOutgoingEdges()
		{
			return new ArrayList<>(outgoingEdges.values());
		}

		public int getIndegree()
		{
			return incomingEdges.size();
		}

		public int getOutDegree()
		{
			return outgoingEdges.size();
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public String toString()
		{
			return "Node [id=" + id + ", value=" + value + "]";
		}
	}

	public static class Edge
	{
		private final Node start;
		private final Node end;

		private int weight;

		private Edge(Node start, Node end, int weight)
		{
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((end == null) ? 0 : end.hashCode());
			result = prime * result + ((start == null) ? 0 : start.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (end == null)
			{
				if (other.end != null)
					return false;
			} else if (!end.equals(other.end))
				return false;
			if (start == null)
			{
				if (other.start != null)
					return false;
			} else if (!start.equals(other.start))
				return false;
			return true;
		}

		@Override
		public String toString()
		{
			return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}
	}

	private static Graph runBFS(Graph graph, Node node) throws Exception
	{
		Graph bfsGraph = new Graph();

		Set<Integer> visitedNodes = new HashSet<>();
		Queue<Node> queue = new LinkedBlockingQueue<>();

		bfsGraph.addOrGetNode(node.id, 0);
		queue.offer(node);
		visitedNodes.add(node.id);

		while (queue.peek() != null)
		{
			Node n = queue.poll();
			Node bfsn = bfsGraph.getNode(n.id);

			List<Edge> edges = n.getOutgoingEdges();
			for (Edge edge : edges)
			{
				Node en = edge.end;
				if (visitedNodes.contains(en.id))
					continue;
				bfsn.addEdge(bfsGraph.addOrGetNode(en.id, bfsn.value + edge.weight), 1);
				queue.offer(en);
				visitedNodes.add(en.id);
			}
		}

		return bfsGraph;
	}
}

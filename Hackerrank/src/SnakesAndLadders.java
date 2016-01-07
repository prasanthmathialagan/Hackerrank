import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class SnakesAndLadders
{
	private static final int DEF_WEIGHT = 1;
	private static final int TELEPORT_WEIGHT = 0;
	private static final int INFINITY = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		// Test cases
		while (T-- > 0)
		{
			Graph board = constructBoard(sc);
			
			// BFS O(2N+L+S)
			Graph bfs = runBFS(board);
			
			Node endNode = bfs.getNode(100);
			if(endNode == null)
			{
				System.out.println(-1);
				continue;
			}
			
			int steps = 0;
			List<Edge> incomingEdges = endNode.getIncomingEdges();
			while (!incomingEdges.isEmpty())
			{
				// Add the other end node
				Edge edge = incomingEdges.get(0);
				steps += edge.weight;
				
				// Make the other end node as end node and get it's incoming edges.
				endNode = edge.start;
				incomingEdges = endNode.getIncomingEdges();
			}
			
			System.out.println(steps);
		}
		
		sc.close();
	}
	
	private static Graph constructBoard(Scanner sc) throws Exception
	{
		Graph graph = new Graph();
		
		Map<Integer, Integer> ladders = new HashMap<>();
		Map<Integer, Integer> snakes = new HashMap<>();
		
		// Add ladders
		int L = sc.nextInt();
		while (L-- > 0)
			ladders.put(sc.nextInt(), sc.nextInt());

		// Add snakes
		int S = sc.nextInt();
		while (S-- > 0)
			snakes.put(sc.nextInt(), sc.nextInt());
		
		Queue<Node> queue = new LinkedBlockingQueue<>();
		Set<Integer> visitedNodes = new HashSet<>();
		
		// Add the start node to the queue and start from it
		queue.offer(graph.addOrGetNode(1, INFINITY));
		visitedNodes.add(1);
		
		while(queue.peek() != null)
		{
			Node node = queue.poll();
			if(node.id == 100)
				continue;
			
			// 1. Draw edge to the next hop. After 94, next hop will be 100 for all the nodes. 
			int nextHop = node.id + 6 > 100 ? 100 : node.id + 6;
			createNodeAndAddEdge(graph, queue, visitedNodes, node, nextHop, DEF_WEIGHT);
			
			// 2. Draw edge to the ladders and snakes within 6 positions
			for (int i = node.id + 1; i < nextHop; i++)
			{
				if(ladders.containsKey(i))
					createNodeAndAddEdge(graph, queue, visitedNodes, node, i, DEF_WEIGHT);
				else if(snakes.containsKey(i))
					createNodeAndAddEdge(graph, queue, visitedNodes, node, i, DEF_WEIGHT);
			}

			// 3. Draw edge between the positions which a ladder or a snake takes us to.
			Integer ladderHop = ladders.get(node.id);
			if(ladderHop != null)
				createNodeAndAddEdge(graph, queue, visitedNodes, node, ladderHop, TELEPORT_WEIGHT);
			
			Integer snakeHop = snakes.get(node.id);
			if(snakeHop != null)
				createNodeAndAddEdge(graph, queue, visitedNodes, node, snakeHop, TELEPORT_WEIGHT);
		}
		
		// Remove all the edges but the transition edges from ladders and snakes.
		removeRedundantEdgesFromLorS(graph, ladders);
		removeRedundantEdgesFromLorS(graph, snakes);
		
		return graph;
	}

	private static Node createNodeAndAddEdge(Graph graph, Queue<Node> queue, Set<Integer> visitedNodes, Node srcNode,
			int nextHopNodeId, int edgeWeight) throws Exception
	{
		Node nextHopNode = graph.addOrGetNode(nextHopNodeId, INFINITY);
		srcNode.addEdge(nextHopNode, edgeWeight);
		if(!visitedNodes.contains(nextHopNodeId))
		{
			queue.offer(nextHopNode);
			visitedNodes.add(nextHopNodeId);
		}
		return nextHopNode;
	}

	// Remove the edges between the base of the ladders and between the mouths of the snakes.
	private static void removeRedundantEdgesFromLorS(Graph graph, Map<Integer, Integer> ladders)
	{
		for (Entry<Integer, Integer> entry : ladders.entrySet())
		{
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			Node n = graph.getNode(key);
			List<Edge> edges = n.getOutgoingEdges();
			for (Edge edge : edges)
			{
				if (edge.end.id != value.intValue())
					n.removeEdge(edge);
			}
		}
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
	
	// Run BFS from the first node.
	private static Graph runBFS(Graph graph) throws Exception
	{
		Graph bfsGraph = new Graph();

		Set<Integer> visitedNodes = new HashSet<>();
		Queue<Node> queue = new LinkedBlockingQueue<>();

		Node node = graph.getNode(1);
		bfsGraph.addOrGetNode(node.id, node.value);
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
				bfsn.addEdge(bfsGraph.addOrGetNode(en.id, en.value), edge.weight);
				queue.offer(en);
				visitedNodes.add(en.id);
			}
		}

		return bfsGraph;
	}
}

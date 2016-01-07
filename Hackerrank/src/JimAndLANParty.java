import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class JimAndLANParty
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int friends = sc.nextInt();
		int games = sc.nextInt();
		int wires = sc.nextInt();

		int[] gamesToPlayerCount = new int[games + 1];
		int[] playersToGame = new int[friends + 1];
		for (int i = 1; i <= friends; i++)
		{
			int game = sc.nextInt();
			gamesToPlayerCount[game] = gamesToPlayerCount[game] + 1;
			playersToGame[i] = game;
		}

		int[] result = new int[games + 1];
		Arrays.fill(result, -1);

		Map<Integer, Cluster> playerToClusterMap = new HashMap<>();
		for (int i = 1; i <= wires; i++)
		{
			int start = sc.nextInt();
			int end = sc.nextInt();

			Cluster startCluster = playerToClusterMap.get(start);
			Cluster endCluster = playerToClusterMap.get(end);

			if (startCluster == null && endCluster == null)
			{
				Cluster c = createCluster(start, playerToClusterMap, playersToGame);
				mergeIfOneNull(gamesToPlayerCount, result, playerToClusterMap, i, c, end, playersToGame[end]);
			} 
			else if (startCluster == null || endCluster == null)
			{
				Cluster baseCluster = startCluster == null ? endCluster : startCluster;
				int newPlayer = startCluster == null ? start : end;
				int game = playersToGame[newPlayer];
				mergeIfOneNull(gamesToPlayerCount, result, playerToClusterMap, i, baseCluster, newPlayer, game);
			} 
			else // Merging clusters
			{
				// Merging common games from second cluster
				for (Entry<Integer, Set<Integer>> entry : startCluster.nodes.entrySet())
				{
					Integer game = entry.getKey();
					Set<Integer> players = endCluster.nodes.remove(game);
					if (players == null)
						continue;

					Set<Integer> startClusterPlayers = entry.getValue();
					for (Integer player : players)
					{
						startClusterPlayers.add(player);
						playerToClusterMap.put(player, startCluster);
					}

					if (result[game] == -1)
					{
						if (gamesToPlayerCount[game] == startClusterPlayers.size())
							result[game] = i;
					}
				}

				// Copying remaining games from second cluster
				for (Entry<Integer, Set<Integer>> entry : endCluster.nodes.entrySet())
				{
					Set<Integer> startClusterPlayers = startCluster.nodes.get(entry.getKey());
					if (startClusterPlayers == null)
					{
						startClusterPlayers = new HashSet<>();
						startCluster.nodes.put(entry.getKey(), startClusterPlayers);
					}

					Set<Integer> players = entry.getValue();
					for (Integer player : players)
					{
						startClusterPlayers.add(player);
						playerToClusterMap.put(player, startCluster);
					}
				}
			}
		}

		for (int j = 1; j < result.length; j++)
		{
			int v = result[j];
			if (v == -1 && gamesToPlayerCount[j] == 1)
				v = 0;
			System.out.println(v);
		}

		sc.close();
	}

	private static void mergeIfOneNull(int[] gamesToPlayerCount, int[] result, Map<Integer, Cluster> playerToClusterMap,
			int i, Cluster baseCluster, int newPlayer, int game)
	{
		Set<Integer> players = baseCluster.nodes.get(game);
		if(players == null)
		{
			players = new HashSet<>();
			baseCluster.nodes.put(game, players);
		}
		players.add(newPlayer);
		playerToClusterMap.put(newPlayer, baseCluster);
		
		if (result[game] == -1)
		{
			if (gamesToPlayerCount[game] == players.size())
			{
				if(gamesToPlayerCount[game] == 1)
					result[game] = 0;
				else
					result[game] = i;
			}
		}
	}

	private static Cluster createCluster(int player, Map<Integer, Cluster> playerToClusterMap, int[] playersToGame)
	{
		Cluster cluster = new Cluster();
		createPlayers(player, playerToClusterMap, playersToGame, cluster);
		return cluster;
	}

	private static void createPlayers(int player, Map<Integer, Cluster> playerToClusterMap, int[] playersToGame,
			Cluster cluster)
	{
		Set<Integer> players = new HashSet<>();
		players.add(player);
		cluster.nodes.put(playersToGame[player], players);
		playerToClusterMap.put(player, cluster);
	}

	private static class Cluster
	{
		// Game to players
		Map<Integer, Set<Integer>> nodes = new HashMap<>();

		@Override
		public String toString()
		{
			return "Cluster [nodes=" + nodes + "]";
		}
	}
}

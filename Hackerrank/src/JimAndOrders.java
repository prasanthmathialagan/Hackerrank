import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class JimAndOrders
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<Order> orders = new ArrayList<>();
		for (int i = 1; i <= N; i++)
			orders.add(new Order(i, sc.nextInt(), sc.nextInt()));
		Collections.sort(orders);
		for (Order order : orders)
			System.out.print(order.id + " ");
		sc.close();
	}
	
	private static class Order implements Comparable<Order>
	{
		int id;
		int endTime;
		
		public Order(int id, int startTime, int duration)
		{
			this.id = id;
			this.endTime = startTime + duration;
		}
		
		@Override
		public int compareTo(Order o)
		{
			return endTime - o.endTime;
		}
	}
}

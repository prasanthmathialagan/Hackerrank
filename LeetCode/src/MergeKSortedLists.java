import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists
{
	public static void main(String[] args)
	{

	}

	public static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}
	}

	public ListNode mergeKLists(ListNode[] lists)
	{
		if(lists == null || lists.length == 0)
			return null;
		
		int size = lists.length;
		PriorityQueue<ListNode> queue = new PriorityQueue<>(size, new Comparator<ListNode>()
		{
			@Override
			public int compare(ListNode o1, ListNode o2)
			{
				return Integer.compare(o1.val, o2.val);
			}
		});
		
		// Add the first element of each list to the queue
		for (ListNode ln : lists)
		{
			if(ln != null)
				queue.add(ln);
		}
		
		if(queue.isEmpty())
			return null;

		ListNode head = queue.poll();
		if(head.next != null)
			queue.add(head.next);
		
		ListNode prev = head;
		while(!queue.isEmpty())
		{
			ListNode e = queue.poll();
			if(e.next != null)
				queue.add(e.next);

			prev.next = e;
			prev = prev.next;
		}
		
		return head;
	}
}

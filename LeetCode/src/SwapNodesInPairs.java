
public class SwapNodesInPairs
{
	public static void main(String[] args)
	{
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		
		swapPairs(head);
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

	public static ListNode swapPairs(ListNode head)
	{
		// If there is only one node
		if(head == null || head.next == null)
			return head;
		
		ListNode newHead = head.next;

		ListNode prev = null;
		ListNode a = head;
		ListNode b = head.next;
		
		while(a != null && b != null)
		{
			if(prev != null)
				prev.next = b;
			
			a.next = b.next;
			b.next = a;
			prev = a;

			a = prev.next;
			b = a != null ? a.next : null;
		}
		
		return newHead;
	}
}

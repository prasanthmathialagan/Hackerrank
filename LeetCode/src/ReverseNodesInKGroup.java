public class ReverseNodesInKGroup
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

	public static ListNode reverseKGroup(ListNode head, int k)
	{
		int size = getSize(head);
		if(size < k)
			return head;
		
		int i = 0;
		ListNode prev = null;
		ListNode curr = head;
		while(i < k)
		{
			ListNode temp = curr.next;
			curr.next = prev;
			prev  = curr;
			curr = temp;
			i++;
		}
		
		ListNode newHead = prev;
		ListNode oldPrev = head;
		oldPrev.next = curr;
		
		while(size - i >= k)
		{
			prev = null;
			int j = 0;
			ListNode pPrev = curr;
			while(j < k)
			{
				ListNode temp = curr.next;
				curr.next = prev;
				prev  = curr;
				curr = temp;
				i++;
				j++;
			}
			
			pPrev.next = curr;
			oldPrev.next = prev;
			oldPrev = pPrev;
		}
		
		return newHead;
	}
	
	public static int getSize(ListNode head)
	{
		int size = 0;
		ListNode node = head;
		while(node != null)
		{
			size++;
			node = node.next;
		}
		return size;
	}
}

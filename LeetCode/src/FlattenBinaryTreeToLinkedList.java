
public class FlattenBinaryTreeToLinkedList
{

	public static void main(String[] args)
	{
		
	}

	public class TreeNode
	{
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x)
		{
			val = x;
		}
	}

	public void flatten(TreeNode root)
	{
		if(root == null)
			return ;
		
		flatten0(root);
	}
	
	public TreeNode flatten0(TreeNode node)
	{
		TreeNode left = node.left;
		TreeNode right = node.right;
		
		TreeNode tempNode = node;
		if(left != null)
		{
			TreeNode leftNode = flatten0(left);
			append(tempNode, leftNode);
			
			while(tempNode.right != null)
				tempNode = tempNode.right;
		}
		
		if(right != null)
		{
			TreeNode rightNode = flatten0(right);
			append(tempNode, rightNode);
		}
		
		return node;
	}

	private void append(TreeNode parent, TreeNode childNode)
	{
		parent.right = childNode;
		parent.left = null;
	}
}

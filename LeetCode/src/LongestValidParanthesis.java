import java.util.Stack;

public class LongestValidParanthesis
{
	public static void main(String[] args)
	{
		System.out.println(longestValidParentheses("()"));
	}

	public static int longestValidParentheses(String s)
	{
		if(s == null || s.length() < 2)
			return 0;
		
		int maxCount = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(c == '(')
			{
				stack.push(i);
			}
			else
			{
				stack.pop();

				if(!stack.isEmpty())
					maxCount = Math.max(maxCount, i - stack.peek());
				else
					stack.push(i);
			}
		}
		
		return maxCount;
	}
}

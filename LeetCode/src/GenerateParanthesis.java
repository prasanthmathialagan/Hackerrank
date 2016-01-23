import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis
{
	public static void main(String[] args)
	{
		System.out.println(generateParenthesis(0));
		System.out.println(generateParenthesis(1));
		System.out.println(generateParenthesis(2));
		System.out.println(generateParenthesis(3));
	}

	public static List<String> generateParenthesis(int n)
	{
		List<String> result = new ArrayList<String>();
		if(n == 0)
			return result;
		
		generateParanthesis(result, n, n, new StringBuilder());
		return result;
	}

	public static void generateParanthesis(List<String> result, int left, int right, StringBuilder sb)
	{
		if (left > right)
			return;

		if (left == 0 && right == 0)
		{
			result.add(sb.toString());
			return;
		}

		if (left > 0)
		{
			generateParanthesis(result, left - 1, right, sb.append("("));
			sb.deleteCharAt(sb.length() - 1);
		}

		if (right > 0)
		{
			generateParanthesis(result, left, right - 1, sb.append(")"));
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}

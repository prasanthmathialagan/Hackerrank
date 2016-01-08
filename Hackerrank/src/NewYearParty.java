import java.util.Scanner;

public class NewYearParty
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int currentTime = 0;
		while(N-- > 0)
		{
			int time = sc.nextInt();
			if(currentTime < time)
				currentTime = time;
			else
				currentTime++;
		}
		
		System.out.println(currentTime);
		
		sc.close();
	}
}

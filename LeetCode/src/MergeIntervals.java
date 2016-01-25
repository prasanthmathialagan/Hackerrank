import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author Prasanth
 *
 */
public class MergeIntervals
{
	public static void main(String[] args)
	{

	}

	public class Interval
	{
		int start;
		int end;

		Interval()
		{
			start = 0;
			end = 0;
		}

		Interval(int s, int e)
		{
			start = s;
			end = e;
		}
	}

	public List<Interval> merge(List<Interval> intervals)
	{
		List<Interval> results = new ArrayList<>();
		if (intervals == null || intervals.isEmpty())
			return results;
		
		Collections.sort(intervals, new Comparator<Interval>()
		{
			@Override
			public int compare(Interval o1, Interval o2)
			{
				return Integer.compare(o1.start, o2.start);
			}
		});
		
		int start = intervals.get(0).start;
		int end =  intervals.get(0).end;
		for (int i = 1; i < intervals.size(); i++)
		{
			Interval interval = intervals.get(i);
			if(interval.start <= end)
			{
				end = Math.max(end, interval.end);
			}
			else
			{
				results.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}
		
		results.add(new Interval(start, end));
		
		return results;
	}
}

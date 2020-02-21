package calculator.stats;

import java.util.ArrayList;
import java.util.*;

/**
 * Calculates the median value in a given set of data
 * @param <T> The type to be used in interpreting data. Must be a number of some variety.
 * @author Lucas Onwuchekwa
 */
public class Median<T> extends abstractStat
{
	/**
	 * An empty constructor calls super consturctor
	 */
	public Median()
	{
		super();
	}
	/**
	 * A constructor with an appropriate ArrayList parameter 
	 * that copies the data from the parameter to the data object within the class itself.
	 */
	public Median (ArrayList<T> list)
	{
		super(list);
	}
	
	@Override
	public ArrayList<Number> GetResult() throws RuntimeException 
	{
		//if null
		ArrayList<Number> result = new ArrayList<Number>();
		
		if (GetData().size() == 0)
		{ 
			RuntimeException e = new RuntimeException("data contains no element");
			throw e;
		}
		else 
		{
			Collections.sort(GetData());
			
			if (GetData().size() % 2 == 1)
			{
				result.add((Number) GetData().get((GetData().size()/2)));
				return result;
			}
			else
			{
				double x = ((Number)(GetData().get((GetData().size()/2)-1))).doubleValue();
				double y = ((Number)(GetData().get((GetData().size()/2)))).doubleValue();
			
				result.add((Number)((x+y)/2));
				return result;
			}
		}
	}
}




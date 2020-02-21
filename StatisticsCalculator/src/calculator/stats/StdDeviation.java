package calculator.stats;

import java.util.ArrayList;
import java.lang.Math;

/**
 * Calculates the standard deviation of a given set of data.
 * @param <T> The type to be used in interpreting data. Must be a number of some variety.
 * @author Lucas Onwuchekwa
 */
public class StdDeviation <T> extends abstractStat
{
	/**
	 * An empty constructor calls super consturctor
	 */
	public StdDeviation()
	{
		super();
	}
	
	/**
	 * A constructor with an appropriate ArrayList parameter 
	 * that copies the data from the parameter to the data object within the class itself.
	 * @param list
	 */
	protected StdDeviation (ArrayList<T> list)
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
			Number sum = 0;
			Average<Number> average = new Average(GetData());
			Number mean = (Number)(average.GetResult()).get(0);
			int x = 0;
			while(x<GetData().size())
			{
				sum = sum.doubleValue() + Math.pow((((Number)(GetData().get(x))).doubleValue() - mean.doubleValue()),2);
				x++;
			}
			Number std = Math.sqrt(sum.doubleValue()/((Number)(GetData().size()-1)).doubleValue());
			result.add(std);
		}
		return result;
	}
}

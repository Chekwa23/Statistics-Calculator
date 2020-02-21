package calculator.stats;

import java.util.ArrayList;

/**
 * Calculates the average value in a given set of data.
 * @param <T> The type to be used in interpreting data. Must be a number of some variety.
 * @author Lucas Onwuchekwa
 */
public class Average<T> extends abstractStat
{
	/**
	 * An empty constructor calls super consturctor
	 */
	public Average()
	{
		super();
	}
	/**
	 * A constructor with an appropriate ArrayList parameter 
	 * that copies the data from the parameter to the data object within the class itself.
	 */
	public Average (ArrayList<T> list)
	{
		super(list);
	}

	@Override
	public ArrayList<Number> GetResult() throws RuntimeException 
	{
		Number sum = 0;
		ArrayList<Number> result = new ArrayList<Number>();
				
		if (GetData().size() == 0)
		{
			RuntimeException e = new RuntimeException("data contains no element");
			throw e;
		}
		else
		{
			int x = 0;
			while (x < GetData().size())
			{
				sum = sum.doubleValue() + ((Number)(GetData().get(x))).doubleValue();
				x++;
			}
			Number average = sum.doubleValue()/GetData().size();
			result.add(average);
		}
		return result;
	}
}


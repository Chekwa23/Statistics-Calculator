package calculator.stats;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Calculates the maximum value in a given set of data
 * @param <T> The type to be used in interpreting data. Must be a number of some variety.
 * @author Lucas Onwuchekwa
 */
public class Maximum<T> extends abstractStat
{
	/**
	 * An empty constructor calls super consturctor
	 */
	public Maximum()
	{
		super();
	}
	/**
	 * A constructor with an appropriate ArrayList parameter 
	 * that copies the data from the parameter to the data object within the class itself.
	 */
	public Maximum (ArrayList<T> list)
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
			int i =GetData().size();
			Number j =  (Number) GetData().get(i-1);
			result.add(j);
		}
		return result;
	}
}

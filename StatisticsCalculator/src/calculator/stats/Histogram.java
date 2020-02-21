package calculator.stats;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Calculates the number of items in each bin of a Histogram and prints out the Histogram.
 * @param <T> The type to be used in interpreting data. Must be a number of some variety.
 * @author Lucas Onwuchekwa
 */
public class Histogram<T> extends abstractStat
{
	/**
	 * Number of equal size bins in the histogram
	 */
	private int numBins;
	/**
	 * The lowest value at which data is included in the lowest bin
	 */
	private Number minRange;
	/**
	 * The highest value at which data is included in the highest bin
	 */
	private Number maxRange;
	
	
	/**
	 * An empty constructor calls super consturctor
	 */
	public Histogram()
	{
		super();
	}
	/**
	 * A constructor with an appropriate ArrayList parameter 
	 * that copies the data from the parameter to the data object within the class itself.
	 * And initializes the instance variables
	 */
	public Histogram (ArrayList<T> list)
	{
		super(list);
		numBins = 10;
		maxRange = Double.POSITIVE_INFINITY;
		minRange = Double.NEGATIVE_INFINITY;
	}

	@Override
	public ArrayList<Number> GetResult() throws RuntimeException 
	{
		//if null
		ArrayList<Number> result = new ArrayList<Number>();

		if (GetMinRange().doubleValue()>GetMaxRange().doubleValue())
		{
			RuntimeException e = new RuntimeException("Histogram invalid");
			throw e;
		}
		if(GetData().size() == 0)
		{
			for(int i = 0; i < numBins; i++)
			{
				result.add(0);
			}
			return result;
		}
		ArrayList<Double> temp = new ArrayList<Double>();
		for (int i = 0; i < GetData().size(); i++)
		{
			if(((Number)(GetData().get(i))).doubleValue() >= minRange.doubleValue() && ((Number)(GetData().get(i))).doubleValue() <= maxRange.doubleValue())
			{
				temp.add(((Number)(GetData().get(i))).doubleValue());
			}
		}
		
		Collections.sort(temp);
//		Setting bin ranges
		double binWidth = (temp.get(temp.size()-1).doubleValue() - temp.get(0).doubleValue())/GetNumberBins().doubleValue();
		
//		Filling up the bins
		double x = temp.get(0).doubleValue();
		int num = 0;
		int bins = 0;
		for (int i = 0; i < temp.size(); i++)
		{
			double last;
			if(bins == numBins-1 )
			{
				last = temp.get(temp.size()-1);
				if(((Number)(temp.get(i))).doubleValue() >= x && ((Number)(temp.get(i))).doubleValue() < last)
				{
					num++;
				}
			}
			else
			{
				last = x + binWidth;
				if(((Number)(temp.get(i))).doubleValue() >= x && ((Number)(temp.get(i))).doubleValue() < last)
				{
					num++;
				}
			}

			if(i == temp.size()-1)
			{
				result.add(num);
				x += binWidth;
				num = 0;
				bins++;
				i = 0;
			}
			if(x >= (temp.get(temp.size()-1)).doubleValue())//7
			{
				break;
			}
		}
		
		
//		Print out the histogram
		double first = 0;
		double second = binWidth;
		System.out.println(result.toString());
		for (int i = 0; i < numBins; i++)
		{
			String temp2 = String.format("(%10.4f-->%10.4f)----->          |", first, second);
			System.out.print(temp2);
			for (int j = 0; j < (result.get(i)).doubleValue(); j++)
			{
				System.out.print("*");
			}
			System.out.println();
			first = second;
			second = second + binWidth;
		}
		return result;
	}
	
	/**
	 * Sets the number of equal size bins in the histogram. 
	 * This method is not called then the default value is 10.
	 * @param bins
	 */
	public void SetNumberBins(int bins)
	{
		numBins = bins;
	}
	
	/**
	 * Gets  the current number of bins set.
	 * @return numBins
	 */
	public Number GetNumberBins()
	{
		return numBins;
	}
	
	/**
	 * Sets the lowest value at which data is included in the lowest bin. 
	 * Data that is less than this value is not placed in any bin if GetResult is called. 
	 * The default isnegative infinity.
	 * @param min
	 */
	public void SetMinRange(Number min)
	{
		minRange = min;
	}
	
	/**
	 * Returns the current MinRange value.
	 * @return minRange
	 */
	public Number GetMinRange()
	{
		return minRange;
	}
	
	/**
	 * Sets the highest value at which data is included in the highest bin.
	 * Data that is greater or equal than this value is not placed in any bin if GetResult is called.
	 * The default is positive infinity
	 * @param max
	 */
	public void SetMaxRange(Number max)
	{
		maxRange = max;
	}
	
	/**
	 * Returns the current MaxRange value.
	 * @return maxRange
	 */
	public Number GetMaxRange()
	{
		return maxRange;
	}
}
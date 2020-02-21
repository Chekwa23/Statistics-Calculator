package calculator.stats;

import java.util.ArrayList;

/**
 * A class for all the duplicate methods and the helper methods, extending StatObject
 * @author Lucas Onwuchekwa
 * @param <T>
 */
public class abstractStat<T extends Number> implements StatObject<T>
{
	/**
	 * An arrayList of type T used to store the data passed into the class
	 */
	private ArrayList<T> list = new ArrayList<T>();
	
	/**
	 * The Description of the statObject
	 */
	private String description;
	
	/**
	 * A deep copy of the list of data past into the statObject
	 */
	private ArrayList<T> copyList = new ArrayList<T>();

	/**
	 * An empty constructor
	 */
	public abstractStat()
	{
		
	}

	/**
	 * A constructor with an appropriate ArrayList parameter that copies the data from the
	 *  parameter to the data object within the class itself
	 * @param list
	 */
	public abstractStat(ArrayList<T> list)
	{
		this.list = list;
	}
	
//--------------------------------------------------------------------------	
	@Override
	public void SetData(ArrayList<T> data)
	{
		ArrayList<T> temp = new ArrayList<T>();
		for (T x: list)
		{
			if (x != null)
			{
				temp.add(x);
			}
		}
		copyList = new ArrayList<T>(temp);
		ArrayList<T> temp2 = new ArrayList<T>();
		for (T x: data)
		{
			if (x != null)
			{
				temp2.add(x);
			}
		}
		list = new ArrayList<T>(temp2);
	}

	@Override
	public void SetDescription(String d)
	{
		description = d;
	}

	@Override
	public String GetDescription() 
	{
		return description;
	}

	@Override
	public ArrayList<Number> GetResult() throws RuntimeException
	{
		return null;
	}
	

	@Override
	public ArrayList<T> GetData() 
	{
		return list;
	}
}

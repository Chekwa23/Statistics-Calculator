package calculator.stats;

import java.util.ArrayList;

/**
 * Use to parse a string to a double of type number
 * @author Lucas Onwuchekwa
 */
public class parseClass implements IParser
{
	/**
	 * Empty Constructor
	 */
	public parseClass() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Number parse(String str) 
	{
	    return Double.valueOf(str);
	}

}



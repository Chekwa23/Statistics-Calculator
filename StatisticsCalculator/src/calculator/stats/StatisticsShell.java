package calculator.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import calculator.stats.Statistics.DATA;

/**
 * Class implementing the Statistics interface.
 * @author Lucas Onwuchekwa
 * @param <T>
 */
public class StatisticsShell<T extends Number> implements Statistics<T>
{
	private IParser<T> parser ;
	private ArrayList<StatObject> statList = new ArrayList<StatObject>();
	private ArrayList<String> rawData = new ArrayList<String>();
	private ArrayList<Number> data = new ArrayList<Number>();
	
	/**
	 * An empty constructor
	 */
	public StatisticsShell()
	{
		
	}
	/**
	 * Constructor setting the given parser to the IParser in this object.
	 * @param par
	 */
	public StatisticsShell(IParser<T> par)
	{
		parser =  par;
	}
	
	@Override
	public boolean ReadFile(String path, DATA d)
	{
		try 
		{
			ArrayList<ArrayList<String>> arrArr = new ArrayList<ArrayList<String>>();
			
			File file = new File(path);
			Scanner scan = new Scanner(file);
			
//			Splits the header line into an array of headings in each array position.
			String firstLine = scan.nextLine();
			String[] flList = firstLine.split("\\s++");
			int index = d.ordinal();
			
			
			while (scan.hasNextLine())
			{
				String nextLine = scan.nextLine();
				String[] arrString = nextLine.split("\\s+");
				ArrayList<String> temp = new ArrayList<String>(Arrays.asList(arrString));
				
				rawData.add(temp.get(index));
			}
			return true;
		} 
		catch (FileNotFoundException e) 
		{
			e = new FileNotFoundException();
			
			System.out.println("File does Not Exist");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void AddStatObject(StatObject<T> so) 
	{
		statList.add((abstractStat) so);
	}

	@Override
	public void AddStatObject(StatObject<T> so, int first, int last) 
	{
		ArrayList<Number> temp = new ArrayList<Number>();
		temp.addAll(data.subList(first, last));
		so.SetData((ArrayList<T>) temp);
		statList.add( so);
	}
	
	@Override
	public StatObject<T> GetStatObject(int i) 
	{
		return statList.get(i);
	}

	@Override
	public StatObject RemoveStatObject(int i) 
	{
		StatObject removed = statList.get(i);
		statList.remove(i);
		return removed;
	}

	@Override
	public int Count() 
	{
		return statList.size();
	}

	@Override
	public ArrayList<T> GetDataSet() 
	{
		return (ArrayList<T>) data;
	}

	@Override
	public ArrayList<ArrayList<Number>> MapCar() 
	{
		ArrayList<ArrayList<Number>> Result = new ArrayList<ArrayList<Number>>();
		
		for (int i = 0; i < statList.size(); i++)
		{
			Result.add(statList.get(i).GetResult());
		}
	
		return Result;
	}
	
	/*
	 * Helper method to set the data and parse it 
	 * from rawdata of type string to data of type double.
	 */
	public void SetDataSet()
	{
		data.clear();
		for (int i = 0; i < rawData.size(); i++)
		{
			boolean temp = true;
			try 
		    {
				String c = rawData.get(i);
				
		    	Double.parseDouble(c);
			    temp = true;
		    }
		    catch( Exception e ) 
		    {
		        temp = false;
		    }
			if (temp == true)
			{
				data.add(parser.parse(rawData.get(i)));
			}
			else {}
		}
	}
	
	/*
	 * Helper method to get the raw data of type string
	 * @return rawData
	 */
	public ArrayList<String> getRawData()
	{
		return rawData;
	}
	
	/*
	 * Helper method to set the raw data used
	 * @param raw
	 */
	public void setRawData(ArrayList<String> raw)
	{
		rawData.clear();
		rawData.addAll(raw);
	}
}

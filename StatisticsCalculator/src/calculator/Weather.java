package calculator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import calculator.stats.*;
import calculator.stats.Statistics.DATA;	

/**
 * Reads a data file given as the first argument on the command line. 
 * And outputs the required information
 * The monthly high temperature and the monthly low temperature for each month in the data.
 * The average temperature for each day.
 * The median temperature for each day.
 * A Histogram of high temperatures for each day with 15 bins ranging from -40 to 110 degrees.
 * @author lucaso
 */
public class Weather 
{
 	public static void main(String [] args) throws FileNotFoundException
 	{
 		String c = args[0];
// 		String c = "C:\\Users\\onwuc\\Eclipse2019\\228Assignment1\\src\\data.txt"; 		
 		parseClass test = new parseClass();
 		StatisticsShell temp = new StatisticsShell(test);
		temp.ReadFile(c, DATA.TEMP);
//		temp.SetDataSet();
		
//		To get the range first and last index of every month 
		StatisticsShell month = new StatisticsShell(test);
		ArrayList<ArrayList<Integer>> arrArrMonth = new ArrayList<ArrayList<Integer>>();
		month.ReadFile(c, DATA.YR_MO_DA_HR_MN);
//		month.SetDataSet();
		double x =  (double) month.GetDataSet().get(0);
		int initialMonth = 0;
		int finalMonth = 0;
		for(int i = 0; i < (month.GetDataSet()).size(); i++)
		{
			if(Math.floor(x/1000000) != Math.floor(((double) month.GetDataSet().get(i))/1000000) || i == (month.GetDataSet()).size()-1)
			{
				ArrayList<Integer> arrMonth = new ArrayList<Integer>();
				if(i != (month.GetDataSet()).size()-1)
				{
					finalMonth = i-1;
				}
				else
				{
					finalMonth = i;
				}

				arrMonth.add(initialMonth);
				if(finalMonth > initialMonth)
				{
					arrMonth.add(finalMonth);
				}
				else{}
				
				arrArrMonth.add(arrMonth);
				if(i != (month.GetDataSet()).size()-1)
				{
					initialMonth = finalMonth+1;
				}
				else
				{
					
				}
				
				x = (double) month.GetDataSet().get(initialMonth);
			}
			else 
			{
				finalMonth = i;
			}
		}
		
		
//		To get the range first and last index of every month 
		StatisticsShell day = new StatisticsShell(test);
		ArrayList<ArrayList<Integer>> arrArrDay = new ArrayList<ArrayList<Integer>>();
		day.ReadFile(c, DATA.YR_MO_DA_HR_MN);
//		day.SetDataSet();
		double y =  (double) day.GetDataSet().get(0);
		int initialDay = 0;
		int finalDay = 0;
		for(int i = 0; i < (day.GetDataSet()).size(); i++)
		{
			if(Math.floor(y/10000) != Math.floor(((double) day.GetDataSet().get(i))/10000) || i == (month.GetDataSet()).size()-1)
			{
				ArrayList<Integer> arrDay = new ArrayList<Integer>();
				if(i != (day.GetDataSet()).size()-1)
				{
					finalDay = i-1;
				}
				else
				{
					finalDay = i;
				}

				arrDay.add(initialDay);
				if(finalDay > initialDay)
				{
					arrDay.add(finalDay);
				}
				else{}
	
				arrArrDay.add(arrDay);
				if(i != (day.GetDataSet()).size()-1)
				{
					initialDay = finalDay+1;
				}
				else{}
				
				y = (double) day.GetDataSet().get(initialDay);
			}
			else 
			{
				finalDay = i;
			}
		}
		
	
//		-----------------------------------------------------------------------------------------------------------------------
//		Monthly High and Low temperature
		for (int whatMonth = 0; whatMonth < arrArrMonth.size(); whatMonth++)
		{
			StatisticsShell temp2 = new StatisticsShell(test);
			temp2.ReadFile(c, DATA.TEMP);
//			temp2.SetDataSet();
			int startForMonth = (arrArrMonth.get(whatMonth)).get(0);
			int endForMonth = (arrArrMonth.get(whatMonth)).get(1);
			ArrayList<String> subRaw = new ArrayList<String>(temp2.getRawData().subList(startForMonth, endForMonth+1));
			temp2.setRawData(subRaw);
			temp2.SetDataSet();
			ArrayList<Number> oneMonth = new ArrayList<Number>(temp2.GetDataSet());
			Maximum max = new Maximum(oneMonth);
			Minimum min = new Minimum(oneMonth);
			int a = whatMonth + 1;
			if(a/12 != 0)
			{
				
			}
	 		System.out.println("The High temperature for month " + a + " is = " + (max.GetResult()).get(0));
	 		System.out.println("The Low temperature for month " + a + " is = " + (min.GetResult()).get(0) + "\n");
		}

		
//		-----------------------------------------------------------------------------------------------------------------------
//		Daily Average and Median temperature
		ArrayList<Number> highTemperatures = new ArrayList<Number>();
		
		for (int whatDay = 0; whatDay < arrArrDay.size(); whatDay++)
		{
			StatisticsShell temp2 = new StatisticsShell(test);
			temp2.ReadFile(c, DATA.TEMP);
//			temp2.SetDataSet();

			int startForDay = (arrArrDay.get(whatDay)).get(0);
			int endForDay = (arrArrDay.get(whatDay)).get(1);
			
			ArrayList<String> subRaw = new ArrayList<String>(temp2.getRawData().subList(startForDay, endForDay+1));
			temp2.setRawData(subRaw);
			temp2.SetDataSet();
			ArrayList<Number> oneDay = new ArrayList<Number>(temp2.GetDataSet());
			Average mean = new Average(oneDay);
			Median middle = new Median(oneDay);
			Maximum max2 = new Maximum(oneDay);
			highTemperatures.add((Number) (max2.GetResult()).get(0));
			int a = whatDay + 1;
	 		System.out.println("The Average temperature for day " + a + " is = " + (mean.GetResult()).get(0));
	 		System.out.println("The Median temperature for day " + a + " is = " + (middle.GetResult()).get(0) + "\n");
		}
		
		System.out.println(highTemperatures.toString());
	
//		-----------------------------------------------------------------------------------------------------------------------
//		Histogram of High temperatures for each day with 15 bins ranging from -40 to 110 degrees	
 		Histogram hist = new Histogram(highTemperatures);
 		hist.SetNumberBins(15);
 		hist.SetMinRange(-40);
 		hist.SetMaxRange(110);
 		hist.GetResult();
 		
 	}
}




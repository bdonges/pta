package bill.file;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class Test 
{

	public static void main(String[] args)
	{
		try
		{
			System.out.println("started reading file at " + new java.util.Date());
			
			// instantiate inbound data file
			File infile = new File("C:\\WWW\\samash.csv");
			
			// instantiate outboud data file (the one to be bulk imported into sql server
			File outfile = new File("C:\\WWW\\samash_pipe.csv");
			
			/*
			read each line.  for each line 
			
			1.  replace "," with "|"
			2.  add carraige return 
			3.  write it to a string buffer
			
			every 10000 rows write the string buffer to a file and clear the buffer
			*/
			LineIterator it = FileUtils.lineIterator(infile, "UTF-8");
			int i = 0;
			try 
			{
				StringBuffer sb = new StringBuffer();
				while (it.hasNext()) 
				{
					String line = it.nextLine();
					sb.append(line.replace("\",\"", "\"|\"")  + "\n\r");

					i++;
					
					if (i % 10000 == 0)
					{
						FileUtils.writeStringToFile(outfile, sb.toString(), true);
						sb.delete(0, sb.length());
					}
				}
				
				// if sb wasn't written to file (i % 10000 == 0) didn't allow it
				// write the remainder
				if (sb.length() > 0)
				{
					FileUtils.writeStringToFile(outfile, sb.toString(), true);
					sb.delete(0, sb.length());
				}
					
			} 
			finally 
			{
				LineIterator.closeQuietly(it);
			}
			System.out.println("finished reading file at " + new java.util.Date());			
			System.out.println("number of lines processed: " + i);
			
		}
		catch (Exception e)
		{
			System.err.println("exception - message: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}

package fortiss.alltool;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

/**
 * Class to show source file
 * @author: Md Shifuddin Al Masud(shifuddin.gmail.com)
 * */
public class SourceFileBuild
{
	private String filePath;
	private List<String> sourceFile;
	private List<String> filteredSourceFile;
	private String sourceFileError ="none";
	private String numberOfline;
	private int start = 0;
		public SourceFileBuild()
		{
			
		}
		/**
		 * Return the content of the source file
		* */
		public List<String> getSource(String filePath)
		{
		
			sourceFile = new ArrayList<String>();
			
			
			try 
			{	
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				String line;
				while ((line = br.readLine()) != null) 
				{
						
					sourceFile.add(line);
				}	 
				
			}
			catch(Exception e)
			{
				sourceFileError = e.toString();
			}	
			return sourceFile;
			
		}
		
		public int getStartPoint()
		{	
			return start;
		}
		
		public String getSourceFileError()
		{
			return sourceFileError;
		}
}

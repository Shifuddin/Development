package fortiss.alltool;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.text.Collator;

/**
 * Class to create report
 * @author: Md Shifuddin Al Masud(shifuddin.gmail.com)
 * */
public class ReportParser
{
		private String MainBaseDir;
		private List<List<String>> table;
		private List<List<List<String>>> table2;
		private List<String> line;
		private String error = "";
		private List<String> lDir;
		private List<String> lFiles;
		
		//
		// Constructor
		// @param project workspace directory
		//
		public ReportParser(String MainBaseDir)
		{
			this.MainBaseDir = MainBaseDir;
			lDir = new ArrayList<String>();
			getDirectories(MainBaseDir + "/TEMP");
		}
		
		//
		//Get subdirectories from a directory
		//
		private void getDirectories(String MainBaseDir)
		{
			File file = new File(MainBaseDir);
			String[] directories = file.list(new FilenameFilter() {
		  
				@Override
				public boolean accept(File current, String name) {
					return new File(current, name).isDirectory();
				}
				});
		
				for(String str:directories)
				{
					lDir.add(MainBaseDir+"//"+str);
				}

		
		}
		public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder( fileEntry);
	        } else {
	        	lFiles.add(fileEntry.getAbsolutePath());
	        }
	    }
	}
	//
	  // Parse Merged Josn file
	 //
	private void parseMerged(String filePath)
	{
		
		JSONParser parser = new JSONParser();

       try {
           Object ToolsErrorsObj = parser.parse(new FileReader(filePath));   
           JSONArray ToolsErrors = (JSONArray) ToolsErrorsObj;

           Iterator<JSONObject> iterator = ToolsErrors.iterator();
           
           for (int i = 0 ; iterator.hasNext() ; ++i )
           {
        	   line = new ArrayList<String>();
				JSONObject Error = iterator.next();

               String type = (String) Error.get("type");
               String file = (String) Error.get("file");
               String lineStr = "";
				try
				{
					Long line = (Long) Error.get("line");
					lineStr = String.valueOf(line);
				}
				catch(Exception e)
				{
					lineStr = "none";
				}

               String procedure = (String) Error.get("procedure");

               String qualifier = (String) Error.get("qualifier");
               String variable = (String) Error.get("variable");
               
               line.add(type);
               line.add(file);
               line.add(lineStr);
               line.add(qualifier);
               line.add(procedure);
			  
			   
               table.add(line);
			   
           }

       } catch (FileNotFoundException e) {

           e.printStackTrace();

       } catch (IOException e) {

           e.printStackTrace();

       } catch (ParseException e) {

           e.printStackTrace();

       }
		}
	
	  // Parse Refined Josn file
	 
	private void parseRefined(String filePath)
	{
		
		JSONParser parser = new JSONParser();

       try {
           Object ToolsErrorsObj = parser.parse(new FileReader(filePath));   
           JSONArray ToolsErrors = (JSONArray) ToolsErrorsObj;

           Iterator<JSONObject> iterator = ToolsErrors.iterator();
          
           for (int i = 0 ; iterator.hasNext() ; ++i )
           {
        	   line = new ArrayList<String>();
				JSONObject Error = iterator.next();
			
			   String severity = (String) Error.get("severity");
			   String variable = (String) Error.get("variable");
               String type = (String) Error.get("type");
               String file = (String) Error.get("file");
               String lineStr = "";
				try
				{
					Long line = (Long) Error.get("line");
					lineStr = String.valueOf(line);
				}
				catch(Exception e)
				{
					lineStr = "none";
				}
				
				String statusStr = "";
				try
				{
					Long status = (Long) Error.get("status");
					statusStr = String.valueOf(status);
				}
				catch(Exception e)
				{
					statusStr = "none";
				}
				

               String procedure = (String) Error.get("procedure");

               String qualifier0 = (String) Error.get("qualifier");
               
               line.add(type);
               line.add(file);
               line.add(lineStr);
               line.add(qualifier0);
               line.add(procedure);
               line.add(severity);
               line.add(statusStr);              
               table.add(line);
			   
           }

       } catch (FileNotFoundException e) {

           e.printStackTrace();

       } catch (IOException e) {

           e.printStackTrace();

       } catch (ParseException e) {

           e.printStackTrace();

       }
		}
	
	//
	  // Parse Tools Josn file
	 //
	private void parseAllTools(String filePath)
	{
	
       try {
           JSONParser parser = new JSONParser();     
	       Object ToolsErrorsObj = parser.parse(new FileReader(filePath));   
	       JSONArray ToolsErrors = (JSONArray) ToolsErrorsObj;

	       for(Object obj:ToolsErrors)
	       {
			
			  line = new ArrayList<String>();
	       	  JSONObject object = (JSONObject)obj;
	       	  String toolname = (String)object.get("tool");
	       	  
	       	  JSONArray array = (JSONArray)object.get("errors");
	       	
			  toolname = toolname + " 9endTableT";
			  line.add(toolname);
			  table.add(line);
			 
	       	  for(Object obj1:array)
	       	  {
				  line = new ArrayList<String>();
	       		  JSONObject objectmy = (JSONObject)obj1;
	       		  String lineStr = "";
				  try
				  {
						Long line = (Long) objectmy.get("line");
						lineStr = String.valueOf(line);
				  }
				  catch(Exception e)
				  {
					 lineStr = "none";
				  }
				  line.add((String)objectmy.get("type"));
				  line.add((String)objectmy.get("file"));
				  line.add(lineStr);
	       		  line.add((String)objectmy.get("qualifier"));
	       		  line.add((String)objectmy.get("procedure"));
	       		  
	       		  table.add(line);
	       		  
	       	  }
	       	  table2.add(table);
	       	  table = new ArrayList<List<String>>();
	       	  
	        }

       } catch (FileNotFoundException e) {

           e.printStackTrace();

       } catch (IOException e) {

           e.printStackTrace();

       } catch (ParseException e) {

           e.printStackTrace();

       }
		}

	//
	 // Get name of the table from filename
	 //
		public String getTableName(String fileName)
		{
			
			if (fileName.lastIndexOf("\\") == 0)
			{
				fileName = fileName.substring(fileName.lastIndexOf("\\")+1).toUpperCase();
				
				if (fileName.contains("TOOLSERRORS"))
					return "Tools Report";
				else if (fileName.contains("REFINEDERRORS"))
					return "Refined Report";
				else if (fileName.contains("MERGEDERRORS"))
					return "Merged Report";
				return "Undefined";
			}
			else
			{
				fileName = fileName.substring(fileName.lastIndexOf("/")+1).toUpperCase();
				if (fileName.contains("TOOLSERRORS"))
					return "Tools Report";
				else if (fileName.contains("REFINEDERRORS"))
					return "Refined Report";
				else if (fileName.contains("MERGEDERRORS"))
					return "Merged Report";
				return "Undefined";
			}
				
			
			
		}
		//
		// Get name of the small table like infer cppcheck from table
		//
		public String getSmallTableName( List<List<String>> table)
		{
			for (List<String> line:table)
			{
					for (String word:line)
						{
								if (word.contains("infer 9endTableT"))
								{
									return "Infer Report";
								}
								if (word.contains("cppcheck 9endTableT"))
								{
									return "Cppcheck Report";
								}		
						}
			}
			return "undefined";
		}
		//
		// Parse result from a file
		//
		public List<List<String>> getResult(String file)
		{
			table = new ArrayList<List<String>>();
			if(getTableName(file).contains("Merged Report"))
			{
				parseMerged(file);
			}
			else if (getTableName(file).contains("Refined Report"))
			{
				parseRefined(file);
			}
			else if (getTableName(file).contains("Tools Report"))
			{
					parseAllTools(file);
			}
			return table;
		}
		//
		// Parse result from a toolserror file
		//
		public List<List<List<String>>> getResultTools(String file)
		{
			table = new ArrayList<List<String>>();
			table2 = new ArrayList<List<List<String>>>();
			
			if (getTableName(file).contains("Tools Report"))
			{
					parseAllTools(file);
			}
			return table2;
		}
		//
		// Perform logical merge
		//
		public List<List<String>> getLogicalMerge(List<List<List<String>>> table)
		{
			List <List<String>> list1 = table.get(0);
			List <List<String>> list2 = table.get(1);
			List<Integer> list = new ArrayList<Integer>();
			StringBuilder sb = new StringBuilder();
			try
			{
			for (int i = 1; i < list1.size(); i++)
			{
				String lnumber = list1.get(i).get(2).toString();
				
				if (!lnumber.contains("null"))
					{
						sb.append(lnumber);
						sb.append(" ");
						int value = Integer.parseInt(lnumber);
						list.add(--value);
						list.add(++value);
						list.add(++value); 
					}
			}
			for (int i = 1; i < list2.size(); i++)
			{
				String lnumber = list2.get(i).get(2).toString();
				
				if (!lnumber.contains("null"))
					{
						sb.append(lnumber);
						sb.append(" ");
						int value = Integer.parseInt(lnumber);
						list.add(--value);
						list.add(++value);
						list.add(++value);
					}
			}
			// add elements to al, including duplicates
			Set<Integer> hs = new HashSet<>();
			hs.addAll(list);
			list.clear();
			list.addAll(hs);
			Collections.sort(list);
			List <String> procedure = new ArrayList<String>();
			List<List<String>> newtable = new ArrayList<List<String>> ();
			for (int i = 0; i < list.size(); i++)
			{
				int j, k;
				String procedure1 = null;
				String procedure2 = null;
				List <String> line = new ArrayList<String>();
				
					for (j = 1; j < list1.size(); j++)
					{
						if (String.valueOf(list.get(i)).equals(list1.get(j).get(2)))
						{
							procedure1 = list1.get(j).get(4);
							break;
						}
					}
					
					for (k = 1; k < list2.size(); k++)
					{
						if (String.valueOf(list.get(i)).equals(list2.get(k).get(2)))
						{
							procedure2 = list2.get(k).get(4);
							break;
						}
					}
					
					if (procedure1 == null && procedure2 == null)
					{
					
					}
					else if (procedure1 == null && procedure2 != null)
					{
						line.add(list2.get(k).get(0));
						line.add(list2.get(k).get(1));
						line.add(list2.get(k).get(2));
						line.add(list2.get(k).get(3));
						line.add(list2.get(k).get(4));
					}
					else if (procedure2 == null && procedure1 != null)
					{
						line.add(list1.get(j).get(0));
						line.add(list1.get(j).get(1));
						line.add(list1.get(j).get(2));
						line.add(list1.get(j).get(3));
						line.add(list1.get(j).get(4));

					}
					else if (procedure1 != null && procedure2 != null)
					{
						if (!procedure1.equals(procedure2))
						{
							line.add("cppcheck: " + list1.get(j).get(0) + "infer: " +list2.get(k).get(0));
							line.add(list1.get(j).get(1));
							line.add(list1.get(j).get(2));
							line.add("cppcheck: " + list1.get(j).get(3) + "infer: " +list2.get(k).get(3));
							line.add("cppcheck: " + list1.get(j).get(4) + "infer: " +list2.get(k).get(4));
						}
						else
						{
							line.add("cppcheck: " + list1.get(j).get(0) + "infer: " +list2.get(k).get(0));
							line.add(list1.get(j).get(1));
							line.add(list1.get(j).get(2));
							line.add("cppcheck: " + list1.get(j).get(3) + "infer: " +list2.get(k).get(3));
							line.add("cppcheck: " + list1.get(j).get(4) + "infer: " +list2.get(k).get(4) +" Waring!!!!! Two different procedures");
						}
					}
					newtable.add(line);
					
			}
			return newtable;
		}
		catch(Exception e)
		{
			return null;
		}
		
		}
		public String getError()
		{
				return error;
		}
		public List<String> getDirectories()
		{
			java.util.Collections.sort(lDir, Collator.getInstance());
			return lDir;
		}
		public List<String> getFiles(String directory)
		{
			lFiles= new ArrayList<String>();
			final File folder = new File(directory);
			listFilesForFolder(folder);			
			return lFiles;
		}
		public String getConfigName(String str)
		{
			str = str.substring(str.lastIndexOf("//")+2).toUpperCase();
			String number = str.substring(6);
			return "Configuration " + number;
		}
		
		
}

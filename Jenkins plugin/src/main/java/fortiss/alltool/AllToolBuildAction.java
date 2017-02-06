package fortiss.alltool;

import hudson.model.AbstractBuild;
import hudson.model.Action;
import java.util.*;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import java.util.Arrays;
import java.io.IOException;

/**
 * BuildAction class
 * @author: Md Shifuddin Al Masud
 */
public class AllToolBuildAction implements Action {

    private String message;
    private AbstractBuild<?, ?> build;
	
	public String myLink="";
	ReportParser rp;
	SourceFileBuild mySourceBuild;
	List<String> sourceInfo;
	private String reportDir;
	private String source;
	private String workflow;
	private String configuration;
	
    @Override
    public String getIconFileName() {
        return "/plugin/alltool/img/project_icon.PNG";
    }

    @Override
    public String getDisplayName() {
        return "autoanalyse result";
    }

    @Override
    public String getUrlName() {
        return "build_page";
    }

    public String getMessage() {
        return this.message;
    }

	//
	 // Get current build number
	 // @return int
	 //
    public int getBuildNumber() {
        return this.build.number;
    }
    //
	 // Get configuration file name
	 // @return string
	 //
    public String getConfigFileName()
    {
		String str = "";
		try
		{
			str =System.getenv("ENGINE_DIR").toString();
			return str;
		}
		catch(Exception e)
		{
			str = e.getMessage();
			return str;
		}
		
	}

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }
    //
	 // Get result from a directory
	 // First find files in a directory
	 // Then extract information from those files
	 // @param String directory
	 // @return list<list<string>>
	 //
    public List<List<String>> getResult(String directory)
    {
			return rp.getResult(directory);
	}
	
	 //
	 // Get result from a directory
	 // First find files in a directory
	 // Then extract information from those files
	 // Only applicable to toolserror json file
	 // @param string
	 // @return list<list<list<string>>>
	 //
	public List<List<List<String>>> getResultTools(String directory)
    {
			return rp.getResultTools(directory);
	}
	 //
	  // Get subdirectories from a direcory
	  // @return list
	 //
    public List<String> getDirectories()
    {
    	return rp.getDirectories();
    }
    //
	  // Get files from a subdirecory
	  // @return List
	 //
    public List<String> getFiles(String directory)
    {
    	return rp.getFiles(directory);
    }
    public String getConfigName(String str)
	{
		return rp.getConfigName(str);
	}
	//
	  // Get name of the table from a file
	  // @param string
	  // @return string
	 //
    public String getTableName(String fileName)
	{
		return rp.getTableName(fileName);
	}
    public String getTextLine(String line)
    {
    	String textLine = "";
    	String[] mystr = line.split(",");
		int i =0;
		for(String str1:mystr)
		{
			if ( i == 0)
				textLine = textLine + " " + str1.substring(1);
			else if (i == mystr.length -1)
				textLine = textLine + " " + str1.substring(0,str1.length()-1);
			else
				textLine = textLine + " " + str1;
			
			i++;
		}
		if(i>0)
			return textLine;
		else
			return line;
    }
	
    /**
     * Gets the dynamic result of the selection element.
     *
     * @param link     the link to identify the sub page to show
     * @param request  Stapler request
     * @param response Stapler response
     * @return the dynamic result of the analysis (detail page).
     * @throws java.io.IOException if an error occurs
     */
    public Object getDynamic(final String link, final StaplerRequest request,
            final StaplerResponse response) throws IOException {
				myLink = link;
				sourceInfo = Arrays.asList(myLink.split(","));
                response.sendRedirect2("sourfileviewbuild");
             
        return null;
    }
    public String getFileName()
    {
		return sourceInfo.get(1).substring(1);
	}
    public String getLineNumber()
    {
		return sourceInfo.get(2).trim();
	}
	//
	  // Return source file content
	  // @return list
	 //
	public List<String> getSource()
	{
		return mySourceBuild.getSource(getMessage()+"//source//"+getFileName());
	}
	//
	  // Returns 1
	  // @return int
	 //
	public int getStartPoint()
	{
		return 1;
	}
	public String getType(List<String> result)
	{
		return result.get(0);
	}
	public String getFile(List<String> result)
	{
		return result.get(1);
	}
	public String getLine(List<String> result)
	{
		return result.get(2);
	}
	public String getQualifier(List<String> result)
	{
		return result.get(3);
	}
	public String getProcedure(List<String> result)
	{
		return result.get(4);
	}
	public String getStatus(List<String> result)
	{
		try
		{
			String status = result.get(6);
			if (status != null)
				return status;
			else
				return "x1k2?.";
		}
		catch (Exception e)
		{
			return "x1k2?.";
		}
		
	}
	public String getSmallTableName( List<List<String>> table)
	{
		return rp.getSmallTableName(table);
	}
	
	//
	  // Get logical merge from two table infer and cppcheck
	 //
	public List<List<String>> getLogicalMerge(List<List<List<String>>> table)
	{
		return rp.getLogicalMerge(table);
	}
	//
	  // Constructor
	  // @param AbstractBuild, path of workspace
	 //
    AllToolBuildAction(final AbstractBuild<?, ?> build,final String workspace)
    {
        this.build = build;
        this.message = workspace;
        
        /*
        this.source = source;
        this. workflow = "\"" + workflow + "\"";
        this.configuration = configuration;
        */
        
        rp = new ReportParser(workspace);   
        mySourceBuild = new SourceFileBuild();
    }
}

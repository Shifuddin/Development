package org.fml.startuplog.survey;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.fml.startuplog.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SurveyController {
 
	@Autowired  
	SurveyService surveryService;
	
	/**
	 * Any intention to load stages without
	 * invoking indicators will lead to redirecting
	 * indicators.jsp
	 * Get method
	 * @return indicators.jsp*/
	@RequestMapping(value = "/stages", method = RequestMethod.GET)
	public String showSurveyQuestions() { 
		return "redirect:indicators";
	}  
	
	/**
	 * Calculate stage from answers of participant on 
	 * indicators
	 * POST method
	 * @return stages.jsp*/
	@RequestMapping(value = "/stages", method = RequestMethod.POST)
	public String showSurveyResult(HttpServletRequest request, ModelMap model ) { 
		
	
		int[] suggestedStages = surveryService.phase(request);
		for(int i = 0; i < suggestedStages.length; i++)
		{
			if(suggestedStages[i] == 1)
			{
				model.put("stage"+i, "suggestion");
			}
			
			
		}
		return  "stages"; 		
	}
	
	/**
	 * Triggered when pre-seed stage selected
	 * @return function.jsp
	 * */ 
	@RequestMapping(value="/function",params="pre-seed",method=RequestMethod.POST)
    public String pre_seed(ModelMap model)
    {
		try {
			surveryService.saveStage("Pre-seed");
			model.put("stage", "pre-seed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Triggered when seed stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="seed",method=RequestMethod.POST)
    public String seed(ModelMap model)
    {
		try {
			surveryService.saveStage("seed");
			model.put("stage", "seed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Triggered when Startup:Conception stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="startup1",method=RequestMethod.POST)
    public String startup1(ModelMap model)
    {
		try {
			surveryService.saveStage("Startup:Conception");
			model.put("stage", "startup:conception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Triggered when Startup:Construction stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="startup2",method=RequestMethod.POST)
    public String startup2(ModelMap model)
    {
		try {
			surveryService.saveStage("Startup:Construction");
			model.put("stage", "startup:construction");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	
	/**
	 * Triggered when Startup:Elaboration stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="startup3",method=RequestMethod.POST)
    public String startup3(ModelMap model)
    {
		try {
			surveryService.saveStage("Startup:Elaboration");
			model.put("stage", "startup:elaboration");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Triggered when Startup:Prototyping stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="startup4",method=RequestMethod.POST)
    public String startup4(ModelMap model)
    {
		try {
			surveryService.saveStage("Startup:Prototyping");
			model.put("stage", "startup:prototyping");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	
	/**
	 * Triggered when First stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="first",method=RequestMethod.POST)
    public String first(ModelMap model)
    {
		try {
			surveryService.saveStage("First Stage");
			model.put("stage", "first");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Triggered when Second stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="second",method=RequestMethod.POST)
    public String second(ModelMap model)
    {
		try {
			surveryService.saveStage("Second Stage");
			model.put("stage", "second");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Triggered when Third stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="third",method=RequestMethod.POST)
    public String third(ModelMap model)
    {
		try {
			surveryService.saveStage("Third Stage");
			model.put("stage", "third");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Triggered when Forth stage selected
	 * @return function.jsp 
	 * */
	@RequestMapping(value="/function",params="forth",method=RequestMethod.POST)
    public String forth(ModelMap model)
    {
		try {
			surveryService.saveStage("Forth Stage");
			model.put("stage", "forth");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "function";
    }
	/**
	 * Any intention to load function page directly
	 * without invoking indicators will lead to redirecting 
	 * indicator page again
	 * GET method
	 * @return indicators.jsp 
	 * */
	@RequestMapping(value = "/function", method = RequestMethod.GET)
	public String loadIndicators() throws Exception {  
		return "redirect:indicators";
	}
	/**
	 * Display UI to select options to
	 * load previously calculated stages
	 * GET method
	 * @return previousphase.jsp 
	 * */
	@RequestMapping(value = "/previous-phase", method = RequestMethod.GET)
	public String previousPhase(ModelMap model) throws Exception { 
		
		Option option = new Option();
		model.put("option", option);
		
		List<String> optionList = new ArrayList<String>();
		optionList.add("Previous Phase");
		optionList.add("Last 2 Phase");
		optionList.add("Last 5 Phase");
		model.put("optionList", optionList);
		return "previousphase";
	} 
	/**
	 * Load previously calculated stages
	 * POST method
	 * @return previousphase.jsp  
	 * */
	@RequestMapping(value = "/previous-phase", method = RequestMethod.POST)
	public String previousPhasePost(Option option, ModelMap model) throws Exception { 
		
		if (option.getSelection().equals("Previous Phase"))
		model.addAttribute("list",surveryService.currentStage(1));
		else if (option.getSelection().equals("Last 2 Phase"))
			model.addAttribute("list",surveryService.currentStage(2));	
		else if (option.getSelection().equals("Last 5 Phase"))
			model.addAttribute("list",surveryService.currentStage(5));
		model = initiateModel(model);
		return "previousphase";
	}
	/**
	 * Populate jsp checkboxes from java code
	 * @return ModelMap 
	 * */
	private ModelMap initiateModel(ModelMap model)
	{
		Option option = new Option();
		model.put("option", option);
		
		List<String> optionList = new ArrayList<String>();
		optionList.add("Previous Phase");
		optionList.add("Last 2 Phase");
		optionList.add("Last 5 Phase");
		model.put("optionList", optionList);
		return model;
	}
}

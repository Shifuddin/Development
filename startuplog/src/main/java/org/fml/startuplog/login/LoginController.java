package org.fml.startuplog.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	/**
	 * Show indicators
	 * GET method
	 * @return indicators.jsp*/
	@RequestMapping(value = "/indicators", method = RequestMethod.GET)
	public String showIndicators(){ 
		return "indicators";
	}
	/**
	 * Show login from
	 * GET method
	 * @return login.jsp*/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){ 
		
		return "login";
	}
	/**
	 * Validate user credentials and
	 * load indicators
	 * POST method
	 * @return indicators.jsp*/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loadIndicators(){ 
		
		return "redirect:indicators";
	}
	
}

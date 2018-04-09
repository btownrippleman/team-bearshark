package com.bearshark.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class ControllerUtil {
	public static ModelAndView ForwardRequest(String viewLocation, String message, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(viewLocation);
		Map<String, Object> m = mav.getModel();
		m.put("message", message);
		req.setAttribute("message", message);
		
		return mav;
	}
	
	public static ModelAndView ForwardRequest(String viewLocation, HttpServletRequest req) {
		return ForwardRequest(viewLocation, null, req);
	}
}

package com.bearshark.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bearshark.loan.Lender;
import com.bearshark.loan.LenderService;

@Controller
public class LenderController {
	private LenderService lendServ = new LenderService();

	@RequestMapping(value = "/ctrl/addBank", method = { RequestMethod.POST })
	public ModelAndView addBank(HttpServletRequest req, HttpServletResponse resp) {
		String option = req.getParameter("option");
		if (option.equalsIgnoreCase("register"))
			return createLenderViaOption(req, resp);
		else
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Cancelled.", req);
	}

	private ModelAndView createLenderViaOption(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("lenderName");
		String zip = req.getParameter("zipCode");
		Lender lend = new Lender();
		lend.setLenderName(name);
		lend.setZipCode(zip);

		boolean created = lendServ.createLender(lend);
		if (created) {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Success.", req);
		} else {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Error adding bank.", req);
		}
	}

	@RequestMapping(value = "/ctrl/lenderView", method = { RequestMethod.GET })
	public ModelAndView getAllLenders(HttpServletRequest req, HttpServletResponse resp) {
		List<Lender> lenders = lendServ.getAllLenders();
		req.setAttribute("lenders", lenders);

		return ControllerUtil.ForwardRequest("../WEB-INF/pages/lenderView.jsp", null, req);
	}

}

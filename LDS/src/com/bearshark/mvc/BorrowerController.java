package com.bearshark.mvc;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bearshark.loan.Borrower;
import com.bearshark.loan.BorrowerService;

@Controller
public class BorrowerController {

	private BorrowerService service = new BorrowerService();
	
	@RequestMapping(value = "/ctrl/addBorrower", method = { RequestMethod.POST })
	public ModelAndView addBorrower(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		// System.out.println("I'm Mr. Borrower, look @ me");
		String option = req.getParameter("option");
		if (option.equalsIgnoreCase("register")) {
			return CreateBorrowerViaOption(req, resp);
		} else {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Cancelled Borrower add", req);
		}
	}

	private ModelAndView CreateBorrowerViaOption(HttpServletRequest req, HttpServletResponse resp)
			throws ParseException {
		BorrowerService service = new BorrowerService();
		
		Integer creditScore = Integer.parseInt(req.getParameter("creditScore"));
		String borrowerFirstName = req.getParameter("firstName");
		String borrowerLastName = req.getParameter("lastName");
		String zipcode = req.getParameter("zipCode");
		Borrower borrower = new Borrower();
		borrower.setCreditScore(creditScore);
		borrower.setFirstName(borrowerFirstName);
		borrower.setLastName(borrowerLastName);
		borrower.setZipCode(zipcode);

		boolean created = service.createBorrower(borrower);
		if (created) {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Success! Created Borrower", req);
		} else {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Couldn't create Borrower", req);
		}

	}

	@RequestMapping(value = "/ctrl/borrowerView", method = {RequestMethod.GET})
	public ModelAndView viewBorrowers(HttpServletRequest req, HttpServletResponse resp){
		List<Borrower> borrowers = service.getAllBorrowers();
		req.setAttribute("borrowers", borrowers);
		
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/borrowerView.jsp", null, req);
	}
}

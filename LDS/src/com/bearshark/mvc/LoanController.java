package com.bearshark.mvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bearshark.loan.Borrower;
import com.bearshark.loan.BorrowerService;
import com.bearshark.loan.Lender;
import com.bearshark.loan.LenderService;
import com.bearshark.loan.Loan;
import com.bearshark.loan.LoanService;

@Controller
public class LoanController {

	private LoanService loanServ = new LoanService();
	private BorrowerService borrowServ = new BorrowerService();
	private LenderService lenderServ = new LenderService();

	@RequestMapping(value = "/ctrl/addLoan", method = { RequestMethod.GET })
	public ModelAndView addLoanForm(HttpServletRequest req, HttpServletResponse resp) {
		List<Borrower> borrowers = borrowServ.getAllBorrowers();
		List<Lender> lenders = lenderServ.getAllLenders();

		req.setAttribute("borrowers", borrowers);
		req.setAttribute("lenders", lenders);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/addLoan.jsp", null, req);
	}

	@RequestMapping(value = "/ctrl/addLoan", method = { RequestMethod.POST })
	public ModelAndView addLoan(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		// System.out.println("I'm Mr. MeSeeks, i.e. LoanController, Look at Me!");
		String option = req.getParameter("option");
		if (option.equalsIgnoreCase("create_loan")) {
			return CreateLoanViaOption(req, resp);
		} else {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Cancelled", req);
		}
	}

	private ModelAndView CreateLoanViaOption(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		BorrowerService service = borrowServ;
		LenderService service2 = lenderServ;

		// System.out.println(req.getParameter("borrower"));
		Long borrowerId = Long.parseLong(req.getParameter("borrower"));
		Borrower borrower = service.getBorrowerByID(borrowerId);
		Long lenderId = Long.parseLong(req.getParameter("lender"));
		Lender lender = service2.getLenderById(lenderId);
		Double apr = Double.parseDouble(req.getParameter("apr"));
		Long principal = Long.parseLong(req.getParameter("principal"));

		String startDateStr = req.getParameter("loanstart");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date startDateUtil = (java.util.Date) sdf.parse(startDateStr);
		Date startDate = new Date(startDateUtil.getTime());

		String lastUpdateStr = req.getParameter("lastupdate");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date lastUpdateUtil = (java.util.Date) sdf2.parse(lastUpdateStr);
		Date lastUpdate = new Date(lastUpdateUtil.getTime());

		Boolean adjustable = "on".equalsIgnoreCase(req.getParameter("isadjustable"));

		Loan loan = new Loan();
		loan.setBorrower(borrower);
		loan.setLender(lender);
		loan.setLoanAPR(new BigDecimal(apr));
		loan.setLoanPrincipal(principal);
		loan.setLoanStartDate(startDate);
		loan.setLoanLastUpdate(lastUpdate);
		loan.setLoanIsAdjustable(adjustable);

		boolean created = loanServ.makeLoan(loan);
		if (created) {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Success!", req);
		} else {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Couldn't create loan", req);
		}

	}

	@RequestMapping(value = "/ctrl/viewLoans", method = { RequestMethod.GET })
	public ModelAndView viewLoans(HttpServletRequest req, HttpServletResponse resp) {
		List<Loan> loans = loanServ.getAllLoans();
		req.setAttribute("loans", loans);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/viewLoans.jsp", null, req);
	}
}

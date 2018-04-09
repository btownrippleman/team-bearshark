package com.bearshark.mvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bearshark.jdbc.PoolLoanDao;
import com.bearshark.loan.Borrower;
import com.bearshark.loan.BorrowerService;
import com.bearshark.loan.Lender;
import com.bearshark.loan.LenderService;
import com.bearshark.loan.Loan;
import com.bearshark.loan.LoanService;
import com.bearshark.loan.PoolLoanService;
import com.bearshark.pool.Pool;
import com.bearshark.pool.PoolService;
import com.gargoylesoftware.htmlunit.javascript.host.Console;

@Controller
public class ControllerTest {

	private String _errors[] = { null, "Unable to create borrower", "Unable to create lender", "Unable to create loan",
			"Unable to create pool" };

	private BorrowerService _borrowerService = new BorrowerService();
	private LenderService _lenderService = new LenderService();
	private LoanService _loanService = new LoanService();
	private PoolService _poolService = new PoolService();
	private PoolLoanService _poolLoanService = new PoolLoanService();

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test", method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) {
		List<Borrower> borrowers = _borrowerService.getAllBorrowers();
		req.setAttribute("borrowers", borrowers);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestBorrower.jsp", null, req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-borrower", method = { RequestMethod.GET })
	public ModelAndView borrower(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Borrower> borrowers = _borrowerService.getAllBorrowers();
			req.setAttribute("borrowers", borrowers);
			String error = req.getParameter("error");
			if (error != null) {
				int e = Integer.parseInt(error);
				if (e > _errors.length)
					return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestBorrower.jsp", _errors[0], req);
				return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestBorrower.jsp", _errors[e], req);
			}
		} catch (Exception e) {
			return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestBorrower.jsp", _errors[0], req);
		}
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestBorrower.jsp", _errors[0], req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-lender", method = { RequestMethod.GET })
	public ModelAndView lender(HttpServletRequest req, HttpServletResponse resp) {

		try {
			List<Lender> lenders = _lenderService.getAllLenders();
			req.setAttribute("lenders", lenders);
			String error = req.getParameter("error");
			if (error != null) {
				int e = Integer.parseInt(error);
				if (e > _errors.length)
					return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLender.jsp", _errors[0], req);
				return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLender.jsp", _errors[e], req);
			}
		} catch (Exception e) {
			return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLender.jsp", _errors[0], req);
		}
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLender.jsp", _errors[0], req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-loan", method = { RequestMethod.GET })
	public ModelAndView loan(HttpServletRequest req, HttpServletResponse resp) {

		try {
			List<Loan> loans = _loanService.getAllLoans();
			List<Borrower> borrowers = _borrowerService.getAllBorrowers();
			List<Lender> lenders = _lenderService.getAllLenders();

			req.setAttribute("lenders", lenders);
			req.setAttribute("borrowers", borrowers);
			req.setAttribute("loans", loans);

			String error = req.getParameter("error");
			if (error != null) {
				int e = Integer.parseInt(error);
				if (e > _errors.length)
					return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLoan.jsp", _errors[0], req);
				return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLoan.jsp", _errors[e], req);
			}
		} catch (Exception e) {
			return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLoan.jsp", _errors[0], req);
		}
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLoan.jsp", _errors[0], req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-pool", method = { RequestMethod.GET })
	public ModelAndView pool(HttpServletRequest req, HttpServletResponse resp) {

		List<Pool> pools = _poolService.getAllPools();
		List<Loan> loansUnassoc = _loanService.getAllUnassociatedLoans();

		req.setAttribute("pools", pools);
		req.setAttribute("free_loans", loansUnassoc);

		try {

			String error = req.getParameter("error");
			if (error != null) {
				int e = Integer.parseInt(error);
				if (e > _errors.length)
					return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestPool.jsp", _errors[0], req);
				return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestPool.jsp", _errors[e], req);
			}

			return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestPool.jsp", _errors[0], req);
		} catch (Exception e) {
			return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestPool.jsp", _errors[0], req);
		}
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-get-pool", method = { RequestMethod.GET })
	public ModelAndView get_pool(HttpServletRequest req, HttpServletResponse resp) {

		// Where is the Service layer call for this? Skipping Service layer
		// since functionality is not supported at that layer.

		PoolLoanDao dao = new PoolLoanDao();
		long id = Long.parseLong(req.getParameter("id"));
		List<Loan> loans = dao.getLoansInPool(id);

		try {
			if (loans != null) {
				for (Loan loan : loans) {
					resp.getWriter()
							.println("<tr>" + "<td>" + loan.getLoanID() + "</td>" + "<td>" + loan.getLoanAPR() + "</td>"
									+ "<td>" + loan.getLoanPrincipal() + "</td>" + "<td>" + loan.getLoanStartDate()
									+ "</td>" + "<td>" + loan.getLoanLastUpdate() + "</td>" + "<td>"
									+ loan.getLender().getLenderName() + "</td>" + "<td>"
									+ loan.getBorrower().getFullName() + "</td>" + "<td>"
									+ ((loan.getLoanIsAdjustable() == true) ? "Yes" : "No") + "</td>" + "</tr>");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * There is no functionality for deleting anything, so for now it gets
	 * ignored.
	 */

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-delete-loan", method = RequestMethod.GET)
	public ModelAndView deleteLoan(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		List<Loan> loans = _loanService.getAllLoans();
		List<Borrower> borrowers = _borrowerService.getAllBorrowers();
		List<Lender> lenders = _lenderService.getAllLenders();

		req.setAttribute("lenders", lenders);
		req.setAttribute("borrowers", borrowers);
		req.setAttribute("loans", loans);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLoan.jsp", null, req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-delete-borrower", method = RequestMethod.GET)
	public ModelAndView deleteBorrower(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Borrower> borrowers = _borrowerService.getAllBorrowers();
		req.setAttribute("borrowers", borrowers);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestBorrower.jsp", null, req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-delete-lender", method = RequestMethod.GET)
	public ModelAndView deleteLender(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Lender> lenders = _lenderService.getAllLenders();
		req.setAttribute("lenders", lenders);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestLender.jsp", null, req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test-delete-pool", method = RequestMethod.GET)
	public ModelAndView deletePool(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		List<Pool> pools = _poolService.getAllPools();
		List<Loan> loansUnassoc = _loanService.getAllUnassociatedLoans();

		req.setAttribute("pools", pools);
		req.setAttribute("free_loans", loansUnassoc);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/new/TestPool.jsp", null, req);
	}

	/**
	 * @Implemented.
	 */
	@RequestMapping(value = "/ctrl/test/add-borrower", method = RequestMethod.POST)
	public ModelAndView addBorrower(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		try {
			Integer creditScore = Integer.parseInt(req.getParameter("CreditScore"));
			String borrowerFirstName = req.getParameter("FirstName");
			String borrowerLastName = req.getParameter("LastName");
			String zipcode = req.getParameter("ZipCode");
			Borrower borrower = new Borrower(null, borrowerFirstName, borrowerLastName, creditScore, zipcode);
			boolean created = _borrowerService.createBorrower(borrower);
			List<Borrower> borrowers = _borrowerService.getAllBorrowers();
			if (created) {
				resp.sendRedirect("/LDS/ctrl/test-borrower?error=0");
			} else {
				resp.sendRedirect("/LDS/ctrl/test-borrower?error=1");
			}
		} catch (Exception e) {
			resp.sendRedirect("/LDS/ctrl/test-borrower?error=1");
		}
		return null;
	}

	@RequestMapping(value = "/ctrl/test/add-lender", method = RequestMethod.POST)
	public ModelAndView addLender(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			List<Lender> lenders = _lenderService.getAllLenders();
			req.setAttribute("lenders", lenders);

			String name = req.getParameter("BankName");
			String zip = req.getParameter("ZipCode");
			Lender lender = new Lender(null, name, zip);

			// FIXME: createLender never returns FALSE. Not a huge deal though
			// since empty fields will be caught in JSP's.

			boolean created = _lenderService.createLender(lender);
			if (created) {
				resp.sendRedirect("/LDS/ctrl/test-lender?error=0");
			} else {
				resp.sendRedirect("/LDS/ctrl/test-lender?error=2");
			}
		} catch (Exception e) {
			resp.sendRedirect("/LDS/ctrl/test-lender?error=2");
		}

		return null;
	}

	@RequestMapping(value = "/ctrl/test/add-loan", method = RequestMethod.POST)
	public ModelAndView addLoan(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {

			String borrowerName = req.getParameter("Borrower");
			String lenderName = req.getParameter("Lender");
			String apr = req.getParameter("Apr");
			String principal = req.getParameter("Principal");
			String startDateStr = req.getParameter("StartDate");
			String lastUpdateStr = req.getParameter("LastUpdated");
			String adjustable = req.getParameter("Adjustable");

			// remove non-breaking spaces.
			String correctedborrowerName = borrowerName.replaceAll("\u00A0", " ");
			String[] borrowerFullName = correctedborrowerName.split("\\s+");

			Borrower _borrower = _borrowerService.getBorrowerByFullName(borrowerFullName[0], borrowerFullName[1]);
			Lender _lender = _lenderService.getLenderByName(lenderName);

			Boolean _adjustable = false;
			if (adjustable != null)
				_adjustable = true;

			BigDecimal _apr = new BigDecimal(Double.parseDouble(apr));
			Long _principal = Long.parseLong(principal);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			java.util.Date startUpdateUtil = (java.util.Date) sdf.parse(startDateStr);
			Date _startDate = new Date(startUpdateUtil.getTime());

			java.util.Date lastUpdateUtil = (java.util.Date) sdf.parse(lastUpdateStr);
			Date _lastUpdate = new Date(startUpdateUtil.getTime());

			Loan loan = new Loan(null, _apr, _principal, _startDate, _lastUpdate, _adjustable);
			loan.setBorrower(_borrower);
			loan.setLender(_lender);

			boolean created = _loanService.makeLoan(loan);
			if (created) {
				resp.sendRedirect("/LDS/ctrl/test-loan?error=0");
				return null;
			} else {
				resp.sendRedirect("/LDS/ctrl/test-loan?error=3");
			}
			resp.sendRedirect("/LDS/ctrl/test-loan?error=3");

			return null;

		} catch (Exception e) {
			System.out.println("*** Exception: " + e);
			resp.sendRedirect("/LDS/ctrl/test-loan?error=3");
		}
		return null;

	}

	@RequestMapping(value = "/ctrl/test/test-add-pool", method = RequestMethod.POST)
	public ModelAndView addPool(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			List<Loan> loans = _loanService.getAllUnassociatedLoans();
			String poolIdStr = req.getParameter("pool_loan_list");
			String[] poolIdList = poolIdStr.split(",");

			List<Loan> _poolLoans = new ArrayList<Loan>();

			Pool pool = new Pool();

			for (String id : poolIdList)
				for (Loan loan : loans)
					if (loan.getLoanID() == (long) Long.parseLong(id))
						_poolLoans.add(loan);

			pool.setLoans(_poolLoans);
			Double totalWeightValueOfLoans = 0.0;
			Double totalInitialPrincipal = 0.0;

			for (Loan l : _poolLoans) {
				Long k = null;
				BigDecimal o = null;
				if (l != null) {
					k = l.getLoanPrincipal();
					o = l.getLoanAPR();
				}	
				if (k != null && o != null) {
					totalWeightValueOfLoans += k.doubleValue() * o.doubleValue();
					totalInitialPrincipal += k.doubleValue();
				}
			}
			pool.setInitial_apr(BigDecimal.valueOf(totalWeightValueOfLoans / totalInitialPrincipal));
			pool.setInitial_total(totalInitialPrincipal.longValue());
			pool.setCreation_date(java.sql.Date.valueOf(java.time.LocalDate.now()));

			long created = _poolService.createPool(pool);
			pool.setPool_id(_poolService.getLastAddedIdofPools());
			
			boolean loanAdd = _poolLoanService.addLoansToPoolOnCreation(pool);			
			resp.sendRedirect("/LDS/ctrl/test-pool?error=0");
			return null;

		} catch (Exception e) {
			System.out.println("*** Exception: " + e);
			resp.sendRedirect("/LDS/ctrl/test-pool?error=4");
		}

		return null;
	}
}

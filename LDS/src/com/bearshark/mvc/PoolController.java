package com.bearshark.mvc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bearshark.loan.Loan;
import com.bearshark.loan.LoanService;
import com.bearshark.loan.PoolLoanService;
import com.bearshark.pool.Pool;
import com.bearshark.pool.PoolService;

@Controller
public class PoolController {
	private PoolLoanService poolLoanServ = new PoolLoanService();
	private PoolService poolServ = new PoolService();
	private LoanService loanServ = new LoanService();

	@RequestMapping(value = "ctrl/addPool", method = { RequestMethod.GET })
	public ModelAndView addPool(HttpServletRequest req, HttpServletResponse resp)
			throws ParseException, InterruptedException {

		// System.out.println(" add Pool");
		String option = req.getParameter("option");
		if (option.equalsIgnoreCase("Add Selected Loans To a Pool")) {
			return CreatePoolViaOption(req, resp);
		} else {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Cancelled Pool add", req);
		}
	}

	private ModelAndView CreatePoolViaOption(HttpServletRequest req, HttpServletResponse resp)
			throws ParseException, InterruptedException {
		LoanService service = new LoanService();
		PoolService poolService = new PoolService();
		List<Loan> selectedLoans = new ArrayList<Loan>();
		String[] loanIDs = req.getParameter("loan_list").split(",");

		for (String s : loanIDs) {
			// System.out.println("loanID: " + s);
			Loan loanToAdd = service.getLoanById(s);
			// System.out.println("loanToAdd " + loanToAdd);
			if (loanToAdd != null)
				selectedLoans.add(loanToAdd);
			// System.out.println(unAssocLoans.toString());
		}

		// System.out.println("selectedLoans size equals = " +
		// selectedLoans.size());

		///// BEGIN Pool initialization code
		Pool pool = new Pool();
		pool.setLoans(selectedLoans);

		pool.initializeCreationData();

		boolean createdPool = false;
		try {
			// System.out.println("Attempting to persist pool...");
			// System.out.println(pool);
			createdPool = poolService.createPool(pool) != null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		pool.setPool_id(poolServ.getLastAddedIdofPools());

		// System.out.println("Attempting to create pool. Information to
		// follow...");
		// System.out.println(pool);

		boolean loanAdd = poolLoanServ.addLoansToPoolOnCreation(pool);

		if (createdPool && loanAdd) {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Success! Created Pool and added to Pool table",
					req);
		} else if (createdPool) {
			return ControllerUtil.ForwardRequest("../pages/index.jsp",
					"Could  create Pool but not able to add to pool loan table", req);
		} else if (loanAdd) {
			return ControllerUtil.ForwardRequest("../pages/index.jsp",
					"Couldn't create Pool but able to add to pool loan table", req);
		} else {
			return ControllerUtil.ForwardRequest("../pages/index.jsp", "Couldn't create Pool", req);
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/ctrl/selectLoansToBeAdded", method = { RequestMethod.GET })
	public ModelAndView viewLoansToBeAdded(HttpServletRequest req, HttpServletResponse resp) {
		// System.out.println("
		// @RequestMapping(value=\"/ctrl/selectLoansToBeAdded\", method={
		// RequestMethod.GET }) ");
		try {
			List<Loan> loans = loanServ.getAllUnassociatedLoans();
			req.setAttribute("loans", loans);
		} finally {
			return ControllerUtil.ForwardRequest("../WEB-INF/pages/selectLoansToBeAdded.jsp", null, req);
		}
	}

	@RequestMapping(value = "/ctrl/addUnassocLoansToPool", method = { RequestMethod.POST })
	public ModelAndView postviewLoansToBeAdded(HttpServletRequest req, HttpServletResponse resp)
			throws InterruptedException {
		try {
			// System.out.println("********addUnassocLoansToPool ");
			return addPool(req, resp);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/ctrl/viewPools", method = { RequestMethod.GET })
	public ModelAndView viewPools(HttpServletRequest req, HttpServletResponse resp) {
		// System.out.println("VIEW POOLS");
		List<Pool> pools = poolServ.getAllPools();
		if (pools == null)
			return ControllerUtil.ForwardRequest("../WEB-INF/pages/viewPools.jsp", null, req);
		// for (Pool p : pools)
		// System.out.println(p.toString());
		req.setAttribute("pools", pools);
		return ControllerUtil.ForwardRequest("../WEB-INF/pages/viewPools.jsp", null, req);
	}

	@RequestMapping(value = "/ctrl/deletePool", method = { RequestMethod.POST })
	public void deletePool(HttpServletRequest req, HttpServletResponse resp) {
		Pool pool = new Pool();
		req.setAttribute("pool", pool);
		poolLoanServ.deletePool(pool);
	}
}
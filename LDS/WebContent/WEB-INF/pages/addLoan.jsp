<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List, com.bearshark.loan.Borrower , com.bearshark.loan.Lender"
    %>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Loans</title>
		<!-- Bootstrap: Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	</head>
	<body class="container">
		<jsp:include page="../includes/nav.jsp"></jsp:include>
		<div class="well row" style="margin-top:70px;">
			<h1>LDS || Add Loan</h1>
			<form action="${ pageContext.request.contextPath }/ctrl/addLoan" method="post">
				<div class="form-group">
					<label class="col-xs-3" for="borrower">Borrower</label> <select required id="borrower" name="borrower">
	
	
						<option value="longid">${borrower.fullname}</option>
	
	
						<% List<Borrower> borrowers = (List<Borrower>) request.getAttribute("borrowers");%>
						<c:forEach items = "${ borrowers }" var = "borrower">
						<option value="${ borrower.borrowerID }">${borrower.fullName}</option>
						</c:forEach>
	
	
	
					</select>
					
				</div>
				<div class="form-group">
					<label class="col-xs-3" for="lender">Lender</label> <select required id="lender" name="lender">
	
	
						<option value="longid">${lender.name }</option>
						<%List<Lender> lenders = (List<Lender>) request.getAttribute("lenders"); %>
						<c:forEach items = "${ lenders }" var = "lender">
							<option value="${ lender.lenderID }">${ lender.lenderName }</option>
						</c:forEach>
	
	
						<c:forEach items = "${ request.lenders }" var = "lender">
							<option value="${ lender.lenderID }">${ lender.lenderName }</option>
						</c:forEach>
	
					</select>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3" for="apr">APR</label>
					<input type="number" name="apr" id="apr" required step="0.25" min="0.25">
				</div>
				
				<div class="form-group">
					<label class="col-xs-3" for="principal">Principal</label>
					<input type="number" name="principal" id="principal" required step="1000" min="50000">
				</div>
				
				<div class="form-group">
					<label class="col-xs-3" for="loanstart">Start Date</label>
					<input type="date" name="loanstart" id="loanstart" required min="2016-01-01">
				</div>
				
				<div class="form-group">
					<label class="col-xs-3" for="lastupdate">Last Update</label>
					<input type="date" name="lastupdate" id="lastupdate" required min="2016-01-01">
				</div>
				
				<div class="form-group">
					<label class="col-xs-3" for="isadjustable">Loan is Adjustable</label>
					<input type="checkbox" name="isadjustable" id="isadjustable">
				</div>
				
				<div class="form-group-btn-group">
					<button type="submit" name="option" value="create_loan" class="btn btn-primary">Add Loan</button>
					<button type = "submit" name = "option" value = "cancel_loan" class = "btn btn-primary">Cancel</button>
				</div>
			</form>
		</div>
		<jsp:include page="../includes/foot.jsp"></jsp:include>
	</body>
</html>
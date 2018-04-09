<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loans to be added into a Pool</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/nav.jsp"></jsp:include>
		<div class="well row" style="margin-top: 70px;">
			<h1>Unassociated Loans</h1>
			<form action="${ pageContext.request.contextPath }/ctrl/addPool"
				method="POST">

				<div class="row col-md-6 col-md-offset-3 ">
					<table class="table table-striped table hover">
						<tr>
							<th>LoanID</th>
							<th>Loan APR</th>
							<th>Principal</th>
							<th>Loan Start Date</th>
							<th>Last Loan Update</th>
							<th>Adjustable</th>
						</tr>

						<%
							java.util.ArrayList<com.bearshark.loan.Loan> loans = (java.util.ArrayList<com.bearshark.loan.Loan>) request
									.getAttribute("loans");
							System.out.println(loans);
						%>

						<%-- 					<c:forEach items=<%=request.getAttribute("loans")%> var="loan"></c:forEach>
					<c:forEach items="${request.getAttribute(\"loans\") }" var="loan"></c:forEach>
--%>
						<c:forEach items="<%=loans%>" var="loan">
							<tr>
								<td><input type="checkbox" name="${loan.loanID}">${loan.loanID }</input></td>
								<td>${loan.loanAPR }</td>
								<td>${loan.loanPrincipal }</td>
								<td>${loan.loanStartDate }</td>
								<td>${loan.loanLastUpdate }</td>
								<td>${loan.loanIsAdjustable }</td>
								<!-- 
							private Long loanID;
							private BigDecimal loanAPR;
							private Long loanPrincipal;
							private Date LoanStartDate;
							private Date LoanLastUpdate;
							private Boolean LoanIsAdjustable;
							private Borrower borrower;
							private Lender lender;
						 -->
							</tr>
						</c:forEach>
					</table>
					<div class="form-group">
						<input type="submit" name="option" value="SendtoPool"></input> <input
							type="submit" name="option" value="Goback"></input>
					</div>
			</form>
		</div>
	</div>

</body>
</html>
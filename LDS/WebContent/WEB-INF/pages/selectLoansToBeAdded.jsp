<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List, com.bearshark.loan.Loan"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LDS || View Loans</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body class="container">
	<jsp:include page="../includes/nav.jsp"></jsp:include>
	<div class="well row" style="margin-top:70px;">
	<h1>LDS || Select Unassociated Loans to be Added to a Pool</h1>
	<form
		action="${ pageContext.request.contextPath }/ctrl/addUnassocLoansToPool"
		method="post">
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Loan Principal</th>
				<th>APR</th>
				<th>Created</th>
				<th>Last update</th>
				<th>Borrower</th>
				<th>Lender</th>
			</tr>
			<%
				List<Loan> loans = (List<Loan>) request.getAttribute("loans");
			%>
			<c:forEach items="${ loans }" var="loan">
				<tr>
					<td><input type="checkbox" name="loan" />${ loan.loanID }</td>
					<input id="loan_list" name="loan_list" type="hidden" />
					<td>${ loan.loanPrincipal }</td>
					<td>${ loan.loanAPR }&#37;</td>
					<%-- percent --%>
					<td>${ loan.loanStartDate }</td>
					<td>${ loan.loanLastUpdate }</td>
					<td>${ loan.borrower.fullName }</td>
					<td>${ loan.lender.lenderName }</td>
				</tr>
			</c:forEach>
		</table>

		<script>
			function request() {
				var results = [];
				var elems = document.getElementsByName('loan');
				for (var k = 0; k < elems.length; k++) {
					var e = elems[k];
					if (e.checked)
						results.push(e.parentElement.innerText);
				}
				var loan_form = document.getElementById("loan_list");
				loan_form.value = results;
			}
		</script>

		<div class="form-group">
			<input type="submit" name="option"
				value="Add Selected Loans To a Pool" onclick="javaScript:request();" id="submit">
			<input type="submit" name="option" value="Cancel" required id="cancel">
		</div>
	</form>
	</div>
	</div>
	<jsp:include page="../includes/foot.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<h1>Loans held by "BorrowerName"</h1>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan View for BorrowerName</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class = "container">
	<div class = "row col-md-6 col-md-offset-3 ">
		<table class = "table table-striped table hover">
			<tr>
				<th>LoanID </th>
				<th>Loan APR</th>
				<th>Principal</th>
				<th>Loan Start Date</th>
				<th>Last Loan Update</th>
				<th>Adjustable</th>
			</tr>
			
			<c:forEach items = "${loaninfo }" var = "loan"></c:forEach>
			<tr>
				<td>${loan.id }</td>
				<td>${loan.apr }</td>
				<td>${loan.principal }</td>
				<td>${loan.startdate }</td>
				<td>${loan.lastupdate }</td>
				<td>${loan.adjustable }</td>
			</tr>
		</table>
	</div>
	<!--  <div class = "form-group">
		<input type = "submit" value = "Send to Pool">
		<input type = "submit" value = "Go back">
	</div> -->
	<jsp:include page="../WEB-INF/includes/foot.jsp"></jsp:include>
</div>
</body>
</html>
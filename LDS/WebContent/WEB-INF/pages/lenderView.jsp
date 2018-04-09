<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.List, com.bearshark.loan.Lender"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<h1>Loans granted by "LenderName"</h1>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan View for LenderName</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class = "container">
	<div class = "row col-md-6 col-md-offset-3 ">
		<table class = "table table-striped table hover">
			<tr>
				<th>LenderID </th>
				<th>Lender Name</th>
				<th>Lender Zip Code</th>
			</tr>
			
			<% List<Lender> lenders = (List<Lender>) request.getAttribute("lenders");%>
			<c:forEach items = "${lenders}" var = "lender">
			<tr>
				<td><input type = "checkbox"/>${lender.lenderID }</td>
				<td>${lender.lenderName}</td>
				<td>${lender.zipCode }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- Braden Ripple, I question whether or not the naming conventions ${lender.xxx} work -->
</div>
</body>
</html>


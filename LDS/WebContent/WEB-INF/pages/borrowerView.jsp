<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Borrowers for ${ lender }</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<h1>Borrower list for ${ lender }</h1>
<body>
<div class = "container">
	<div class = "row col-md-6 col-md-offset-3 ">
		<table class = "table table-striped table hover">
			<tr>
				<th>BorrowerID </th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Credit Score</th>
				<th>Zipcode</th>
				
			</tr>
			
			<c:forEach items = "${loaninfo }" var = "borrower"></c:forEach>
			<tr>
				<td><input type = "checkbox"/>${borrower.id }</td>
				<td>${borrower.firstname }</td>
				<td>${borrower.lastname }</td>
				<td>${borrower.creditScore }</td>
				<td>${borrower.zipcode }</td>
				
			</tr>
		</table>
	</div>
	<!--  <div class = "form-group">
		<input type = "submit" value = "Send to Pool">
		<input type = "submit" value = "Go back">
	</div> -->
</body>
</html>
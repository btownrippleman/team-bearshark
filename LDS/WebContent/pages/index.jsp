<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LDS</title>
<!-- Bootstrap: Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body class="container">
	<jsp:include page="../WEB-INF/includes/nav.jsp"></jsp:include>
	<div class="well row" style="margin-top:70px;">
		<h1>LDS &mdash; Welcome</h1>
		<c:if test="${ not empty message }">
			<div class="alert alert-danger">${ message }</div>
		</c:if>
		<ul>
			<li><a
				href="${ pageContext.request.contextPath }/pages/addBorrower.jsp">Add
					a Borrower</a></li>
			<li><a
				href="${ pageContext.request.contextPath }/pages/addBank.jsp">Add
					a Lender</a></li>
			<li><a href="${ pageContext.request.contextPath }/ctrl/addLoan">Add
					a Loan</a></li>
			<li><a
				href="${ pageContext.request.contextPath }/ctrl/selectLoansToBeAdded">Select
					Loans To Be Added to a Pool</a></li>
			<li><a
				href="${ pageContext.request.contextPath }/ctrl/viewLoans">View
					All Loans</a></li>
			<li><a
				href="${ pageContext.request.contextPath }/ctrl/viewPools">View
					All Pools</a></li>
		</ul>
	</div>
	<jsp:include page="../WEB-INF/includes/foot.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List, com.bearshark.pool.Pool, com.bearshark.pool.PoolService"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LDS || View Pools</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body class="container">
	<jsp:include page="../includes/nav.jsp"></jsp:include>
	<div class="well row" style="margin-top:70px;">
	<h1>LDS || View Pools</h1>
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<!--<th>Total value</th>-->
				<th>Initial value</th>
				<th>Initial apr</th>
				<th>Creation date</th>
				<!--<th>&#35; Loans</th>-->
			</tr>
			<c:forEach items="${pools }" var="pool">
				<tr>
					<td>${ pool.pool_id }</td>
					<td>${ pool.initial_total }</td>
					<td>${ pool.initial_apr }</td>
					<td>${ pool.creation_date }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../includes/foot.jsp"></jsp:include>
</body>
</html>
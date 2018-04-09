<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Borrower Registration</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<jsp:include page="../WEB-INF/includes/nav.jsp"></jsp:include>
		<div class="well row" style="margin-top: 70px;">
			<h1>Borrower Registration Page</h1>
			<h2>Please enter information in the following sections:</h2>
			<form action="${ pageContext.request.contextPath }/ctrl/addBorrower"
				method="POST">
				<div class="form-group">
					<label class="col-md-4 control label" for="firstNameInput"></label>
					First Name:<input id="col-md-4" name="firstName" type="text"
						maxlength="100" class="form-control input-md" required>
				</div>
				<div class="form-group">
					<label class="col-md-0 control label" for="lastNameInput"></label>
					Last Name:<input id="col-md-3" name="lastName" type="text"
						maxlength="100" class="form-control input-md" required>
				</div>
				<div class="form-group">
					<label class="col-md-0 control label" for="creditScoreInput"></label>
					Credit Score:<input id="col-md-2" name="creditScore" type="text"
						maxlength="3" class="form-control input-md" required>
				</div>
				<div class="form-group">
					<label class="col-md-0 control label" for="zipCodeInput"></label>
					Zip Code:<input id="col-md-1" name="zipCode" type="text"
						maxlength="5" class="form-control input-md" required>
				</div>
				<div class="form-group">
					<input type="submit" name="option" value="Register"> <input
						type="submit" name="option" value="Cancel" required>
				</div>
			</form>
		</div>
		<jsp:include page="../WEB-INF/includes/foot.jsp"></jsp:include>
	</div>
</body>
</html>
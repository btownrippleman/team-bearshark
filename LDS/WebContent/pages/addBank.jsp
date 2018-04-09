<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank Registration</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<jsp:include page="../WEB-INF/includes/nav.jsp"></jsp:include>
		<div>
			<div class="well row" style="margin-top:70px;">
				<h1>Bank Registration Page</h1>
				<h2>Please enter information in the following sections:</h2>
				<form action="${ pageContext.request.contextPath }/ctrl/addBank"
					method="POST">
					<div class="form-group">
						<label class="col-md-4 control label" for="nameInput"></label>
						Bank Name: <input id="col-md-4" name="lenderName" type="text"
							maxlength="10" class="form-control input-md">
					</div>
					<div class="form-group">
						<label class="col-md-0 control label" for="zipInput"></label>
						Zipcode: <input id="col-md-0" name="zipCode" type="text"
							maxlength="5" class="form-control input-md">
					</div>
					<div class="form-group">
						<input type="submit" name="option" value="Register"> <input
							type="submit" name="option" value="Cancel">
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="../WEB-INF/includes/foot.jsp"></jsp:include>
	</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="TestHead.jsp"%>
<%@ include file="TestNewLenderModal.jsp"%>
<%@ include file="TestErrorModal.jsp"%>

<!--  Main Page Content. -->

<body>

	<!--  Header -->

	<div id="header">
		<h2>Loan Delivery System</h2>
	</div>

	<%@ include file="TestMenu.jsp"%>

	<!-- Main. -->

	<div id="main">

		<center>
			<div class="well row">
				<table id="borrowerTable" class="table table-striped table-hover">
					<thead>
						<td><strong>Id</strong></td>
						<td><strong>Name</strong></td>
						<td><strong>Zip Code</strong></td>
						<td></td>
					</thead>
					<tbody>
						<c:forEach var="lender" items="${lenders}">
							<tr>
								<td>${lender.lenderID}</td>
								<td>${lender.lenderName}</td>
								<td>${lender.zipCode}</td>
								<td><img
									style="width: 20px; height: 20px; cursor: pointer;"
									src="https://cdn1.iconfinder.com/data/icons/nuove/128x128/actions/button_cancel.png"
									onClick="javaScript:window.location='/LDS/ctrl/test-delete-lender?id=' + ${lender.lenderID};" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<button type="button" class="btn btn-info btn-lg"
					data-toggle="modal" data-target="#NewLenderModal">New
					Lender</button>

			</div>
		</center>

		<!-- Scripts. -->

		<script>
			/**
			 *	Creates table.
			 */
			$('#borrowerTable').DataTable({
				"paging" : true,
				"language" : {
					"paginate" : {
						"previous" : "&laquo;",
						"next" : "&raquo;"
					}
				}
			});
		</script>

		<c:if test="${ not empty message }">
			<script>
				/**
				*	Show error dialog.
				*/
				document.getElementById("GlobalSystemError").innerText = "${message}";
				$('#ErrorModal').modal('show');
			</script>
		</c:if>

		<%@ include file="TestFooter.jsp"%>
		
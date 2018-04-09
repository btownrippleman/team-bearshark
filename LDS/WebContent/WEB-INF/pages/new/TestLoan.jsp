<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="TestHead.jsp"%>
<%@ include file="TestNewLoanModal.jsp"%>
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
				<table id="loanTable" class="table table-striped table-hover">
					<thead>
						<td><strong>Id</strong></td>
						<td><strong>APR</strong></td>
						<td><strong>Principal</strong></td>
						<td><strong>Start Date</strong></td>
						<td><strong>Last Updated</strong></td>
						<td><strong>Lender</strong></td>
						<td><strong>Borrower</strong></td>
						<td><strong>Adjustable</strong></td>
						<td></td>
					</thead>
					<tbody>
						<c:forEach var="loan" items="${loans}">
							<tr>
								<td>${loan.loanID}</td>
								<td>${loan.loanAPR}%</td>
								<td>${loan.loanPrincipal}</td>
								<td>${loan.loanStartDate}</td>
								<td>${loan.loanLastUpdate}</td>
								<td>${loan.lender.lenderName}</td>
								<td>${loan.borrower.firstName}&nbsp;${loan.borrower.lastName}</td>
								<c:choose>
									<c:when test="${loan.loanIsAdjustable == false}">
										<td>No</td>
									</c:when>
									<c:otherwise>
										<td>Yes</td>
									</c:otherwise>
								</c:choose>
								<td><img
									style="width: 20px; height: 20px; cursor: pointer;"
									src="https://cdn1.iconfinder.com/data/icons/nuove/128x128/actions/button_cancel.png"
									onClick="javaScript:window.location='/LDS/ctrl/test-delete-loan?id=' + ${loan.loanID};" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<button type="button" class="btn btn-info btn-lg"
					data-toggle="modal" data-target="#NewLoanModal">New Loan</button>

			</div>
		</center>

		<!-- Scripts. -->

		<script>
			/**
			 *	Creates table.
			 */
			$('#loanTable').DataTable({
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
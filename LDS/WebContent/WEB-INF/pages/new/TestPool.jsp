<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="TestHead.jsp"%>
<%@ include file="TestNewPoolModal.jsp"%>
<%@ include file="TestViewPoolModal.jsp"%>
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
						<td><strong>Initial value</strong></td>
						<td><strong>Initial Apr</strong></td>
						<td><strong>Creation Date</strong></td>
						<td></td>
					</thead>
					<tbody>
						<c:forEach var="pool" items="${pools}">
							<tr id="pool_${pool.pool_id}">
								<td onClick="javaScript:open_pool('${pool.pool_id}');">${pool.pool_id}</td>
								<td onClick="javaScript:open_pool('${pool.pool_id}');">${pool.initial_total}</td>
								<td onClick="javaScript:open_pool('${pool.pool_id}');">${pool.initial_apr}</td>
								<td onClick="javaScript:open_pool('${pool.pool_id}');">${pool.creation_date}</td>
								<td><img
									style="width: 20px; height: 20px; cursor: pointer;"
									src="https://cdn1.iconfinder.com/data/icons/nuove/128x128/actions/button_cancel.png"
									onClick="javaScript:window.location='/LDS/ctrl/test-delete-pool?id=' + ${pool.pool_id};" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<button type="button" class="btn btn-info btn-lg"
					data-toggle="modal" data-target="#NewPoolModal">New Pool</button>

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

			$('#NewPoolModalTable').DataTable({
				"paging" : true,
				"language" : {
					"paginate" : {
						"previous" : "&laquo;",
						"next" : "&raquo;"
					}
				}
			});
			
			function open_pool(id) {

				var xmlHttp = new XMLHttpRequest();
				xmlHttp.open("GET", "test-get-pool?id=" + id, false);
				xmlHttp.send(null);

				var s = xmlHttp.responseText;

				var html = '<table id="PoolLoanTable" class="table table-striped table-hover">'
						+ '<tr>'
						+ '<td><strong>Id</strong></td>'
						+ '<td><strong>APR</strong></td>'
						+ '<td><strong>Principal</strong></td>'
						+ '<td><strong>Start Date</strong></td>'
						+ '<td><strong>Last Updated</strong></td>'
						+ '<td><strong>Lender</strong></td>'
						+ '<td><strong>Borrower</strong></td>'
						+ '<td><strong>Adjustable</strong></td>'
						+ '</tr>'
						+ s
						+ '</table>';

				document.getElementById("view_pool_modal_body").innerHTML = html;

				$('#ViewPoolModal').modal('show');
			}
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
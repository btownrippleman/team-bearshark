
<div id="NewPoolModal" class="modal fade" role="dialog">
	<div class="modal-new-pool">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">New Pool</h4>
			</div>
			<div class="modal-body">
				<center>
					<strong>Unassociated Loans:</strong>
					<table id="NewPoolModalTable"
						class="table table-striped table-hover">
						<thead>
							<td></td>
							<td><strong>Id</strong></td>
							<td><strong>Borrower</strong></td>
							<td><strong>Lender</strong></td>							
							<td><strong>Apr</strong></td>
							<td><strong>Principal</strong></td>
							<td><strong>Start Date</strong></td>
							<td><strong>Last Updated</strong></td>
							<td><strong>Is Adjustable</strong></td>
						</thead>
						<tbody>
							<c:forEach var="loan" items="${free_loans}">
								<tr>
									<td><input type="checkbox" name="pool_loan" /></td>
									<td>${loan.loanID}</td>
									<td>${loan.borrower.firstName}&nbsp;${loan.borrower.lastName}</td>
									<td>${loan.lender.lenderName}</td.>
									<td>${loan.loanAPR}</td>
									<td>${loan.loanPrincipal}</td>
									<td>${loan.loanStartDate}</td>
									<td>${loan.loanLastUpdate}</td>
									<c:choose>
										<c:when test="${loan.loanIsAdjustable == false}">
											<td>No</td>
										</c:when>
										<c:otherwise>
											<td>Yes</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</center>
			</div>
			<div class="modal-footer">
				<form method="post" action="/LDS/ctrl/test/test-add-pool">
					<input type="hidden" id="pool_loan_list" name="pool_loan_list"></input>
					<button type="submit" class="btn btn-danger"
						onClick="javaScript:send_pool_request();">Submit</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
	function send_pool_request() {
		var results = [];
		var elems = document.getElementsByName('pool_loan');

		for (var k = 0; k < elems.length; k++) {
			var e = elems[k];
			var real_id = +e.parentElement.parentElement.cells[1].innerText;

			if (e.checked)
				results.push(real_id);
		}
		
		var loan_form = document.getElementById("pool_loan_list");
		loan_form.value = results;
	}
</script>

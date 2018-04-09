
<div id="NewLoanModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">New Loan</h4>
			</div>
			<form method="post" action="/LDS/ctrl/test/add-loan">
				<div class="modal-body">
					<table>
						<tr>
							<td><label for="Borrower">Borrower: </label></td>
							<td><select name="Borrower">
									<c:forEach var="borrower" items="${borrowers}">
										<option>${borrower.firstName}&nbsp;${borrower.lastName}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><label>Lender: </label></td>
							<td><select name="Lender">
									<c:forEach var="lender" items="${lenders}">
										<option>${lender.lenderName}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><label for="Apr">Apr:</label>
							<td><input type="number" placeholder="Apr" name="Apr" /></td>
						</tr>
						<tr>
							<td><label for="Principal">Principal</label>
							<td><input type="number" placeholder="Principal" name="Principal" /></td>
						</tr>
						<tr>
							<td><label for="StartDate">Start Date:</label>
							<td><input type="date" placeholder="Start Date" name="StartDate" /></td>
						</tr>
						<tr>
							<td><label for="LastUpdated">Last Update:</label>
							<td><input type="date" placeholder="Last Update" name="LastUpdated" /></td>
						</tr>
						<tr>
							<td><label for="Adjustable">Loan is Adjustable:</label>
							<td><input type="checkbox" name="Adjustable" /></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-danger">Submit</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</form>
		</div>
	</div>
</div>

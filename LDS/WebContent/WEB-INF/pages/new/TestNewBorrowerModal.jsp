
<div id="NewBorrowerModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">New Borrower</h4>
			</div>
			<form method="post" action="/LDS/ctrl/test/add-borrower">
				<div class="modal-body">
					<table>
						<tr>
							<td><label for="FirstName">First name: </label></td>
							<td><input name="FirstName" type="text"
								placeholder="First Name" /></td>
						</tr>
						<tr>
							<td><label for="LastName">Last name: </label></td>
							<td><input name="LastName" type="text"
								placeholder="Last Name" /></td>
						</tr>
						<tr>
							<td><label for="CreditScore">Credit Score: </label></td>
							<td><input name="CreditScore" type="number"
								placeholder="Credit Score" /></td>
						</tr>
						<tr>
							<td><label for="ZipCode">Zip Code: </label></td>
							<td><input name="ZipCode" type="number"
								placeholder="Zip Code" /></td>
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

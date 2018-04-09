
<div id="NewLenderModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">New Lender</h4>
			</div>
			<form method="post" action="/LDS/ctrl/test/add-lender">
				<div class="modal-body">
					<table>
						<tr>
							<td><label for="BankName">Bank name: </label></td>
							<td><input name="BankName" type="text"
								placeholder="Bank Name" /></td>
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

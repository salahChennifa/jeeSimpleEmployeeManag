
<div id="editEmployeeModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="/employee-crud-project-0.0.1-SNAPSHOT/update" method="post">
				<div class="modal-header">
					<h4 class="modal-title">Edit Employee</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Name</label>
						<input type="text" name="name" ng-model="employee.name" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Email</label>
						<input type="email" name="email" ng-model="employee.email" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Address</label>
						<textarea name="address" ng-model="employee.address" class="form-control" required></textarea>
					</div>
					<div class="form-group">
						<label>Phone</label>
						<input type="text" name="phone" ng-model="employee.phone" class="form-control" required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="id" value="{{employee.id}}">
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
					<input type="submit" class="btn btn-info" value="Save">
				</div>
			</form>
		</div>
	</div>
</div>
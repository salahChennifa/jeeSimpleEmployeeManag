var app = angular.module('employeeApp', []);

app.controller('employeeCtrl', function ($scope) {
	$scope.getEmployeeDetails = function (employeeId) {
		var employeeDetails = '';
		$.ajax(
			{
				url: '/employee-crud-project-0.0.1-SNAPSHOT/get',
				type: 'POST',
				data: { "employeeId": employeeId },
				async: false,
				success: function (data, textStatus, jqXHR) {
					employeeDetails = data;
				},
				error: function (jqXHR, textStatus, error) {
					employeeDetails = "";
					console.log("Eroor in getting employee details from server => " + error);
				}
			}
		);
		$scope.employee = JSON.parse(employeeDetails);
		console.log("employee Details ==> " + $scope.employee);
		return $scope.employee;
	}
});
$(document).ready(function () {

	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function () {
		if (this.checked) {
			checkbox.each(function () {
				this.checked = true;
			});
		} else {
			checkbox.each(function () {
				this.checked = false;
			});
		}
	});
	checkbox.click(function () {
		if (!this.checked) {
			$("#selectAll").prop("checked", false);
		}
	});

	$('#deleteBtn').click
		(
			function () {
				var deletedEmployees = [];
				$("input:checkbox[class='employeeCheck']:checked").each(function () {
					deletedEmployees.push($(this).val());
				});

				deletedEmployees = deletedEmployees.join(",")
				var employeeIds = deletedEmployees.toString();
				console.log("employeeIds ==> ", employeeIds);
				$.ajax(
					{
						url: '/employee-crud-project-0.0.1-SNAPSHOT/delete',
						async: false,
						dataType: "html",
						type: "POST",
						data: { "employeeIds": employeeIds },
						success: function (data, textStatus, jqXHR) {
							if (data != "") {
								response = data;
							}
							else {
								response = '';
							}
							console.log("response ==>", response);
							window.location.href = '/employee-crud-project-0.0.1-SNAPSHOT/';
						},
						error: function (jqXHR, textStatus, errorThrown) {
							console.log("something went wrong==>", errorThrown);
							response = '';
							alert('exception, errorThrown==>' + errorThrown);
						}
					});
			}
		);
});

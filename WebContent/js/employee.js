var app = angular.module('employeeApp', []);
		var contextPath = '${pageContext.request.contextPath}';
		var selectOptionRecordsPerPage = '${recordsPerPage}';
		app.controller('employeeCtrl', function ($scope) {
			$scope.getEmployeeDetails = function (employeeId) {
				var employeeDetails = '';
				$.ajax(
					{
						url: contextPath + '/get',
						type: 'POST',
						data: { "employeeId": employeeId },
						async: false,
						success: function (data, textStatus, jqXHR) {
							employeeDetails = data;
						},
						error: function (jqXHR, textStatus, error) {
							employeeDetails = "";
							console.error("Eroor in getting employee details from server => " + error);
						}
					}
				);
				$scope.employee = JSON.parse(employeeDetails);
				
				return $scope.employee;
			}
		});
		$(document).ready(function () {
			$("#recordsPerPage option").each(function () {
				if ($(this).text() == selectOptionRecordsPerPage)
					$(this).attr("selected", "selected");
			});
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
						$.ajax(
							{
								url: contextPath + '/delete',
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
									
									window.location.href = contextPath + '/';
								},
								error: function (jqXHR, textStatus, errorThrown) {
									
									response = '';
									alert('exception, errorThrown==>' + errorThrown);
								}
							});
					}
				);
				$('#recordsPerPage').click
				(
					function () {
						var recordsPerPage = $(this).val();
						$.ajax(
							{
								url: contextPath + '/',
								async: false,
								dataType: "html",
								type: "POST",
								data: { "recordsPerPage": recordsPerPage },
								success: function (data, textStatus, jqXHR) {
									if (data != "") {
										response = data;
									}
									else {
										response = '';
									}
									window.location.href = contextPath + '/';
								},
								error: function (jqXHR, textStatus, errorThrown) {
									response = '';
									alert('exception, errorThrown==>' + errorThrown);
								}
							});
					}
				);
				
		});

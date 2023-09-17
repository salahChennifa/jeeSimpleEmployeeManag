<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Servlet, JSP, JDBC and MVC Example</title>

	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<style>
		  body {
        color: #566787;
		background: #f5f5f5;
		font-family: 'Varela Round', sans-serif;
		font-size: 13px;
	}
	.table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 30px 0;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
	.table-title {        
		padding-bottom: 15px;
		background: #435d7d;
		color: #fff;
		padding: 16px 30px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 5px 0 0;
		font-size: 24px;
	}
	.table-title .btn-group {
		float: right;
	}
	.table-title .btn {
		color: #fff;
		float: right;
		font-size: 13px;
		border: none;
		min-width: 50px;
		border-radius: 2px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
		padding: 12px 15px;
		vertical-align: middle;
    }
	table.table tr th:first-child {
		width: 60px;
	}
	table.table tr th:last-child {
		width: 100px;
	}
    table.table-striped tbody tr:nth-of-type(odd) {
    	background-color: #fcfcfc;
	}
	table.table-striped.table-hover tbody tr:hover {
		background: #f5f5f5;
	}
    table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }	
    table.table td:last-child i {
		opacity: 0.9;
		font-size: 22px;
        margin: 0 5px;
    }
	table.table td a {
		font-weight: bold;
		color: #566787;
		display: inline-block;
		text-decoration: none;
		outline: none !important;
	}
	table.table td a:hover {
		color: #2196F3;
	}
	table.table td a.edit {
        color: #FFC107;
    }
    table.table td a.delete {
        color: #F44336;
    }
    table.table td i {
        font-size: 19px;
    }
	table.table .avatar {
		border-radius: 50%;
		vertical-align: middle;
		margin-right: 10px;
	}
    .pagination {
        float: right;
        margin: 0 0 5px;
    }
    .pagination li a {
        border: none;
        font-size: 13px;
        min-width: 30px;
        min-height: 30px;
        color: #999;
        margin: 0 2px;
        line-height: 30px;
        border-radius: 2px !important;
        text-align: center;
        padding: 0 6px;
    }
    .pagination li a:hover {
        color: #666;
    }	
    .pagination li.active a, .pagination li.active a.page-link {
        background: #03A9F4;
    }
    .pagination li.active a:hover {        
        background: #0397d6;
    }
	.pagination li.disabled i {
        color: #ccc;
    }
    .pagination li i {
        font-size: 16px;
        padding-top: 6px
    }
    .hint-text {
        float: left;
        margin-top: 10px;
        font-size: 13px;
    }    
	/* Custom checkbox */
	.custom-checkbox {
		position: relative;
	}
	.custom-checkbox input[type="checkbox"] {    
		opacity: 0;
		position: absolute;
		margin: 5px 0 0 3px;
		z-index: 9;
	}
	.custom-checkbox label:before{
		width: 18px;
		height: 18px;
	}
	.custom-checkbox label:before {
		content: '';
		margin-right: 10px;
		display: inline-block;
		vertical-align: text-top;
		background: white;
		border: 1px solid #bbb;
		border-radius: 2px;
		box-sizing: border-box;
		z-index: 2;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		content: '';
		position: absolute;
		left: 6px;
		top: 3px;
		width: 6px;
		height: 11px;
		border: solid #000;
		border-width: 0 3px 3px 0;
		transform: inherit;
		z-index: 3;
		transform: rotateZ(45deg);
	}
	.custom-checkbox input[type="checkbox"]:checked + label:before {
		border-color: #03A9F4;
		background: #03A9F4;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		border-color: #fff;
	}
	.custom-checkbox input[type="checkbox"]:disabled + label:before {
		color: #b8b8b8;
		cursor: auto;
		box-shadow: none;
		background: #ddd;
	}
	/* Modal styles */
	.modal .modal-dialog {
		max-width: 400px;
	}
	.modal .modal-header, .modal .modal-body, .modal .modal-footer {
		padding: 20px 30px;
	}
	.modal .modal-content {
		border-radius: 3px;
	}
	.modal .modal-footer {
		background: #ecf0f1;
		border-radius: 0 0 3px 3px;
	}
    .modal .modal-title {
        display: inline-block;
    }
	.modal .form-control {
		border-radius: 2px;
		box-shadow: none;
		border-color: #dddddd;
	}
	.modal textarea.form-control {
		resize: vertical;
	}
	.modal .btn {
		border-radius: 2px;
		min-width: 100px;
	}	
	.modal form label {
		font-weight: normal;
	}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script>
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

	</script>

</head>

<body ng-app="employeeApp" ng-controller="employeeCtrl">
	<%
		// TODO
		Object dfdt  = request.getAttribute("recordsPerPage");
        out.print("Welecome " + dfdt);
    %>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>
							Manage <b>Employees</b>
						</h2>
					</div>
					<div class="col-sm-6">
						<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
							<i class="material-icons">&#xE147;</i>
							<span>Add New Employee</span>
						</a>
						<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
							<i class="material-icons">&#xE15C;</i>
							<span>Delete</span>
						</a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>
							<span class="custom-checkbox">
								<input type="checkbox" id="selectAll">
							</span>
						</th>
						<th>Name</th>
						<th>Email</th>
						<th>Address</th>
						<th>Phone</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="employee" items="${employees}">
						<tr>
							<td>
								<span class="custom-checkbox">
									<input class="employeeCheck" id="${employee.id}" type="checkbox" name="options[]"
										value="${employee.id}">
									<label for="checkbox1"></label>
								</span>
							</td>
							<td>${employee.name}</td>
							<td>${employee.email}</td>
							<td>${employee.address}</td>
							<td>${employee.phone}</td>
							<td>
								<a href="#editEmployeeModal" class="edit" data-toggle="modal">
									<i class="material-icons" ng-click="getEmployeeDetails('${employee.id}')"
										data-toggle="tooltip" title="Edit">&#xE254;</i>
								</a>
								<a href="#deleteEmployeeModal" class="delete" data-toggle="modal">
									<i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

				<div class="clearfix">
					<div class="hint-text">
						Showing <b>${recordsPerPage}</b> out of <b>${noOfRecords}</b> entries
					</div>
					<ul class="pagination">
						<c:if test="${currentPage != 1}">
							<li class="page-item"><a href="list?page=${currentPage - 1}">Previous</a></li>
						</c:if>
						<c:forEach begin="1" end="${noOfPages}" var="i">
							<c:choose>
								<c:when test="${currentPage eq i}">
									<li class="page-item active"><a href="#" class="page-link">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a href="list?page=${i}" class="page-link">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${currentPage lt noOfPages}">
							<li class="page-item"><a href="list?page=${currentPage + 1}" class="page-link">Next</a></li>
						</c:if>
					</ul>
				</div>
		</div>
	</div>
	
	<jsp:include page="addEmployeeView.jsp"></jsp:include>
	
	<jsp:include page="updateEmployeeView.jsp"></jsp:include>
	
	<jsp:include page="deleteEmployeeView.jsp"></jsp:include>

</body>

</html>
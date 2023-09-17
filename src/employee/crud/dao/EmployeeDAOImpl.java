package employee.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import employee.crud.beans.Employee;
import employee.crud.db.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Connection connection = DBConnection.connection;

	@Override
	public boolean addEmployee(Employee employee) {
		System.out.println("Start addEmployee");

		try {
			String insertStatement = "INSERT INTO employee_db.employee (name, email, phone, address) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());

			int result = preparedStatement.executeUpdate();
			return result == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		System.out.println("Start updateEmployee");

		try {
			String updateStatement = "UPDATE employee_db.employee SET NAME = ?, email = ?, phone = ?, address = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setInt(5, employee.getId());

			int result = preparedStatement.executeUpdate();
			return result == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		System.out.println("Start deleteEmployee");

		try {
			String deleteStatement = "DELETE FROM employee_db.employee WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, employeeId);

			int result = preparedStatement.executeUpdate();
			return result == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("Start Select All Employees");

		try {
			String selectStatement = "SELECT * FROM employee_db.employee;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			while (resultSet.next()) {
				Employee employee = new Employee();

				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));

				employees.add(employee);
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getAllEmployeesSize() {
		try {
			int nbrOfRows = 0;
			String selectStatement = "SELECT COUNT(*) FROM employee_db.employee;";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				nbrOfRows = resultSet.getInt(1);
			}
			return nbrOfRows;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Employee getEmployee(int employeeId) {
		System.out.println("Start Select All Employees");

		try {
			String selectStatement = "SELECT * FROM employee_db.employee WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setInt(1, employeeId);

			ResultSet resultSet = preparedStatement.executeQuery();
			Employee employee = new Employee();

			while (resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));
			}

			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> viewAllEmployees(int offset, int noOfRecords) {
		System.out.println("Start Select viewAllEmployees");
		try {
			String selectStatement = "SELECT * FROM employee_db.employee limit " + offset + ", " + noOfRecords;
			PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));
				employees.add(employee);
			}
			System.out.println("employees offset : " + Arrays.toString(employees.toArray()));
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {

		Employee employee = new Employee();
		employee.setId(13);
		employee.setName("Karim");
		employee.setEmail("kareem2022@gmail.com");
		employee.setAddress("France");
		employee.setPhone("004486955547");

		EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		// System.out.println(employeeDAOImpl.addEmployee(employee)); //Insert
		// System.out.println(employeeDAOImpl.updateEmployee(employee)); //Update
		// System.out.println(employeeDAOImpl.deleteEmployee(19)); //Delete
		// System.out.println(employeeDAOImpl.getAllEmployees().size()); //Select All
		// System.out.println(employeeDAOImpl.getAllEmployees().get(0)); //Select All
		// System.out.println(employeeDAOImpl.getEmployee(7));
		employeeDAOImpl.viewAllEmployees(0, 2); // Select One Employee
	}

}

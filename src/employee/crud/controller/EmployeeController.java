package employee.crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import employee.crud.beans.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.dao.EmployeeDAOImpl;  

/**
  * Servlet implementation class EmployeeController
 */
@WebServlet("/")
public class EmployeeController extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(EmployeeController.class);  
	private static final long serialVersionUID = 1L;
	private int page;
	private int recordsPerPage;

	

	

	EmployeeDAO employeeDAO = null;

	public EmployeeController() {
		super();
		this.page = 1;
		this.recordsPerPage = 5;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		employeeDAO = new EmployeeDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("EmployeeController, doPost method Started");

		String action = request.getServletPath();
		
		logger.debug("doPost, action==>" + action);
		
		switch (action) {
			case "/add": {
				addNewEmployee(request, response);
				break;
			}
			case "/update": {
				updateEmployee(request, response);
				break;
			}
			case "/delete": {
				deleteEmployee(request, response);
				break;
			}
			case "/list": {
				this.page = Integer.parseInt(
					request.getParameter("page"));
				getAllEmployeesPagination(request, response);
				break;
			}
			case "/get": {
				getEmployee(request, response);
				break;
			}
			default: {
				getAllEmployeesPagination(request, response);
				break;
			}
		}
	}

	private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Start getEmployee");

		int id = Integer.parseInt(request.getParameter("employeeId"));
		logger.debug("getEmployee, Employee ID ==>" + id);

		Employee employee = employeeDAO.getEmployee(id);
		logger.debug("getEmployee, result is ==> " + employee);

		try {
			ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String employeeStr = mapper.writeValueAsString(employee);

			ServletOutputStream servletOutputStream = response.getOutputStream();
			servletOutputStream.write(employeeStr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("page") != null) {
				this.page = Integer.parseInt(
						request.getParameter("page"));
			}
			if (request.getParameter("recordsPerPage") != null) {
				this.recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
				this.page = 1;
			}
			int noOfRecords = ((EmployeeDAOImpl) employeeDAO).getAllEmployeesSize();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0
					/ recordsPerPage);

			String employeeIds = request.getParameter("employeeIds");
			if (employeeIds == null) {
				List<Employee> employees = employeeDAO.viewAllEmployees((page - 1) * recordsPerPage,
						recordsPerPage);
				logger.debug("insertResult employees ==>" + employees.size());
				request.setAttribute("employees", employees);
				request.setAttribute("noOfPages", noOfPages);
				if (employees.isEmpty()){
					this.page = page - 1;
					request.setAttribute("currentPage", page - 1);
				}else {
					request.setAttribute("currentPage", page);
				}
				request.setAttribute("recordsPerPage", recordsPerPage);
				request.setAttribute("noOfRecords", noOfRecords);
				request.setAttribute("noOfRecords", noOfRecords);
				request.setAttribute("employeeIsDeleted", false);
				redirectToList(request, response);
				return;
			}
			StringTokenizer tokenizer = new StringTokenizer(employeeIds, ",");
			boolean result = false;
			while (tokenizer.hasMoreTokens()) {
				String employeeId = tokenizer.nextElement().toString();
				logger.debug("Employee ID to be deleted,  employeeId ==>" + employeeId);

				result = employeeDAO.deleteEmployee(Integer.parseInt(employeeId));
				logger.debug("is employeeId " + employeeId + " deleted ? " + result);
			}

			List<Employee> employees = employeeDAO.viewAllEmployees((page - 1) * recordsPerPage,
						recordsPerPage);
			logger.debug("insertResult employees ==>" + employees.size());

			request.setAttribute("employees", employees);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("recordsPerPage", recordsPerPage);
			request.setAttribute("noOfRecords", noOfRecords);
			request.setAttribute("employeeIsDeleted", result);
			redirectToList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void redirectToList(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Start updateEmployee");
		if (request.getParameter("page") != null){
			this.page = Integer.parseInt(
					request.getParameter("page"));
		}
		if (request.getParameter("recordsPerPage") != null) {
			this.recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
			this.page = 1;
		}
		int noOfRecords = ((EmployeeDAOImpl) employeeDAO).getAllEmployeesSize();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0
				/ recordsPerPage);

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		Employee employee = new Employee(id, name, email, phone, address);
		logger.debug("updateEmployee, Employee Details==>" + employee);

		boolean result = employeeDAO.updateEmployee(employee);
		logger.debug("updateEmployee, result is ==> " + result);
		List<Employee> employees = employeeDAO.viewAllEmployees((page - 1) * recordsPerPage,
						recordsPerPage);
		logger.debug("getAllEmployees, employees size ==> " + employees.size());
		try {
			request.setAttribute("employees", employees);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("recordsPerPage", this.recordsPerPage);
			request.setAttribute("noOfRecords", noOfRecords);
			request.setAttribute("employeeIsUpdated", result);
			response.sendRedirect(request.getContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Start addNewEmployee");
		if (request.getParameter("page") != null) {
			this.page = Integer.parseInt(
					request.getParameter("page"));
		}

		if (request.getParameter("recordsPerPage") != null) {
			this.recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
			this.page = 1;
		}

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		Employee employee = new Employee(name, email, phone, address);
		logger.debug("addNewEmployee, Employee Details==>" + employee);

		boolean result = employeeDAO.addEmployee(employee);
		logger.debug("addNewEmployee, result is ==> " + result);

		int noOfRecords = ((EmployeeDAOImpl) employeeDAO).getAllEmployeesSize();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0
				/ recordsPerPage);
		if (noOfPages > this.page){
			this.page = noOfPages; 
		}

		List<Employee> employees = employeeDAO.viewAllEmployees((page - 1) * recordsPerPage,
						recordsPerPage);
		logger.debug("getAllEmployees, employees size ==> " + employees.size());

		try {
			request.setAttribute("employees", employees);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", noOfPages);
			request.setAttribute("recordsPerPage", recordsPerPage);
			request.setAttribute("noOfRecords", noOfRecords);
			request.setAttribute("employeeIsAdded", result);
			response.sendRedirect(request.getContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getAllEmployeesPagination(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Start getAllEmployeesPagination");
		if (request.getParameter("page") != null) {
			this.page = Integer.parseInt(
					request.getParameter("page"));
		}
		if (request.getParameter("recordsPerPage") != null) {
			this.recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
			this.page = 1;
		}
		
		List<Employee> employees = employeeDAO.viewAllEmployees((page - 1) * recordsPerPage,
				recordsPerPage);
		int noOfRecords = ((EmployeeDAOImpl) employeeDAO).getAllEmployeesSize();

		int noOfPages = (int) Math.ceil(noOfRecords * 1.0
				/ recordsPerPage);

		logger.debug("getAllEmployees, employees size ==> " + employees.size());

		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("employeesView.jsp");
			request.setAttribute("employees", employees);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("recordsPerPage", recordsPerPage);
			request.setAttribute("noOfRecords", noOfRecords);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
}

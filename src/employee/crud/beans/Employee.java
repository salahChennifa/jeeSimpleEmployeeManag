package employee.crud.beans;

public class Employee {

	private int id;
	private String name;	
	private String email;
	private String phone;
	private String address;

	public Employee() {
		super();
	}

	public Employee(int id, String name, String email, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public Employee(String name, String email, String phone, String address) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phone="
				+ phone + "]";
	}

	public static void main(String[] args) {
		Employee employee = new Employee();

		employee.setId(1);
		employee.setName("Karim Mahmoud");
		employee.setAddress("Egypt");
		employee.setPhone("009750413948");
		employee.setEmail("kmshawky20@gmail.com");

		System.out.println(employee);
	}
}
package uo.cpm.p3.model;

public class Customer {
	public static final int EDAD_MIN = 16;
	public static final int EDAD_MAX = 100;
	private String name, password;
	private Integer birthYear;
	
	public Customer(String name, String password, Integer birthYear) {
		super();
		this.name = name;
		this.password = password;
		this.birthYear = birthYear;
	}

	public Customer() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

}

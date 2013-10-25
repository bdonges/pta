package bill.pta;

public class Guardian 
{

	// --------------------------------------------------
	// constructors	
	public Guardian()
	{
	}
	
	// --------------------------------------------------
	// variables	
	private String priority;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	
	// --------------------------------------------------
	// methods	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		if (email == null)
			email = "";
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhone() {
		if (phoneNumber == null)
			phoneNumber = "";
		return phoneNumber;
	}	
}

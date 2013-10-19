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
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

package bill.pta;

public class Student 
{

	// --------------------------------------------------
	// constructors	
	public Student() 
	{
	}
	
	// --------------------------------------------------
	// variables
	private String firstName;
	private String grade;
	private String displayGrade;
	private String teacher;
	
	// --------------------------------------------------
	// methods
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
		setDisplayGrade();
	}
	
	public String getDisplayGrade() {
		return displayGrade;
	}
	
	private void setDisplayGrade() 
	{
		String s = "";
		
		if (this.getGrade().equalsIgnoreCase("PK"))
			s = "PK";
		else if (this.getGrade().equalsIgnoreCase("KK"))
			s = "K";
		else if (this.getGrade().equalsIgnoreCase("01"))
			s = "1st";
		else if (this.getGrade().equalsIgnoreCase("02"))
			s = "2nd";
		else if (this.getGrade().equalsIgnoreCase("03"))
			s = "3rd";
		else if (this.getGrade().equalsIgnoreCase("04"))
			s = "4th";
		else if (this.getGrade().equalsIgnoreCase("05"))
			s = "5th";		

		this.displayGrade = s;
	}
	
	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}

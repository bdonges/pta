package bill.pta;

import java.util.ArrayList;

public class Entry 
{
	
	// --------------------------------------------------
	// constructors	
	public Entry()
	{
	}
	
	public Entry(String lastName, String priority, String complex, String streetNum, String streetName, String streetType,
			String apt, String cityStateZip, String phone)
	{
		this.setLastName(lastName);
		this.setPriority(priority);
		this.setComplex(complex);
		this.setStreetNum(streetNum);
		this.setStreetName(streetName);
		this.setStreetType(streetType);
		this.setApt(apt);
		this.setCityStateZip(cityStateZip);
		this.setPhone(phone);
		
	}
	
	// --------------------------------------------------
	// variables
	private String lastName;
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Guardian> guardians = new ArrayList<Guardian>();
	private String priority;
	private String complex;
	private String streetNum;
	private String streetName;
	private String streetType;
	private String apt;
	private String cityStateZip;
	private String phone;
	
	// --------------------------------------------------
	// methods	
	
	public boolean containsStudent(String firstName)
	{
		boolean b = false;
		for (Student s : getStudents())
		{
			if (s.getFirstName().equals(firstName))
			{
				b = true;
				break;
			}
		}
		return b;
	}
	
	public boolean containsGuardian(String firstName)
	{
		boolean b = false;
		for (Guardian g : getGuardians())
		{
			if (g.getFirstName().equals(firstName))
			{
				b = true;
				break;
			}
		}
		return b;
	}	
	
	public Guardian getFirstPriority()
	{
		Guardian guardian = new Guardian();
		for (Guardian g : getGuardians())
		{
			if (g.getPriority().equals("1.0"))
			{
				guardian = g;
			}
		}
		return guardian;
	}
	
	public Guardian getSecondPriority()
	{
		Guardian guardian = new Guardian();
		for (Guardian g : getGuardians())
		{
			if (g.getPriority().equals("2.0"))
			{
				guardian = g;
			}
		}
		return guardian;
	}	
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public ArrayList<Guardian> getGuardians() {
		return guardians;
	}
	
	public void setGuardians(ArrayList<Guardian> guardians) {
		this.guardians = guardians;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getComplex() {
		return complex;
	}
	
	public void setComplex(String complex) {
		this.complex = complex;
	}
	
	public String getStreetNum() {
		return streetNum;
	}
	
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getStreetType() {
		return streetType;
	}
	
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	
	public String getApt() 
	{
		return apt;
	}
	
	public void setApt(String apt) {
		this.apt = apt;
	}
	
	public String getCityStateZip() {
		return cityStateZip;
	}
	
	public void setCityStateZip(String cityStateZip) {
		this.cityStateZip = cityStateZip;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

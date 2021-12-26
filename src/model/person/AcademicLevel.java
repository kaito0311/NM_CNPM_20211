package model.person;

public class AcademicLevel {
	private int level;
	private String name;
	
	public AcademicLevel(int statusID, String name) {
		super();
		this.level = statusID;
		this.name = name;
	}
	@Override
	public String toString() {
		return "EduStatus [statusID=" + level + ", name=" + name + "]";
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int statusID) {
		this.level = statusID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

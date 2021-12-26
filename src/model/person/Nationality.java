package model.person;

public class Nationality {
    private String nationalityID; 
    private String name; 

    public Nationality(String nationalityID, String name){
        this.nationalityID = nationalityID;
        this.name = name; 
    }

    public String getNationalityID() {
		return nationalityID;
	}

	public void setNationalityID(String nationalityID) {
		this.nationalityID = nationalityID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Nationality(){

    }

    @Override
    public String toString() {
        return "Nationality [name=" + name + ", nationalityID=" + nationalityID + "]";
    }
    
}
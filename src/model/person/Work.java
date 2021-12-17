package model.person;

public class Work {
	private String job;
	private String place;
	
	public Work(String job, String place) {
		super();
		this.job = job;
		this.place = place;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	
}

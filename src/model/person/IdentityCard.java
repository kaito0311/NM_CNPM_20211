package model.person;

import java.time.LocalDate;

public class IdentityCard {
	private String number;
	private LocalDate registerDate;
	private String registerPlace;
	
	public IdentityCard(String number, LocalDate registerDate, String registerPlace) {
		super();
		this.number = number;
		this.registerDate = registerDate;
		this.registerPlace = registerPlace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public String getRegisterPlace() {
		return registerPlace;
	}

	public void setRegisterPlace(String registerPlace) {
		this.registerPlace = registerPlace;
	}
	
	
}

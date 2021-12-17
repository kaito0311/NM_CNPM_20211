package model.person;

import java.time.LocalDate;

public class Person {
    private int personID;
    private String fullName; 
    private String nickName;
    private LocalDate birthDate;
    private String gender;
    private String ethinic;
    private String nationality;
    private String residence;
    private String oriPlace;
    private IdentityCard idCard;
    private TemporaryAbRe tmpAbRe;
    private Work work;
    
	public Person(int personID, String fullName, String nickName, LocalDate birthDate, String gender, String ethinic,
			String nationality) {
		super();
		this.personID = personID;
		this.fullName = fullName;
		this.nickName = nickName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.ethinic = ethinic;
		this.nationality = nationality;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getOriPlace() {
		return oriPlace;
	}
	public void setOriPlace(String oriPlace) {
		this.oriPlace = oriPlace;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public IdentityCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IdentityCard idCard) {
		this.idCard = idCard;
	}
	public TemporaryAbRe getTmpAbRe() {
		return tmpAbRe;
	}
	public void setTmpAbRe(TemporaryAbRe tmpAbRe) {
		this.tmpAbRe = tmpAbRe;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEthinic() {
		return ethinic;
	}
	public void setEthinic(String ethinic) {
		this.ethinic = ethinic;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
    
}

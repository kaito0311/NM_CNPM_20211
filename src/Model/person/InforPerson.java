package model.person;

public class InforPerson {

    private Person person = null;
    private Nationality nationality = null;
    private Ethnic ethnic = null;
    private Work work = null;
    private IdentityCard card = null;
    private OriginPlace originPlace = null;
    private BirthPlace birthPlace = null;
    private TemporaryAbsence temporaryAbsence = null;
    private TemporaryResidence temporaryResidence = null; 
    private Education education = null;
    private AcademicLevel academicLevel = null;
    private ResidenceType residenceType = null; 
    private Residence residence = null;     

    public InforPerson() {
    }

    public InforPerson(Person person, Nationality nationality, Ethnic ethnic, Work work, IdentityCard card,
			OriginPlace originPlace, BirthPlace birthPlace, TemporaryAbsence temporaryAbsence,
			TemporaryResidence temporaryResidence, Education education, AcademicLevel academicLevel,
			ResidenceType residenceType, Residence residence) {
		super();
		this.person = person;
		this.nationality = nationality;
		this.ethnic = ethnic;
		this.work = work;
		this.card = card;
		this.originPlace = originPlace;
		this.birthPlace = birthPlace;
		this.temporaryAbsence = temporaryAbsence;
		this.temporaryResidence = temporaryResidence;
		this.education = education;
		this.academicLevel = academicLevel;
		this.residenceType = residenceType;
		this.residence = residence;
	}

	@Override
	public String toString() {
		return "InforPerson [person=" + person + ", nationality=" + nationality + ", ethnic=" + ethnic + ", work="
				+ work + ", card=" + card + ", originPlace=" + originPlace + ", birthPlace=" + birthPlace
				+ ", temporaryAbsence=" + temporaryAbsence + ", temporaryResidence=" + temporaryResidence
				+ ", education=" + education + ", academicLevel=" + academicLevel + ", residenceType=" + residenceType
				+ ", residence=" + residence + "]";
	}

	public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Ethnic getEthnic() {
        return ethnic;
    }

    public void setEthnic(Ethnic ethnic) {
        this.ethnic = ethnic;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public IdentityCard getCard() {
        return card;
    }

    public void setCard(IdentityCard card) {
        this.card = card;
    }

    public OriginPlace getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(OriginPlace originPlace) {
        this.originPlace = originPlace;
    }

    public BirthPlace getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(BirthPlace birthPlace) {
        this.birthPlace = birthPlace;
    }

	public TemporaryAbsence getTemporaryAbsence() {
		return temporaryAbsence;
	}

	public void setTemporaryAbsence(TemporaryAbsence temporaryAbsence) {
		this.temporaryAbsence = temporaryAbsence;
	}

	public TemporaryResidence getTemporaryResidence() {
		return temporaryResidence;
	}

	public void setTemporaryResidence(TemporaryResidence temporaryResidence) {
		this.temporaryResidence = temporaryResidence;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	public ResidenceType getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(ResidenceType residenceType) {
		this.residenceType = residenceType;
	}

	public Residence getResidence() {
		return residence;
	}

	public void setResidence(Residence residence) {
		this.residence = residence;
	}

}
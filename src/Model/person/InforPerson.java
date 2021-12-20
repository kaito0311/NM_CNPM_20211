package Model.person;

public class InforPerson {

    private Person person = null;
    private Nationality nationality = null;
    private Ethnic ethnic = null;
    private Work work = null;
    private IdentityCard card = null;
    private OriginPlace originPlace = null;
    private BirthPlace birthPlace = null;

    public InforPerson() {
    }

    public InforPerson(Person person, Nationality nationality, Ethnic ethnic, Work work, IdentityCard card,
            OriginPlace originPlace, BirthPlace birthPlace) {
        this.person = person;
        this.nationality = nationality;
        this.ethnic = ethnic;
        this.work = work;
        this.card = card;
        this.originPlace = originPlace;
        this.birthPlace = birthPlace;
    }

    @Override
    public String toString() {
        return "Inforperson [birthPlace=" + birthPlace.toString() + ", card=" + card.toString() + ", ethnic=" + ethnic.toString() + ", nationality="
                + nationality.toString() + ", originPlace=" + originPlace.toString() + ", person=" + person.toString() + ", work=" + work.toString() + "]";
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

}

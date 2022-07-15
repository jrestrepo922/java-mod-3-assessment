public class Speciality {
    private String speciality;
    private Integer numOfTreatments;
    private Boolean canBeCure;
    private Integer gainFromEachTreatment;

    public Speciality(){

    }

    public Speciality(String speciality, Integer numOfTreatments, Boolean canBeCure, Integer gainFromEachTreatment) {
        this.speciality = speciality;
        this.numOfTreatments = numOfTreatments;
        this.canBeCure = canBeCure;
        this.gainFromEachTreatment = gainFromEachTreatment;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Integer getNumOfTreatments() {
        return numOfTreatments;
    }

    public Boolean getCanBeCure() {
        return canBeCure;
    }

    public Integer getGainFromEachTreatment() {
        return gainFromEachTreatment;
    }
}

public class Ailment {
    private String speciality;
    private String ailment;
    private Integer patientStartingHealthIndex;

    public Ailment(){

    }
    public Ailment(String speciality, String ailment, Integer patientStartingHealthIndex) {
        this.speciality = speciality;
        this.ailment = ailment;
        this.patientStartingHealthIndex = patientStartingHealthIndex;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getAilment() {
        return ailment;
    }

    public Integer getPatientStartingHealthIndex() {
        return patientStartingHealthIndex;
    }
}

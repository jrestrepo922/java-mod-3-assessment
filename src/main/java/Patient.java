public class Patient {
    private String name;
    private Ailment ailment;
    private Integer healthIndex;

    public Patient(){

    }


    public Patient(String name, Ailment ailment, Integer healthIndex) {
        this.name = name;
        this.ailment = ailment;
        this.healthIndex = healthIndex;
    }

    public String getName() {
        return name;
    }

    public Ailment getAilment() {
        return ailment;
    }

    public Integer getHealthIndex() {
        return healthIndex;
    }

    public void setHealthIndex(Integer healthIndex) {
        this.healthIndex = healthIndex;
    }
}

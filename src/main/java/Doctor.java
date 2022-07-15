import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private Speciality speciality;
    private List<Patient> patients;

    public Doctor(){

    }
    public Doctor(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
        this.patients = new ArrayList<Patient>();
    }

    public String getName() {
        return name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
    }

    public static void removePatient(Doctor doctor, Patient patient){
        // get the healthIndex from the patient
        Integer healthIndex = patient.getHealthIndex();
        String patientName = patient.getName();
        String patientAilment = patient.getAilment().getAilment();
        // if the healthIndex is >= 100 remove patient from doctor patients list
        if(healthIndex >= 100){
            System.out.println("Patient " + patientName + " is cure of " + patientAilment + ".");
            doctor.getPatients().remove(patient);
        }
    }

    public static Doctor findDoctorFromPatient(List<Doctor> doctors, Patient patient){
        // create a allPatientList
        List<Patient> allPatients = new ArrayList<Patient>();
        // iterate over the list of doctors
        for(int i = 0; i < doctors.size(); i++){
            Doctor currentDoctor = doctors.get(i);
            List<Patient> doctorPatients = currentDoctor.getPatients();

            for(int j = 0; j < doctorPatients.size(); j++){
                Patient currentPatient = doctorPatients.get(j);
                if(currentPatient.equals(patient)){
                    return currentDoctor;
                }
            }
        }

        return null;
    }


    public static void treatPatient(List<Doctor> doctors, Patient patient){
        System.out.println("Pre Treatment vital for " + patient.getName() + ": Health Index: " + patient.getHealthIndex());

        // get the correct doctor from patient
        Doctor doctor = Doctor.findDoctorFromPatient(doctors, patient);

        // get the doctors speciality information we need canBeCure and GainFromEachTreatment
        Speciality speciality = doctor.getSpeciality();
        Boolean canBeCure = speciality.getCanBeCure();
        Integer gainFromEachTreatment = speciality.getGainFromEachTreatment();

        // add to the healthIndex of the patient base on the Gain from EachTreatment

            // if canBeCure equal False do nothing and printout Patient will never heal;
            if(canBeCure){
                patient.setHealthIndex(patient.getHealthIndex() + gainFromEachTreatment);
                // need to check if patient can be removed or not
                Doctor.removePatient(doctor, patient);
            } else {
                System.out.println(patient.getName() + " will never be cure of " + patient.getAilment().getAilment());
            }

        System.out.println("Post Treatment vital for " + patient.getName() + ": Health Index: " + patient.getHealthIndex());
            System.out.println("");
    }




}
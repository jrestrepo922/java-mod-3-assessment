import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    private String name;
    private List<Doctor> doctors;

    public Hospital(){

    }

    public Hospital(String name) {
        this.name = name;
        this.doctors = new ArrayList<Doctor>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }

    public static Hospital createHospital(Scanner scanner){
        System.out.println("Please provide the name of the Hospital");
        String nameOfHospital = scanner.nextLine();
        while(nameOfHospital.equals("") || nameOfHospital.equals(" ") ){
            System.out.println("Name of Hospital can not be empty");
            nameOfHospital = scanner.nextLine();
        }
        return new Hospital(nameOfHospital);
    }

    public static List<Speciality> createSpecialities(){
        Speciality dermatology = new Speciality("dermatology",1, true, 20);
        Speciality pediatrics = new Speciality("pediatrics",9999999, false,  0);
        Speciality radiology = new Speciality("radiology",5, true,  10);
        return new ArrayList<Speciality>(Arrays.asList(dermatology,pediatrics,radiology));
    }

    public static List<Ailment> createAilments(){
        Ailment acne = new Ailment("dermatology", "acne", 80);
        Ailment colic = new Ailment("pediatrics", "colic", 90);
        Ailment brokenBones = new Ailment("radiology", "broken bones", 50);
        return new ArrayList<Ailment>(Arrays.asList(acne,colic,brokenBones));
    }

    public static Doctor createDoctor(Scanner scanner, List<Speciality> specialities){
        System.out.println("Please provide the name of the doctor");
        String nameOfDoctor = scanner.nextLine();
        while(nameOfDoctor.equals("") || nameOfDoctor.equals(" ") ){
            System.out.println("Name of doctor can not be empty");
            nameOfDoctor = scanner.nextLine();
        }
        System.out.println("Please select the speciality by typing a number between 0 to 2");
        System.out.println("Dermatology: type 0");
        System.out.println("Pediatrics: type 1");
        System.out.println("Radiology: type 2");
        String speciality = scanner.nextLine();
        while(!(speciality.equals("0") || speciality.equals("1") || speciality.equals("2")) ||
                (speciality.equals("") || speciality.equals(" ")))
        {
            System.out.println("Please provide a number between 0 and 2");
            speciality = scanner.nextLine();
        }
        return new Doctor(nameOfDoctor, specialities.get(Integer.parseInt(speciality)));
    }

    public static Patient createPatient(Scanner scanner, List<Ailment> Ailments){
        System.out.println("Please provide the name of the patient");
        String nameOfPatient = scanner.nextLine();
        while(nameOfPatient.equals("") || nameOfPatient.equals(" ") ){
            System.out.println("Name of patient can not be empty");
            nameOfPatient = scanner.nextLine();
        }
        System.out.println("Please select the symptoms by typing a number between 0 to 2");
        System.out.println("acne: type 0");
        System.out.println("colic: type 1");
        System.out.println("broken bones: type 2");
        String symptom = scanner.nextLine();
        while(!(symptom.equals("0") || symptom.equals("1") || symptom.equals("2")) ||
                (symptom.equals("") || symptom.equals(" "))
        ){
            System.out.println("Please provide a number between 0 and 2");
            symptom = scanner.nextLine();
        }
        Ailment selectedAilment = Ailments.get(Integer.parseInt(symptom));
        Integer patientStartingHealthIndex = selectedAilment.getPatientStartingHealthIndex();
        return new Patient(nameOfPatient, selectedAilment,patientStartingHealthIndex);
    }

    public static void doctorToPatientMapping(List<Doctor> doctors, Patient patient){
        // for loop that iterates over every doctor
        for(int i = 0; i < doctors.size(); i++){

            // every doctor needs to check the patients and see if the patient's symptoms is something
            // they specialize in
            Doctor doctor = doctors.get(i);
            String doctorSpeciality = doctor.getSpeciality().getSpeciality();
            String patientAilment = patient.getAilment().getAilment();

            if(doctorSpeciality.equals("dermatology") && patientAilment.equals("acne")){
                doctor.addPatient(patient);
                return;
            } else if(doctorSpeciality.equals("pediatrics") && patientAilment.equals("colic")){
                doctor.addPatient(patient);
                return;
            } else if(doctorSpeciality.equals("radiology")  && patientAilment.equals("broken bones")){
                doctor.addPatient(patient);
                return;
            }
        }
    }

    public static List<Patient> getAllPatients(Hospital hospital){
        // get a list of all doctors
        List<Doctor> doctors = hospital.getDoctors();
        // create a allPatientList
        List<Patient> allPatients = new ArrayList<Patient>();
        // iterate over the list of doctors
        for(int i = 0; i < doctors.size(); i++){
            Doctor currentDoctor = doctors.get(i);
            List<Patient> doctorPatients = currentDoctor.getPatients();

            for(int j = 0; j < doctorPatients.size(); j++){
                Patient currentPatient = doctorPatients.get(j);
                allPatients.add(currentPatient);
            }
        }

        // return new list of patients
        return allPatients;
    }

    public static Patient selectPatient(Hospital hospital, Scanner scanner){
        // create a list of patients;
        List<Patient> allPatient = Hospital.getAllPatients(hospital);

        //provide user with the name and number to type to select a user;
        System.out.println("Please choose a patient that will get treatment");
        for(int i = 0; i < allPatient.size(); i++){
            Patient currentPatient = allPatient.get(i);
            String patientName = currentPatient.getName();
            String patientAilment = currentPatient.getAilment().getAilment();
            System.out.println("Type " + (i) +  " for " + patientName + " who  suffers from " + patientAilment);
        }
        String chosenValue = scanner.nextLine();
        if(!chosenValue.matches("[0-9]+")){
            System.out.println("The value needs to be a number");
            System.out.println("The first patient is autoSelected");
            chosenValue = "0";

        }
        Integer chosenValueInt = Integer.parseInt(chosenValue);
        Integer upperBound = allPatient.size() -1;
        Integer lowerBound = 0;
        while(upperBound < chosenValueInt || chosenValueInt < lowerBound){
            System.out.println("Number must be between " + lowerBound + " and " + upperBound);
            chosenValue = scanner.nextLine();
            chosenValueInt = Integer.parseInt(chosenValue);
        }
        // return selected patient
        return allPatient.get(chosenValueInt);
    }




}
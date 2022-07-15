import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // your code here
        try(Scanner scanner = new Scanner(System.in)){
            // if it does exist use the skip the world creation and just print out the world
            File hospitalFile = new File("hospital" + ".json");
            Hospital hospital = null;
            if(hospitalFile.exists()){
                hospital = FileIORunner.hospitalObjectFromJSON("hospital");
                Main.printTheWorld(hospital);

            } // if file does not exist create the world manually
            else {
                //hospital = generateDummyData();
                hospital = createHospitalWorld(scanner);
                FileIORunner.saveHospitaltAsJSON(hospital, "hospital");
            }
            additionalUserFunctionality(hospital, scanner);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private static Hospital generateDummyData(){
        Hospital hospital = new Hospital("San Martin");
        List<Speciality> allSpecialities = Hospital.createSpecialities();
        // create 3 doctors
        Doctor d1 = new Doctor("doctor 1", allSpecialities.get(0));
        Doctor d2 = new Doctor("doctor 2", allSpecialities.get(1));
        Doctor d3 = new Doctor("doctor 3", allSpecialities.get(2));
        List<Doctor> doctors = new ArrayList<Doctor>(Arrays.asList(d1,d2,d3));

        // create 5 patients
        List<Ailment> allAilments = Hospital.createAilments();
        Patient p1 = new Patient("patient 1", allAilments.get(0), 80);
        Patient p2 = new Patient("patient 2", allAilments.get(1), 90);
        Patient p3 = new Patient("patient 3", allAilments.get(2), 50);
        Patient p4 = new Patient("patient 4", allAilments.get(0), 80);
        Patient p5 = new Patient("patient 5", allAilments.get(1), 90);

        // assign patient to doctor
        d1.addPatient(p1);
        d1.addPatient(p4);
        d2.addPatient(p2);
        d2.addPatient(p5);
        d3.addPatient(p3);

        hospital.setDoctors(doctors);
        return hospital;
    }

    private static Hospital createHospitalWorld(Scanner scanner){
        // create hospital
        System.out.println("============ " + "Welcome to the Hospital Management System" + " ==============");
        Hospital hospital = Hospital.createHospital(scanner);

        // create a total of 3 the specialities that will be added to the doctor
        List<Speciality> allSpecialities = Hospital.createSpecialities();

        //create a total of 3 doctors and add them to the hospital
        System.out.println("A total of 3 doctors needs to be added");
        for(Integer i = 0; i < 3; i++){
            // createDoctor also needs to map the correct specialty to the doctor
            Doctor newDoctor = Hospital.createDoctor(scanner, allSpecialities);
            hospital.addDoctor(newDoctor);
        }

        // create a total of 3 ailments to be use for patients
        List<Ailment> allAilments = Hospital.createAilments();

        System.out.println("A total of 5 patients needs to be added");
        // create a total of 5 patients and map their ailment
        for(Integer i = 0; i < 5; i++){
            Patient newPatient = Hospital.createPatient(scanner, allAilments);
            // Match the patient to the doctor based on ailment
            Hospital.doctorToPatientMapping(hospital.getDoctors(), newPatient);
        }

        // now print out the world
        Main.printTheWorld(hospital);

        return hospital;
    }

    private static void printTheWorld(Hospital hospital){
        List<Doctor> doctors = hospital.getDoctors();

        System.out.println("");
        System.out.println(" ******************* Hospital Management System Information ****************************");
        System.out.println("");
        System.out.println("Hospital: " + hospital.getName());
        System.out.println("");
        System.out.println("======= Doctors and their patients =========");
        System.out.println("");
        for(int i = 0; i < doctors.size(); i++){
            Doctor doctor = doctors.get(i);
            String doctorSpeciality = doctor.getSpeciality().getSpeciality();
            System.out.println("Doctor: " + doctor.getName() + ", Speciality: " + doctorSpeciality);
            System.out.println("     List of Patients");
            List<Patient> patients = doctor.getPatients();
            for(int j = 0; j < patients.size(); j++){
                Patient patient = patients.get(j);
                String patientAilment = patient.getAilment().getAilment();

                System.out.println("      Patient Name: " + patient.getName() + ", Ailment: " + patientAilment);
                System.out.println("");
            }
            System.out.println("");
            System.out.println("-----------------------");
            System.out.println("");
        }
    }

    private static void additionalUserFunctionality(Hospital hospital, Scanner scanner) throws Exception {
        List<Doctor> doctors = hospital.getDoctors();

        String selection = "0";
        System.out.println("Please choose what you will like to do ?");
        // create a while loop that continues unless the option is 3
            while(!selection.equals("2")){
                // print out the options to the user
                System.out.println("Type 0: treating a chosen patient");
                System.out.println("Type 1: adding a new patient");
                System.out.println("Type 2: exit the system");
                // get user input
                selection = scanner.nextLine();
                while(!(selection.equals("0") || selection.equals("1") || selection.equals("2")) ||
                        (selection.equals("") || selection.equals(" "))) {
                    System.out.println("Type 0: treating a chosen patient");
                    System.out.println("Type 1: adding a new patient");
                    System.out.println("Type 2: exit the system");
                    selection = scanner.nextLine();
                }
                // treat a patient
                if(selection.equals("0")){
                    //get all patients
                   Patient patient = Hospital.selectPatient(hospital, scanner);
                   // call the treatment method
                    Doctor.treatPatient(doctors, patient);
                } // add a patient
                else if(selection.equals("1")){
                    List<Ailment> ailments = Hospital.createAilments();
                    Patient patient = Hospital.createPatient(scanner, ailments);
                    Hospital.doctorToPatientMapping(doctors, patient);
                }

            }




        // program closing down comment
        System.out.println("Program is now close but your information has been saved!");
            FileIORunner.saveHospitaltAsJSON(hospital, "hospital");
    }

}

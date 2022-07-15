import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileIORunner {
    static void printHospitalAsJSON(Hospital hospital) throws Exception {
        String json = new ObjectMapper().writeValueAsString(hospital);
        System.out.println(json);
    }

    static void saveHospitaltAsJSON(Hospital hospital, String fileName) throws IOException, Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName+ ".json"), hospital);
        printHospitalAsJSON(hospital);
    }

    static Hospital hospitalObjectFromJSON(String fileName)  {
        Hospital restoredHospital = null;
        try{

            restoredHospital = new ObjectMapper().readValue(new File(fileName + ".json"), Hospital.class);

        } catch (Exception e){
            e.printStackTrace();
        }
        return restoredHospital;
    }
}

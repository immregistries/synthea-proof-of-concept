package eu.telecomnancy.syntheaproofofconcept;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan(basePackages = "eu.telecomnancy.syntheaproofofconcept")
public class PatientTestServicesTests {

    @Autowired
    private PatientTestService patientTestService;

    @Test
    public void createPatientTest() {
        patientTestService.createPatient(1);
    }

}

package eu.telecomnancy.syntheaproofofconcept;

import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.helpers.Config;
import org.mitre.synthea.modules.Immunizations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PatientTestService {

    public void createPatient(int i) {
        Generator.GeneratorOptions options = new Generator.GeneratorOptions();
        options.population = i;
        options.enabledModules = new ArrayList<>();
        options.enabledModules.add(Immunizations.IMMUNIZATIONS);
        Config.set("exporter.hospital.fhir.export", "false");
        Config.set("exporter.practitioner.fhir.export", "false");
        Generator generator = new Generator(options);
        generator.run();
    }
}

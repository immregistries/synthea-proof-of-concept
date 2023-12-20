package eu.telecomnancy.syntheaproofofconcept;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan(basePackages = "eu.telecomnancy.syntheaproofofconcept")
public class ReadXmlDomParserTests {

    private ReadXmlDomParser parser = new ReadXmlDomParser();

    @Test
    public void test() {
        parser.buildRessource();
    }
}

package eu.telecomnancy.syntheaproofofconcept;

import jakarta.annotation.PostConstruct;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.XMLConstants;

public class ReadXmlDomParser {

    //@PostConstruct
    public void buildRessource() {

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        URL url = this.getClass().getClassLoader().getResource("polio.xml");

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(url.toURI()));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("series");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // we can ask for default series even if it's not at the root of element
                    // Be careful, when field is empty, give an empty String "", not null.
                    // It means there is still an element that exist, explaining why it doesn't raise an exception

                    String defaultSeries = element.getElementsByTagName("defaultSeries").item(0).getTextContent();
                    // if it is a default series: we work
                    if (defaultSeries.equals("Yes")) {
                        // get name
                        // /!\ useless
                        String name = element.getElementsByTagName("seriesName").item(0).getTextContent();
                        System.out.println("Series Name : " + name);

                        String minAgeToStart = element.getElementsByTagName("minAgeToStart").item(0).getTextContent();
                        System.out.println("Min Age To Start : " + minAgeToStart);
                        String maxAgeToStart = element.getElementsByTagName("maxAgeToStart").item(0).getTextContent();
                        System.out.println("Max Age To Start : " + maxAgeToStart);

                        // get doses list
                        NodeList doses = element.getElementsByTagName("seriesDose");
                        for (int j = 0; j < doses.getLength(); j++ ) {
                            System.out.println("    ----------");

                            Element dose = (Element) doses.item(j);  // No need for if we are sure that it is a ELEMENT_NODE

                            String doseNumber = dose.getElementsByTagName("doseNumber").item(0).getTextContent();
                            System.out.println("    Dose Number : " + doseNumber);

                            Element age = (Element) dose.getElementsByTagName("age").item(0); // same
                            String earliestRecAge = age.getElementsByTagName("earliestRecAge").item(0).getTextContent();
                            System.out.println("    Earliest Rec Age : " + earliestRecAge);
                            String latestRecAge = age.getElementsByTagName("latestRecAge").item(0).getTextContent();
                            System.out.println("    Latest Rec Age : " + latestRecAge);

                            NodeList vaccins = dose.getElementsByTagName("preferableVaccine");
                            for (int k = 0; k < vaccins.getLength(); k++) {
                                Element vaccin = (Element) vaccins.item(k);
                                System.out.println("        "
                                        + vaccin.getElementsByTagName("vaccineType").item(0).getTextContent()
                                        + ", CVX: "
                                        + vaccin.getElementsByTagName("cvx").item(0).getTextContent());
                                System.out.println("        Vaccine Type Begin Age: "
                                        + vaccin.getElementsByTagName("beginAge").item(0).getTextContent()
                                        + ", Vaccine Type End Age: "
                                        + vaccin.getElementsByTagName("endAge").item(0).getTextContent());
                            }

                            String recurringDose = dose.getElementsByTagName("recurringDose").item(0).getTextContent();
                            System.out.println("    Recurring Dose : " + recurringDose);
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException | NullPointerException e ) {
            e.printStackTrace();
        }

    }
}

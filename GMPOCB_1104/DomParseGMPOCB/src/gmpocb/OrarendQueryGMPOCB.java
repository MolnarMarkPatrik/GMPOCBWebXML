package gmpocb;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

public class OrarendQueryGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("OrarendGMPOCB.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList oraList = doc.getElementsByTagName("ora");

            System.out.println("--- Hétfői órák ---");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element e = (Element) oraList.item(i);
                Element idopont = (Element) e.getElementsByTagName("idopont").item(0);
                if (idopont.getElementsByTagName("nap").item(0).getTextContent().equalsIgnoreCase("Hétfő")) {
                    System.out.println(e.getElementsByTagName("targy").item(0).getTextContent());
                }
            }

            System.out.println("\n--- Gyakorlati órák ---");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element e = (Element) oraList.item(i);
                if (e.getAttribute("típus").equalsIgnoreCase("gyakorlat")) {
                    System.out.println(e.getElementsByTagName("targy").item(0).getTextContent());
                }
            }

            System.out.println("\n--- In/101 teremben tartott órák ---");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element e = (Element) oraList.item(i);
                if (e.getElementsByTagName("helyszin").item(0).getTextContent().contains("In/101")) {
                    System.out.println(e.getElementsByTagName("targy").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
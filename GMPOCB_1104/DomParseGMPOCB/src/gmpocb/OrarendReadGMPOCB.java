package gmpocb;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

public class OrarendReadGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ora");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("\nAktuális elem: " + eElement.getNodeName());
                    System.out.println("Óra ID: " + eElement.getAttribute("id"));
                    System.out.println("Típus: " + eElement.getAttribute("típus"));
                    System.out.println("Tárgy: " + eElement.getElementsByTagName("targy").item(0).getTextContent());
                    
                    Element idopont = (Element) eElement.getElementsByTagName("idopont").item(0);
                    System.out.println("Időpont:\n  Nap: " + idopont.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("  ÓraTól: " + idopont.getElementsByTagName("tol").item(0).getTextContent());
                    System.out.println("  ÓraIg: " + idopont.getElementsByTagName("ig").item(0).getTextContent());
                    
                    System.out.println("Helyszín: " + eElement.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktató: " + eElement.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak: " + eElement.getElementsByTagName("szak").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
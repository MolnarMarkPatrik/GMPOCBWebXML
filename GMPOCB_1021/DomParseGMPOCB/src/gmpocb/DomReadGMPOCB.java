package gmpocb;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

public class DOMReadGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_XML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            
            NodeList nList = doc.getElementsByTagName("Etterem");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Current Element: " + eElement.getNodeName());
                    System.out.println("Étterem ID: " + eElement.getAttribute("Ekod"));
                    System.out.println("Név: " + eElement.getElementsByTagName("nev").item(0).getTextContent());
                    
                    Element cim = (Element) eElement.getElementsByTagName("Cim").item(0);
                    System.out.println("Cím: " + cim.getElementsByTagName("Varos").item(0).getTextContent() + ", " 
                                     + cim.getElementsByTagName("Utca").item(0).getTextContent() + " " 
                                     + cim.getElementsByTagName("Hazszam").item(0).getTextContent());
                    System.out.println("Csillag: " + eElement.getElementsByTagName("Csillag").item(0).getTextContent());
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package domgmpocb;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadGMPOCB {

    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_orarend.xml");
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            
            doc.getDocumentElement().normalize();
            
            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");
            
            NodeList nList = doc.getElementsByTagName("ora");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    System.out.println("Óra ID: " + eElement.getAttribute("id"));
                    System.out.println("Típus: " + eElement.getAttribute("típus"));
                    System.out.println("  Tárgy: " + eElement.getElementsByTagName("targy").item(0).getTextContent());
                    
                    Node idopontNode = eElement.getElementsByTagName("idopont").item(0);
                    if (idopontNode != null && idopontNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element idopontElement = (Element) idopontNode;
                        System.out.println("  Időpont: " 
                            + idopontElement.getElementsByTagName("nap").item(0).getTextContent() + " "
                            + idopontElement.getElementsByTagName("tol").item(0).getTextContent() + "-"
                            + idopontElement.getElementsByTagName("ig").item(0).getTextContent());
                    }
                    
                    System.out.println("  Helyszín: " + eElement.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("  Oktató: " + eElement.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("  Szak: " + eElement.getElementsByTagName("szak").item(0).getTextContent());
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
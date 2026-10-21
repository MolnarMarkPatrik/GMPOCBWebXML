package gmpocb;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

public class DOMQueryGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_XML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("--- Szakközépiskolát végzett szakácsok ---");
            NodeList szakacsList = doc.getElementsByTagName("Szakacs");
            for (int i = 0; i < szakacsList.getLength(); i++) {
                Element e = (Element) szakacsList.item(i);
                if (e.getElementsByTagName("Vegzettseg").item(0).getTextContent().toLowerCase().contains("szakközépiskola")) {
                    System.out.println(e.getElementsByTagName("Nev").item(0).getTextContent());
                }
            }

            System.out.println("\n--- 5 csillagos éttermek ---");
            NodeList etteremList = doc.getElementsByTagName("Etterem");
            for (int i = 0; i < etteremList.getLength(); i++) {
                Element e = (Element) etteremList.item(i);
                if (e.getElementsByTagName("Csillag").item(0).getTextContent().equals("5")) {
                    System.out.println(e.getElementsByTagName("nev").item(0).getTextContent());
                }
            }

            System.out.println("\n--- Délutános gyakornokok ---");
            NodeList gyakornokList = doc.getElementsByTagName("Gyakornok");
            for (int i = 0; i < gyakornokList.getLength(); i++) {
                Element e = (Element) gyakornokList.item(i);
                if (e.getElementsByTagName("Muszak").item(0).getTextContent().toLowerCase().contains("délután")) {
                    System.out.println(e.getElementsByTagName("Nev").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
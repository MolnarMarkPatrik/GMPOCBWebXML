package xpathneptunkod;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;

public class xPathQueryNeptunkod {
    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_orarend2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            System.out.println("--- 1. Lekérdezés: Az összes gyógakorlat típusú óra ---");
            NodeList gyakorlatok = (NodeList) xPath.compile("//ora[@típus='gyakorlat']").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < gyakorlatok.getLength(); i++) {
                Element ora = (Element) gyakorlatok.item(i);
                System.out.println("- " + ora.getElementsByTagName("targy").item(0).getTextContent());
            }

            System.out.println("\n--- 2. Lekérdezés: Keddi napon tartott tárgyak ---");
            NodeList keddiOrak = (NodeList) xPath.compile("//ora[idopont/nap='Kedd']").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < keddiOrak.getLength(); i++) {
                Element ora = (Element) keddiOrak.item(i);
                System.out.println("- " + ora.getElementsByTagName("targy").item(0).getTextContent());
            }

            System.out.println("\n--- 3. Lekérdezés: Dr. Agárdi Anita által tartott órák ---");
            NodeList tanarOrak = (NodeList) xPath.compile("//ora[oktato='Dr. Agárdi Anita']").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < tanarOrak.getLength(); i++) {
                Element ora = (Element) tanarOrak.item(i);
                System.out.println("- " + ora.getElementsByTagName("targy").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
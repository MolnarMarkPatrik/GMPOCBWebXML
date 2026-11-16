package xpathgmpocb;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;

public class xPathQueryOrarendGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            System.out.println("--- 1. Lekérdezés: Az összes ELMÉLET típusú óra ---");
            NodeList elmeletek = (NodeList) xPath.compile("//ora[@típus='elmélet']").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < elmeletek.getLength(); i++) {
                Element ora = (Element) elmeletek.item(i);
                System.out.println("- " + ora.getElementsByTagName("targy").item(0).getTextContent());
            }

            System.out.println("\n--- 2. Lekérdezés: Ki tanítja az Elektrotechnikát? ---");
            NodeList oktatok = (NodeList) xPath.compile("//ora[targy='Elektrotechnika']/oktato").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < oktatok.getLength(); i++) {
                System.out.println("- " + oktatok.item(i).getTextContent());
            }

            System.out.println("\n--- 3. Lekérdezés: Milyen órák vannak Szerdán? ---");
            NodeList szerdaiOrak = (NodeList) xPath.compile("//ora[idopont/nap='Szerda']").evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < szerdaiOrak.getLength(); i++) {
                Element ora = (Element) szerdaiOrak.item(i);
                System.out.println("- " + ora.getElementsByTagName("targy").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
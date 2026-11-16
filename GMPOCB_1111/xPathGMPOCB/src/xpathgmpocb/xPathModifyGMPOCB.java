package xpathgmpocb;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.File;

public class xPathModifyGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("studentGMPOCB.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String kifejezes = "/class/student[@id='01']/keresztnev";
            Node keresztnevNode = (Node) xPath.compile(kifejezes).evaluate(doc, XPathConstants.NODE);

            if (keresztnevNode != null) {
                keresztnevNode.setTextContent("Tivadar");
                System.out.println("Módosítás sikeres!");

                Node studentNode = keresztnevNode.getParentNode();
                Element studentElement = (Element) studentNode;

                System.out.println("\nA módosított diák adatai:");
                System.out.println("ID: " + studentElement.getAttribute("id"));
                System.out.println("Keresztnév: " + studentElement.getElementsByTagName("keresztnev").item(0).getTextContent());
                System.out.println("Vezetéknév: " + studentElement.getElementsByTagName("vezeteknev").item(0).getTextContent());
                System.out.println("Becenév: " + studentElement.getElementsByTagName("becenev").item(0).getTextContent());
                System.out.println("Kor: " + studentElement.getElementsByTagName("kor").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
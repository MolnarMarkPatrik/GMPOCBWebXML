package xpathneptunkod;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.File;

public class xPathModifyNeptunkod {
    public static void main(String[] args) {
        try {
            File inputFile = new File("orarendGMPOCB3.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            Node helyszinNode = (Node) xPath.compile("//ora[@id='1']/helyszin").evaluate(doc, XPathConstants.NODE);
            if (helyszinNode != null) {
                helyszinNode.setTextContent("In/202");
            }

            Node ora2Node = (Node) xPath.compile("//ora[@id='2']").evaluate(doc, XPathConstants.NODE);
            if (ora2Node != null && ora2Node.getNodeType() == Node.ELEMENT_NODE) {
                Element ora2 = (Element) ora2Node;
                ora2.setAttribute("típus", "gyakorlat");
            }

            Node oktatoNode = (Node) xPath.compile("//ora[@id='3']/oktato").evaluate(doc, XPathConstants.NODE);
            if (oktatoNode != null) {
                oktatoNode.setTextContent("Új Oktató");
            }

            System.out.println("A módosítások sikeresen megtörténtek!\n");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            
            transformer.transform(source, new StreamResult(System.out));
            
            transformer.transform(source, new StreamResult(new File("orarendGMPOCB1.xml")));
            System.out.println("\nFájl sikeresen mentve: orarendGMPOCB1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
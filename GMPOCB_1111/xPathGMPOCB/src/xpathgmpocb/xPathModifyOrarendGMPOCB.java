package xpathgmpocb;

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

public class xPathModifyOrarendGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            Node helyszinNode = (Node) xPath.compile("//ora[@id='1']/helyszin").evaluate(doc, XPathConstants.NODE);
            if (helyszinNode != null) {
                helyszinNode.setTextContent("Nagyelőadó");
            }

            Node ora2Node = (Node) xPath.compile("//ora[@id='2']").evaluate(doc, XPathConstants.NODE);
            if (ora2Node != null && ora2Node.getNodeType() == Node.ELEMENT_NODE) {
                Element ora2 = (Element) ora2Node;
                ora2.setAttribute("típus", "távoktatás");
            }

            Node targyNode = (Node) xPath.compile("//ora[targy='Angol műszaki szaknyelv 1']/targy").evaluate(doc, XPathConstants.NODE);
            if (targyNode != null) {
                targyNode.setTextContent("Szakmai Angol");
            }
            
            System.out.println("A 3 módosítás sikeresen megtörtént az XML struktúrában!");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
            StreamResult fileResult = new StreamResult(new File("orarendGMPOCB1.xml"));
            transformer.transform(source, fileResult);
            
            System.out.println("\nA módosított fájl sikeresen mentve: orarendGMPOCB1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
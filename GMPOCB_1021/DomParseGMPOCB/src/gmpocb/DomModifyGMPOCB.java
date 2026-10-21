package gmpocb;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;

public class DOMModifyGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("GMPOCB_XML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList vendegList = doc.getElementsByTagName("Vendeg");
            for (int i = 0; i < vendegList.getLength(); i++) {
                Element vendeg = (Element) vendegList.item(i);
                if (vendeg.getAttribute("Vkod").equals("v1")) {
                    vendeg.getElementsByTagName("Nev").item(0).setTextContent("Módosított Név");
                    vendeg.getElementsByTagName("Eletkor").item(0).setTextContent("99");
                }
            }

            NodeList gyakornokList = doc.getElementsByTagName("Gyakornok");
            for (int i = 0; i < gyakornokList.getLength(); i++) {
                Element gyakornok = (Element) gyakornokList.item(i);
                gyakornok.setAttribute("E_GY", "e3");
            }

            NodeList rendelesList = doc.getElementsByTagName("Rendeles");
            for (int i = rendelesList.getLength() - 1; i >= 0; i--) {
                Node rendeles = rendelesList.item(i);
                rendeles.getParentNode().removeChild(rendeles);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
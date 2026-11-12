package gmpocb;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;

public class OrarendModifyGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("OrarendGMPOCB.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = oraList.getLength() - 1; i >= 0; i--) {
                Element ora = (Element) oraList.item(i);
                String id = ora.getAttribute("id");
                
                if (id.equals("1")) {
                    ora.getElementsByTagName("helyszin").item(0).setTextContent("Új Labor 101");
                }

                else if (id.equals("2")) {
                    ora.setAttribute("típus", "Távoktatás");
                }

                else if (id.equals("3")) {
                    ora.getParentNode().removeChild(ora);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(System.out));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
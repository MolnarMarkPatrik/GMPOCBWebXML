package xpathgmpocb;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class xPathGMPOCB {
    public static void main(String[] args) {
        try {
            File inputFile = new File("studentGMPOCB.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();
            PrintWriter writer = new PrintWriter(new FileWriter("student1GMPOCB.txt"));

            
            // 1) Válassza ki az összes student element, amely a class gyermekei!
            //futtat(xPath, doc, writer, "1. Összes student a class alatt", "/class/student");
            
            // 2) Student elem, amely rendelkezik "id" attribútummal és értéke "02"
            //futtat(xPath, doc, writer, "2. A 02-es ID-jú student", "//student[@id='02']");
            
            // 3) Összes student elem, függetlenül attól, hogy hol vannak a dokumentumban
            //futtat(xPath, doc, writer, "3. Bárhol lévő student elemek", "//student");
            
            // 4) Második student element, amely a class gyermeke
            //futtat(xPath, doc, writer, "4. Második student elem", "/class/student[2]");
            
            // 5) Utolsó student elem
            //futtat(xPath, doc, writer, "5. Utolsó student elem", "/class/student[last()]");
            
            // 6) Utolsó előtti student elem
            //futtat(xPath, doc, writer, "6. Utolsó előtti student", "/class/student[last()-1]");
            
            // 7) Első két student elem
            //futtat(xPath, doc, writer, "7. Első két student", "/class/student[position()<=2]");
            
            // 8) A class root element összes gyermek eleme
            //futtat(xPath, doc, writer, "8. A class összes gyermeke", "/class/*");
            
            // 9) Összes student elem, amely rendelkezik legalább egy attribútummal
            //futtat(xPath, doc, writer, "9. Attribútummal rendelkező studentek", "//student[@*]");
            
            // 10) A dokumentum összes eleme
            //futtat(xPath, doc, writer, "10. Dokumentum összes eleme", "//*");
            
            // 11) Student elem, amelynél a kor > 20
            //futtat(xPath, doc, writer, "11. 20 évnél idősebbek", "/class/student[kor>20]");
            
            // 12) Összes student keresztnev VAGY vezeteknev csomópontja
            //futtat(xPath, doc, writer, "12. Kereszt- és vezetéknevek", "//student/keresztnev | //student/vezeteknev");

            writer.close();
            System.out.println("\nFájlba írás sikeres: student1GMPOCB.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void futtat(XPath xPath, Document doc, PrintWriter writer, String cim, String kifejezes) throws XPathExpressionException {
        String fejléc = "\n" + cim + " (XPath: " + kifejezes + ")";
        System.out.println(fejléc);
        writer.println(fejléc);

        NodeList nodeList = (NodeList) xPath.compile(kifejezes).evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;
                String id = e.getAttribute("id");
                String kimenet = " -> " + e.getNodeName() + (id.isEmpty() ? "" : " (ID: " + id + ")") 
                               + " | Tartalom: " + e.getTextContent().replaceAll("\\s+", " ").trim();
                System.out.println(kimenet);
                writer.println(kimenet);
            }
        }
    }
}
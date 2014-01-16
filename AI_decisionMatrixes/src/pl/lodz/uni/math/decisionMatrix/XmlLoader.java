package pl.lodz.uni.math.decisionMatrix;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlLoader {
    public static ArrayList<Rule> loadXml(String name)
            throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory
                .newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(name);
        XPath xpath = XPathFactory.newInstance().newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = xpath.compile("//rule");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        ArrayList<Rule> examples = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            int id= new Integer(nodes.item(i).getAttributes()
                    .getNamedItem("id").getNodeValue());
            int numberOfDoors = new Integer(nodes.item(i).getAttributes()
                    .getNamedItem("numberOfDoors").getNodeValue());
            int enginePower = new Integer(nodes.item(i).getAttributes()
                    .getNamedItem("enginePower").getNodeValue());
            String color = new String(nodes.item(i).getAttributes()
                    .getNamedItem("color").getNodeValue());
            DecisionAttribute brand;
            String brandString = nodes.item(i).getAttributes()
                    .getNamedItem("brand").getNodeValue();
            switch (brandString) {
            case "Opel":
                brand = DecisionAttribute.Opel;
                break;
            case "Nissan":
                brand = DecisionAttribute.Nissan;
                break;
            case "Ferrari":
                brand = DecisionAttribute.Ferrari;
                break;
            default:
                brand = DecisionAttribute.Ferrari;
                break;
            }

            examples.add(new Rule(id, numberOfDoors, enginePower, color, brand));

        }
        return examples;
    }
}

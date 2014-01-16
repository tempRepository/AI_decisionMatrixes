package pl.lodz.uni.math.decisionMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Rule> rules = XmlLoader.loadXml("examples.xml");
            //ArrayList<Rule> rules = XmlLoader.loadXml("wellExamples.xml");
            ArrayList<Rule> positiveRegion = Rule.lowerApproximation(rules);
            Collections.sort(positiveRegion);
            if (positiveRegion.size() / rules.size() == 1) {
                System.out.println("Well defined");
                for (Rule rule : positiveRegion) {
                    System.out.println(rule.toString());
                }
            } else {
                System.out.println("Poorly defined :( \nWell defined version:");
                for (Rule rule : positiveRegion) {
                    System.out.println(rule.toString());
                }
                System.out.println("\nNon-deterministic rules: ");
                for (Rule rule : rules) {
                    if (!positiveRegion.contains(rule)) {
                        System.out.println(rule);
                    }
                }
            }
        } catch (XPathExpressionException | ParserConfigurationException
                | SAXException | IOException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
        }

    }

}

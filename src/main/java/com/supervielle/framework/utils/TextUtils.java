package com.supervielle.framework.utils;

import java.io.File;

import java.io.IOException;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cucumber.listener.Reporter;

public class TextUtils {

    public static String normalize(String text) {
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        System.out.println("Text: " + text);
        text = text.replaceAll("[^\\p{ASCII}]", "");
        System.out.println("Normalized: " + text);
        return text;
    }

    public static void addResponseToReport(String xmlSource, String name) {
        // Parse the given input
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = builder.parse(new InputSource(new StringReader(xmlSource)));
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Write the parsed document to an xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        File dir = new File(System.getProperty("user.dir") + "/target/cucumber-reports/responses");
        if (!dir.exists())
            dir.mkdirs();
        name = name + System.currentTimeMillis();
        StreamResult result = new StreamResult(
                new File(System.getProperty("user.dir") + "/target/cucumber-reports/responses/" + name + ".xml"));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Reporter.addStepLog("<a href='file:" + dir + "/" + name + ".xml'>Response</a>");

    }

    public static void addRequestToReport(String xmlSource, String name) {
        // Parse the given input
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = builder.parse(new InputSource(new StringReader(xmlSource)));
        } catch (SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Write the parsed document to an xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        File dir = new File(System.getProperty("user.dir") + "/target/cucumber-reports/requests");
        if (!dir.exists())
            dir.mkdirs();
        name = name + System.currentTimeMillis();
        StreamResult result = new StreamResult(
                new File(System.getProperty("user.dir") + "/target/cucumber-reports/requests/" + name + ".xml"));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Reporter.addStepLog("<a href='file:" + dir + "/" + name + ".xml'>Request</a>");

    }

    // Method for adding logs passed from test cases
    public static void reportLog(String message) {
        Reporter.addStepLog(message);

    }

    //Method to extract a value from text using regular expression
    public static String extractValues(String text,String regex){
        Pattern ptn = Pattern.compile(regex);
        Matcher mtch = ptn.matcher(text);
        if(mtch.find()){
            return mtch.group();
        }
        return StringUtils.EMPTY;
    }
    
}

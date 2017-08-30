package com.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class JaxpUtil {
   public static Document getDocument() throws ParserConfigurationException, IOException, SAXException {
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = dbf.newDocumentBuilder();
       Document document = builder.parse("src/exam.xml");
       return  document;
   }
   public static void write2xml (Document document) throws TransformerException {
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer ts = tf.newTransformer();
       ts.transform(new DOMSource(document),new StreamResult("src/exam.xml"));
   }
}

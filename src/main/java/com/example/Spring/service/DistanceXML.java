package com.example.Spring.service;

import com.example.Spring.domain.Distance;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class DistanceXML {
    private static ArrayList<Distance> distances = new ArrayList<>();

    public static ArrayList<Distance> XMLReader(MultipartFile file) throws ParserConfigurationException, SAXException, IOException {
        if (file == null) {
            return null;
        }
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse(file.getInputStream(), handler);
            return distances;
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Record")) {
                if (attributes.getValue("type").equals("HKQuantityTypeIdentifierDistanceWalkingRunning")) {
                    String name = attributes.getValue("sourceName");
                    LocalDateTime CD = LocalDateTime.parse(attributes.getValue("creationDate").substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    Double value = Double.parseDouble(attributes.getValue("value"));
                    distances.add(new Distance(name, CD, value));
                }
            }
        }

    }
}
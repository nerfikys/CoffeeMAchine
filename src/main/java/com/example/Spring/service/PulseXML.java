package com.example.Spring.service;

import com.example.Spring.domain.Pulse;
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


public class PulseXML {
    private static ArrayList<Pulse> pulses = new ArrayList<>();

    public static ArrayList<Pulse> XMLReader(MultipartFile file) throws ParserConfigurationException, SAXException, IOException {
        if (file == null) {
            return null;
        }
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse(file.getInputStream(), handler);
            return pulses;
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Record")) {
                if (attributes.getValue("type").equals("HKQuantityTypeIdentifierHeartRateVariabilitySDNN")) {
                    String name = attributes.getValue("sourceName");
                    LocalDateTime CD = LocalDateTime.parse(attributes.getValue("creationDate").substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String value = attributes.getValue("value");
                    pulses.add(new Pulse(name, CD, value));
                }
            }
        }

    }
}
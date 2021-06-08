package com.example.Spring.service;

import com.example.Spring.domain.Weight;
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

public class WeightXML {
    private ArrayList<Weight> weights = new ArrayList<>();

    public ArrayList<Weight> XMLReader(MultipartFile file) throws ParserConfigurationException, SAXException, IOException {
        if (file == null) {
            return null;
        }
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse(file.getInputStream(), handler);
            return weights;
    }

    private class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Record")) {
                if (attributes.getValue("type").equals("HKQuantityTypeIdentifierBodyMass")) {
                    String name = attributes.getValue("sourceName").replaceAll("\\s+", "");
                    LocalDateTime CD = LocalDateTime.parse(attributes.getValue("creationDate").substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String value = attributes.getValue("value");
                    Weight pul = new Weight(name, CD, value);
                    if (!weights.contains(pul))
                    {
                        weights.add(pul);
                    }
                }
            }
        }

    }
}

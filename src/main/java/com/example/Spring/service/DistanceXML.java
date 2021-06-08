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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class DistanceXML {
    private ArrayList<Distance> distances = new ArrayList<>();

    public ArrayList<Distance> XMLReader(MultipartFile file) throws ParserConfigurationException, SAXException, IOException {
        if (file == null) {
            return null;
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(file.getInputStream(), handler);
        return distances;
    }

    private class XMLHandler  extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Record")) {
                if (attributes.getValue("type").equals("HKQuantityTypeIdentifierDistanceWalkingRunning")) {
                    String name = attributes.getValue("sourceName").replaceAll("\\s+", "");
                    LocalDate CD = LocalDate.parse(attributes.getValue("creationDate").substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Double value = Double.parseDouble(attributes.getValue("value"));
                    value=value*1000;
                    value = Double.valueOf(Math.round(value))/1000;
                    System.out.println(value);
                    if (distances.isEmpty()) {
                        distances.add(new Distance(name, CD, value));

                    } else {
                        boolean flag = true;
                        for (int i = distances.size() - 1; (i > distances.size() - 10) && (i > 0); i--) {

                            if (distances.get(i).getName().equals(name) && distances.get(i).getData().equals(CD)) {
                                flag = false;
                                distances.get(i).setValue(distances.get(i).getValue() + value);
                                break;
                            }
                        }
                        if (flag) {
                            Distance pul = new Distance(name, CD, value);
                            if (!distances.contains(pul))
                            {
                                distances.add(pul);
                            }
                        }
                    }
                }
            }

        }
    }
}
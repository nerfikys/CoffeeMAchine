package com.example.Spring.service;

import com.example.Spring.domain.Pulse;
import com.example.Spring.domain.Step;
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


public class StepXML {
    private static ArrayList<Step> steps = new ArrayList<>();

    public static ArrayList<Step> XMLReader(MultipartFile file) throws ParserConfigurationException, SAXException, IOException {
        if (file == null) {
            return null;
        }
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            XMLHandler handler = new XMLHandler();
            parser.parse(file.getInputStream(), handler);
            return steps;
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Record")) {
                if (attributes.getValue("type").equals("HKQuantityTypeIdentifierStepCount")) {
                    String name = attributes.getValue("sourceName").replaceAll("\\s+", "");
                    LocalDate CD = LocalDate.parse(attributes.getValue("creationDate").substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Long value = Long.parseLong(attributes.getValue("value"));
                    if (steps.isEmpty())
                    {
                        steps.add(new Step(name, CD, value));

                    }
                    else
                    {

                         boolean flag=true;
                         for (int i = steps.size()-1;(i>steps.size()-10)&&(i>0);i--)
                         {

                            if (steps.get(i).getName().equals(name)&&steps.get(i).getData().equals(CD))
                            {
                                flag=false;
                                steps.get(i).setValue(steps.get(i).getValue()+value);
                                break;
                            }
                        }
                        if (flag)
                        {
                            Step pul = new Step(name, CD, value);
                            if (!steps.contains(pul))
                            {
                                steps.add(pul);
                            }
                        }
                    }
                }
            }
        }
    }
}
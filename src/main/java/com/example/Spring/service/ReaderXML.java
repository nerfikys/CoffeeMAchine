package com.example.Spring.service;

import com.example.Spring.domain.Distance;
import com.example.Spring.domain.Pulse;
import com.example.Spring.domain.Step;
import com.example.Spring.domain.Weight;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReaderXML
{
    private ArrayList<Pulse> pulses = new ArrayList<>();
    private ArrayList<Distance> distances = new ArrayList<>();
    private ArrayList<Step> steps = new ArrayList<>();
    private ArrayList<Weight> weights = new ArrayList<>();

    public ArrayList<Pulse> getPulses() {
        return pulses;
    }
    public ArrayList<Distance> getDistances() {
        return distances;
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }
    public ArrayList<Weight> getWeights() {
        return weights;
    }

    public void ReaderXML(MultipartFile file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        ReaderXML.XMLHandler handler = new ReaderXML.XMLHandler();
        parser.parse(file.getInputStream(), handler);
    }

    private class XMLHandler  extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            if (qName.equals("Record"))
            {
                if (attributes.getValue("type").equals("HKQuantityTypeIdentifierDistanceWalkingRunning")) {
                    String name = attributes.getValue("sourceName").replaceAll("\\s+", "");
                    LocalDate CD = LocalDate.parse(attributes.getValue("creationDate").substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Double value = Double.parseDouble(attributes.getValue("value"));
                    value=value*1000;
                    value = Double.valueOf(Math.round(value))/1000;
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
                if (attributes.getValue("type").equals("HKQuantityTypeIdentifierHeartRateVariabilitySDNN")) {
                    String name = attributes.getValue("sourceName").replaceAll("\\s+", "");
                    LocalDateTime CD = LocalDateTime.parse(attributes.getValue("creationDate").substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String value = attributes.getValue("value");
                    Pulse pul = new Pulse(name, CD, value);
                    if (!pulses.contains(pul))
                    {
                        pulses.add(pul);
                    }
                }
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

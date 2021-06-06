package com.example.Spring.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import au.com.bytecode.opencsv.CSVReader;
import com.example.Spring.domain.Distance;
import com.example.Spring.domain.Pulse;
import com.example.Spring.domain.Step;
import com.example.Spring.domain.Weight;
import org.springframework.web.multipart.MultipartFile;

public class ReaderCSV
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

    public void ReadCSV(MultipartFile file) throws Exception
    {

        File convFile = multipartToFile(file);
        CSVReader reader = new CSVReader(new FileReader(convFile), ',' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                String part[] = Arrays.toString(nextLine).split(",");
                part[0] = part[0].replace("[", "");
                if(!part[1].equals(" "))
                {
                    LocalDateTime CD = LocalDateTime.parse(part[0]+" 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    Weight weight = new Weight("Google Fit",CD,part[1]);
                    if (!weights.contains(weight))
                    {
                        weights.add(weight);
                    }
                }
                if(!part[11].equals(" "))
                {
                    LocalDateTime CD = LocalDateTime.parse(part[0]+" 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    Long value =Long.valueOf(Math.round(Double.parseDouble(part[11])));
                    Pulse pulse = new Pulse("Google Fit",CD,value.toString());
                    if (!pulses.contains(pulse))
                    {
                        pulses.add(pulse);
                    }
                }
                if(!part[14].equals(" "))
                {
                    part[14] = part[14].replace(" ","");
                    LocalDate CD = LocalDate.parse(part[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Step step = new Step("Google Fit",CD,Long.parseLong(part[14]));
                    if (!steps.contains(step))
                    {
                        steps.add(step);
                    }
                }
                if(!part[15].equals(" "))
                {
                    Long value = Math.round(Double.parseDouble(part[15]));
                    LocalDate CD = LocalDate.parse(part[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Distance distance = new Distance("Google Fit",CD,Double.valueOf(value));
                    if (!distances.contains(distance))
                    {
                        distances.add(distance);
                    }
                }
            }
        }
        reader.close();
        convFile.delete();
    }
    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+multipart.getOriginalFilename()+multipart.hashCode());
        System.out.println(convFile.getAbsolutePath());
        multipart.transferTo(convFile);
        return convFile;
    }
}

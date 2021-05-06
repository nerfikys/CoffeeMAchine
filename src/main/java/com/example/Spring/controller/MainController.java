package com.example.Spring.controller;

import com.example.Spring.domain.*;
import com.example.Spring.repos.DistanceRepo;
import com.example.Spring.repos.PulseRepo;
import com.example.Spring.repos.StepRepo;
import com.example.Spring.repos.WeightRepo;
import com.example.Spring.service.DistanceXML;
import com.example.Spring.service.PulseXML;
import com.example.Spring.service.StepXML;
import com.example.Spring.service.WeightXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private static boolean ZANAYTO = false;
    @Autowired
    private PulseRepo pulseRepo;
    @Autowired
    private StepRepo stepRepo;
    @Autowired
    private DistanceRepo distanceRepo;
    @Autowired
    private WeightRepo weightRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
    @GetMapping("/static/index.html")
    public String html(Map<String, Object> model) {
        return "/";
    }

    @PostMapping("/")
    public String greeting2(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam Map<String, String> form, @RequestParam(required = false, defaultValue = "")String dataFrom, @RequestParam(required = false, defaultValue = "")String dataTo, @AuthenticationPrincipal User user, Model model) {
        List<Pulse> pulses = new ArrayList<>();
        List<Step> steps = new ArrayList<>();
        List<Distance> distances = new ArrayList<>();
        List<Weight> weights = new ArrayList<>();


        Iterable<Pulse> pulses0 = new ArrayList<>();
        Iterable<Step> steps0= new ArrayList<>();
        Iterable<Distance> distances0= new ArrayList<>();
        Iterable<Weight> weights0= new ArrayList<>();

        if (((dataFrom != null)&&(!dataFrom.equals("")))&&((dataTo != null)&&(!dataTo.equals(""))))
        {
            LocalDateTime dataFromForm = LocalDateTime.parse(dataFrom + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime dataToForm = LocalDateTime.parse(dataTo + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDate dateFromForm = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dateToForm = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            pulses0= pulseRepo.findByDataBetween(dataFromForm,dataToForm);
            weights0 = weightRepo.findByDataBetween(dataFromForm,dataToForm);
            steps0 = stepRepo.findByDataBetween(dateFromForm,dateToForm);
            distances0 = distanceRepo.findByDataBetween(dateFromForm,dateToForm);
            for (Pulse pulse : pulses0)
            {
                if (pulse.getAuthorName().equals(user.getUsername()))
                {
                    pulses.add(pulse);
                }
            }
            for (Weight weight : weights0)
            {
                if (weight.getAuthorName().equals(user.getUsername()))
                {
                    weights.add(weight);
                }
            }
            for (Step step : steps0)
            {
                if (step.getAuthorName().equals(user.getUsername()))
                {
                    steps.add(step);
                }
            }
            for (Distance distance : distances0)
            {
                if (distance.getAuthorName().equals(user.getUsername()))
                {
                    distances.add(distance);
                }
            }
        }
        else
        {
            pulses = pulseRepo.findByAuthor(user);
            steps = stepRepo.findByAuthor(user);
            distances = distanceRepo.findByAuthor(user);
            weights = weightRepo.findByAuthor(user);
        }
        ArrayList<String> spisokP=  new ArrayList<>();
        for (Pulse p : pulses)
        {
            boolean flag = true;
            if (spisokP.isEmpty())
            {
                spisokP.add(p.getName());
            }
            for (String sp : spisokP)
            {
               if(p.getName().equals(sp))
               {
                  flag=false;
                  break;
               }
            }
            if (flag)
            {
                spisokP.add(p.getName());
            }
        }
        ArrayList<String> spisokS=  new ArrayList<>();
        for (Step p : steps)
        {
            boolean flag = true;
            if (spisokS.isEmpty())
            {
                spisokS.add(p.getName());
            }
            for (String sp : spisokS)
            {
                if(p.getName().equals(sp))
                {
                    flag=false;
                    break;
                }
            }
            if (flag)
            {
                spisokS.add(p.getName());
            }
        }
        ArrayList<String> spisokD=  new ArrayList<>();
        for (Distance p : distances)
        {
            boolean flag = true;
            if (spisokD.isEmpty())
            {
                spisokD.add(p.getName());
            }
            for (String sp : spisokD)
            {
                if(p.getName().equals(sp))
                {
                    flag=false;
                    break;
                }
            }
            if (flag)
            {
                spisokD.add(p.getName());
            }
        }
        ArrayList<String> spisokW=  new ArrayList<>();
        for (Weight p : weights)
        {
            boolean flag = true;
            if (spisokW.isEmpty())
            {
                spisokW.add(p.getName());
            }
            for (String sp : spisokW)
            {
                if(p.getName().equals(sp))
                {
                    flag=false;
                    break;
                }
            }
            if (flag)
            {
                spisokW.add(p.getName());
            }
        }
        ArrayList<String> spisok2P= new ArrayList<>();
        ArrayList<String> spisok2S= new ArrayList<>();
        ArrayList<String> spisok2D= new ArrayList<>();
        ArrayList<String> spisok2W= new ArrayList<>();
        for (String key : form.keySet())
        {
            if (key.contains("pulse"))
            {
                spisok2P.add(key.substring(0,key.length()-6));
            }
            if (key.contains("step"))
            {
                spisok2S.add(key.substring(0,key.length()-5));
            }
            if (key.contains("distance"))
            {
                spisok2D.add(key.substring(0,key.length()-9));
            }
            if (key.contains("weight"))
            {
                spisok2W.add(key.substring(0,key.length()-7));

            }
        }
        model.addAttribute("spisok2P",spisok2P);
        model.addAttribute("spisok2S",spisok2S);
        model.addAttribute("spisok2D",spisok2D);
        model.addAttribute("spisok2W",spisok2W);
        model.addAttribute("spisokP",spisokP);
        model.addAttribute("spisokS",spisokS);
        model.addAttribute("spisokD",spisokD);
        model.addAttribute("spisokW",spisokW);
        model.addAttribute("dataFrom",dataFrom);
        model.addAttribute("dataTo",dataTo);
        model.addAttribute("pulses", pulses);
        model.addAttribute("steps", steps);
        model.addAttribute("distances", distances);
        model.addAttribute("weights", weights);
        model.addAttribute("ZANAYTO", ZANAYTO);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        ZANAYTO= true;
        ArrayList<Pulse> pulses = new ArrayList<>();
        ArrayList<Step> steps = new ArrayList<>();
        ArrayList<Distance> distances = new ArrayList<>();
        ArrayList<Weight> weights = new ArrayList<>();

        try {
            PulseXML pulseXML = new PulseXML();
            pulses = pulseXML.XMLReader(file);
            StepXML stepXML= new StepXML();
            steps = stepXML.XMLReader(file);
            DistanceXML distanceXML = new DistanceXML();
            distances = distanceXML.XMLReader(file);
            WeightXML weightXML = new WeightXML();
            weights = weightXML.XMLReader(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        Iterable<Pulse> pulses0 = pulseRepo.findByAuthor(user);
        for (Pulse pulse : pulses)
        {
            boolean flag = true;
            for (Pulse p : pulses0)
            {
                if(pulse.getValue().equals(p.getValue())&&pulse.getData().equals(p.getData())&&pulse.getName().equals(p.getName()))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                pulse.setAuthor(user);
                pulseRepo.save(pulse);
            }
        }
        Iterable<Weight> weights0 = weightRepo.findByAuthor(user);
        for (Weight weight : weights) {
            boolean flag = true;
            for (Weight w : weights0) {
                if (weight.getValue().equals(w.getValue()) && weight.getData().equals(w.getData()) && weight.getName().equals(w.getName()))
                {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                weight.setAuthor(user);
                weightRepo.save(weight);
            }
        }
        Iterable<Step> steps0 = stepRepo.findByAuthor(user);
        for (Step step : steps) {
            boolean flag = true;
            for (Step s : steps0) {
                if (step.getValue().equals(s.getValue()) && (step.getData().equals(s.getData()) && (step.getName().equals(s.getName()))))
                {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                step.setAuthor(user);
                stepRepo.save(step);
            }
        }
        Iterable<Distance> distances0 = distanceRepo.findByAuthor(user);
        for (Distance distance : distances) {
            boolean flag = true;
            for (Distance d : distances0) {
                if (distance.getValue().equals(d.getValue()) && (distance.getData().equals(d.getData()) && (distance.getName().equals(d.getName()))))
                {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                distance.setAuthor(user);
                distanceRepo.save(distance);
            }
        }
        ZANAYTO = false;
        Iterable<Pulse> pulses1 = pulseRepo.findByAuthor(user);
        Iterable<Step> steps1 = stepRepo.findByAuthor(user);
        Iterable<Distance> distances1 = distanceRepo.findByAuthor(user);
        Iterable<Weight> weights1 = weightRepo.findByAuthor(user);
        model.put("pulses", pulses1);
        model.put("steps", steps1);
        model.put("distances", distances1);
        model.put("weights", weights1);
        model.put("ZANAYTO", ZANAYTO);

        return "/";
    }
}

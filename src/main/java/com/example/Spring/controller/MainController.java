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
import org.springframework.beans.factory.annotation.Value;
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
import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private PulseRepo pulseRepo;
    @Autowired
    private StepRepo stepRepo;
    @Autowired
    private DistanceRepo distanceRepo;
    @Autowired
    private WeightRepo weightRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Pulse> pulses = pulseRepo.findAll();
        Iterable<Step> steps = stepRepo.findAll();
        Iterable<Distance> distances = distanceRepo.findAll();
        Iterable<Weight> weights = weightRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            pulses = pulseRepo.findByName(filter);
            steps = stepRepo.findByName(filter);
            distances = distanceRepo.findByName(filter);
            weights = weightRepo.findByName(filter);

        } else {
            pulses = pulseRepo.findAll();
            steps = stepRepo.findAll();
            distances = distanceRepo.findAll();
            weights = weightRepo.findAll();
        }

        model.addAttribute("pulses", pulses);
        model.addAttribute("steps", steps);
        model.addAttribute("distances", distances);
        model.addAttribute("weights", weights);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        ArrayList<Pulse> pulses = new ArrayList<>();
        ArrayList<Step> steps = new ArrayList<>();
        ArrayList<Distance> distances = new ArrayList<>();
        ArrayList<Weight> weights = new ArrayList<>();
        try {
            pulses = PulseXML.XMLReader(file);
            steps = StepXML.XMLReader(file);
            distances = DistanceXML.XMLReader(file);
            weights = WeightXML.XMLReader(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        for (Pulse pulse : pulses)
        {
         pulse.setAuthor(user);
         pulseRepo.save(pulse);
        }

        for (Weight weight : weights)
        {
            weight.setAuthor(user);
            weightRepo.save(weight);
        }
        for (Step step : steps)
        {
            System.out.println("PUY");
            step.setAuthor(user);
            stepRepo.save(step);
        }

        for (Distance distance : distances)
        {
            System.out.println("YUP");
            distance.setAuthor(user);
            distanceRepo.save(distance);
        }

        Iterable<Pulse> pulses1 = pulseRepo.findAll();
        Iterable<Step> steps1 = stepRepo.findAll();
        Iterable<Distance> distances1 = distanceRepo.findAll();
        Iterable<Weight> weights1 = weightRepo.findAll();

        model.put("pulses", pulses1);
        model.put("steps", steps1);
        model.put("distances", distances1);
        model.put("weights", weights1);

        return "/main";
    }
}

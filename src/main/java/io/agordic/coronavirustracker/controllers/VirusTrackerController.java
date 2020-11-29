package io.agordic.coronavirustracker.controllers;

import io.agordic.coronavirustracker.dto.UserDto;
import io.agordic.coronavirustracker.models.LocationStats;
import io.agordic.coronavirustracker.services.CoronaVirusDataService;
import io.agordic.coronavirustracker.security.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin
public class VirusTrackerController {
    private CoronaVirusDataService service;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    public VirusTrackerController(CoronaVirusDataService service){
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model, @Param("keyword") String keyword) throws IOException, InterruptedException {
        List<LocationStats> allStats = allStats = service.getAllStats();

        double totalLatestSum = allStats.stream()
                .mapToDouble(stat -> stat.getLatestTotalCases()).sum();

        double sumDiffFromPrevDay = allStats.stream()
                .mapToDouble(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalLatestSum);
        model.addAttribute("sumDiffFromPrevDay", sumDiffFromPrevDay);
        model.addAttribute("numberOfCountries", allStats.size());
        return "home";
    }
}

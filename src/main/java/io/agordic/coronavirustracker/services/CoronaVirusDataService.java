package io.agordic.coronavirustracker.services;

import io.agordic.coronavirustracker.models.LocationStats;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CoronaVirusDataService implements CoronVirusDataRepo{
    @Value("${VIRUS_DATA_URL}")
    private String VIRUS_DATA_URL;

    @PostConstruct
    public void print(){
        System.out.println(VIRUS_DATA_URL);
    }

    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats(){
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        //New instance of httpClient for sending request
        HttpClient httpClient = HttpClient.newHttpClient();

        //Create a request using a builder pattern
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        //Client send request and what to do with a body? BodyHandlers.ofString give me a string of body
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(httpResponse.body());

        List<LocationStats> newStats = new ArrayList<>();

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            double latestCases = Double.parseDouble(record.get(record.size() - 1));
            double prevCases = Double.parseDouble(record.get(record.size() - 2));

            LocationStats stat = new LocationStats()
                    .setState(record.get("Province/State"))
                    .setCountry(record.get("Country/Region"))
                    .setLatestTotalCases(latestCases)
                    .setDiffFromPrevDay(latestCases - prevCases);
            newStats.add(stat);
        }
        this.allStats = newStats;
    }

//    @Override
    public List<LocationStats> findByKeyword(String keyword) {
        ArrayList<LocationStats> tms = new ArrayList<>();
        for(int i = 0 ; i < allStats.size(); i++){
                if(allStats.get(i).getCountry().toLowerCase().contains(keyword.toLowerCase())){
                    tms.add(allStats.get(i));
                }
        }
        return tms;
    }


}

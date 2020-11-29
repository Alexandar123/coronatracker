package io.agordic.coronavirustracker.services;

import java.io.IOException;

public interface CoronVirusDataRepo {
     void fetchVirusData() throws IOException, InterruptedException;
}

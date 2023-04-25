package org.example.service;

import org.example.model.Client;

import java.util.Random;

public class RandomClientGenerator {
    static Random random = new Random();

    public static Client generateClient(int clientId, int arrivalMin, int arrivalMax, int serviceMin, int serviceMax) {
        int arrivalTime = random.nextInt(arrivalMax - arrivalMin + 1) + arrivalMin;
        int serviceTime = random.nextInt(serviceMax - serviceMin + 1) + serviceMin;
        return new Client(clientId, arrivalTime, serviceTime);
    }
}

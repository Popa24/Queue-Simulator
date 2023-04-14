package org.example.service;

import org.example.model.Client;

import java.util.Random;

public class RandomClientGenerator {
    static Random random = new Random();

    public static Client generateClient(int clientId, int arrivalMin, int arrivalMax, int serviceMin, int serviceMax) {
        int arrivalTime = random.nextInt(arrivalMax - arrivalMin + 1) + arrivalMin;
        int serviceTime = random.nextInt(serviceMax - serviceMin + 1) + serviceMin;
        if(arrivalTime<0 && serviceTime<0){
            arrivalTime=arrivalTime*-1;
            serviceTime=serviceTime*-1;
        }else if(arrivalTime<0){
           arrivalTime=arrivalTime*-1;
        } else if(serviceTime<0){
            serviceTime=serviceTime*-1;
        }
        return new Client(clientId, arrivalTime, serviceTime);
    }
}

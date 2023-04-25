package org.example.simulation;

import org.example.model.Client;
import org.example.model.Queue;
import org.example.service.Logger;
import org.example.service.QueueManager;
import org.example.service.RandomClientGenerator;
import org.example.view.SimulationListener;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    int numClients;
    int numQueues;
    int simulationMax;
    int arrivalMin;
    int arrivalMax;
    int serviceMin;
    int serviceMax;
    QueueManager queueManager;
    Logger logger;
    private SimulationListener simulationListener;

    public void setSimulationListener(SimulationListener simulationListener) {
        this.simulationListener = simulationListener;
    }

    public Simulation(int numClients, int numQueues, int simulationMax, int arrivalMin, int arrivalMax, int serviceMin, int serviceMax) {
        this.numClients = numClients;
        this.numQueues = numQueues;
        this.simulationMax = simulationMax;
        this.arrivalMin = arrivalMin;
        this.arrivalMax = arrivalMax;
        this.serviceMin = serviceMin;
        this.serviceMax = serviceMax;
        queueManager = new QueueManager(numQueues);
        logger = new Logger("Test 4.txt");
    }

    @Override
    public void run() {
        int currentTime = 0;
        List<Client> allClients = generateAllClients();
        List<Client> waitingClients = new ArrayList<>(allClients);

        while (!isSimulationFinished(waitingClients, queueManager.getQueues()) && currentTime <= simulationMax) {

            int finalCurrentTime = currentTime;


            waitingClients.removeIf(client -> client.getArrivalTime() == finalCurrentTime && queueManager.addClientToShortestQueue(client, finalCurrentTime));


            if (simulationListener != null) {
                simulationListener.onSimulationUpdate(currentTime, queueManager.getQueues(), waitingClients);
            }


            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            logger.logQueueState(currentTime, queueManager.getQueues());
        }


        queueManager.stopAllQueues();

        float averageWaitingTime = queueManager.calculateAverageWaitingTime(numClients);
        System.out.println("Average waiting time for Lidl Simulation is : " + averageWaitingTime);
        logger.logAverageWaitingTime(averageWaitingTime);
    }

    private boolean isSimulationFinished(List<Client> waitingClients, List<Queue> queues) {
        boolean areQueuesEmpty = queues.stream().allMatch(queue -> queue.getClients().isEmpty());
        return waitingClients.isEmpty() && areQueuesEmpty;
    }

    private List<Client> generateAllClients() {
        List<Client> allClients = new ArrayList<>();
        for (int i = 1; i <= numClients; i++) {
            Client client = RandomClientGenerator.generateClient(i, arrivalMin, arrivalMax, serviceMin, serviceMax);
            allClients.add(client);
        }
        return allClients;
    }
}

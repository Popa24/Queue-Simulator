package org.example.simulation;

import org.example.model.Client;
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
        logger = new Logger("Test 3.txt");
    }
    private List<Client> generateNewClients(int currentTime) {
        List<Client> newClients = new ArrayList<>();
        for (int i = 1; i <= numClients; i++) {
            Client client = RandomClientGenerator.generateClient(i, arrivalMin, arrivalMax, serviceMin, serviceMax);
            if (client.getArrivalTime() == currentTime) {
                newClients.add(client);
            }
        }
        return newClients;
    }


    @Override
    public void run() {
        int currentTime = 0;
        List<Client> waitingClients = new ArrayList<>();

        while (currentTime < simulationMax) {
            // Add new clients that arrive at this time to waitingClients
            List<Client> newClients = generateNewClients(currentTime);
            waitingClients.addAll(newClients);

            // Try to assign waiting clients to queues
            waitingClients.removeIf(client -> queueManager.addClientToShortestQueue(client));

            // Update the state of the queues
            queueManager.updateQueues();

            // Call the listener to update the GUI
            if (simulationListener != null) {
                simulationListener.onSimulationUpdate(currentTime, queueManager.getQueues(), waitingClients);
            }

            // Increment time
            currentTime++;
             try {
                 Thread.sleep(1000); // Sleep for 1 second.
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

            // Log the state of the queues
            logger.logQueueState(currentTime, queueManager.getQueues());
        }

// Calculate and display the average waiting time.
        float averageWaitingTime = queueManager.calculateAverageWaitingTime(numClients);
        System.out.println("Average waiting time for Lidl Simulation is : " + averageWaitingTime);
    }
}
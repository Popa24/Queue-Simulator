package org.example.service;

import org.example.model.Client;
import org.example.model.Queue;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {
    public List<Queue> queues;

    public QueueManager(int numQueues) {
        queues = new ArrayList<>();
        for (int i = 0; i < numQueues; i++) {
            queues.add(new Queue());
        }
    }

    public float calculateAverageWaitingTime(int numClients) {
        int totalWaitingTime = 0;
        for (Queue queue : queues) {
            totalWaitingTime += queue.waitingTime;
        }
        return (float) totalWaitingTime / numClients;
    }

    public boolean addClientToShortestQueue(Client client) {
        Queue shortestQueue = queues.get(0);
        for (Queue queue : queues) {
            if (queue.waitingTime < shortestQueue.waitingTime) {
                shortestQueue = queue;
            }
        }
        return shortestQueue.addClient(client);
    }

    public void updateQueues() {
        for (Queue queue : queues) {
            queue.updateServiceTime();
        }
    }

    public List<Queue> getQueues() {
        return queues;
    }
}


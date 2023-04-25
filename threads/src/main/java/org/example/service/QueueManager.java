package org.example.service;

import org.example.model.Client;
import org.example.model.Queue;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {
    public List<Queue> queues;
    public List<Thread> queueThreads;

    public QueueManager(int numQueues) {
        queues = new ArrayList<>();
        queueThreads = new ArrayList<>();
        for (int i = 0; i < numQueues; i++) {
            Queue queue = new Queue();
            queues.add(queue);
            Thread thread = new Thread(queue);
            queueThreads.add(thread);
            thread.start();
        }
    }

    public float calculateAverageWaitingTime(int numClients) {
        int totalWaitingTime = 0;
        for (Queue queue : queues) {
            totalWaitingTime += queue.waitingTime;
        }
        return (float) totalWaitingTime / numClients;
    }

    public synchronized boolean addClientToShortestQueue(Client client, int currentTime) {
        if (client.getArrivalTime() > currentTime) {
            return false; // Do not add the client if its arrival time is greater than the current time
        }

        Queue shortestQueue = queues.get(0);
        for (Queue queue : queues) {
            if (queue.waitingTime < shortestQueue.waitingTime) {
                shortestQueue = queue;
            }
        }
        return shortestQueue.addClient(client);
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void stopAllQueues() {
        for (Queue queue : queues) {
            queue.stop();
        }
    }
}


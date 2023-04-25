package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Queue implements Runnable {
    List<Client> clients;
    public int waitingTime;

    private boolean running;

    public Queue() {
        clients = new ArrayList<>();
        waitingTime = 0;
        running = true;
    }

    public List<Client> getClients() {
        return clients;
    }

    public synchronized boolean addClient(Client client) {
        clients.add(client);
        waitingTime += client.serviceTime;
        return true;
    }

    public synchronized void updateServiceTime() {
        if (!clients.isEmpty()) {
            Client frontClient = clients.get(0);
            frontClient.serviceTime -= 1;
            if (frontClient.serviceTime <= 0) {
                clients.remove(0);
            }
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            updateServiceTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

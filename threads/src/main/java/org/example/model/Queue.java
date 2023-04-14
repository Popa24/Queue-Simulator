package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    List<Client> clients;
    public int waitingTime;

    public Queue() {
        clients = new ArrayList<>();
        waitingTime = 0;
    }
    public List<Client> getClients() {
        return clients;
    }

    public synchronized boolean addClient(Client client) {
        clients.add(client);
        waitingTime += client.serviceTime;
        return false;
    }

    public synchronized void updateServiceTime() {
        if (!clients.isEmpty()) {
            Client frontClient = clients.get(0);
            frontClient.serviceTime -= 1;
            if (frontClient.serviceTime == 0) {
                clients.remove(0);
            }
        }
    }
}
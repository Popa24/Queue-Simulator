package org.example.view;


import org.example.model.Client;
import org.example.model.Queue;

import java.util.List;

public interface SimulationListener {
    void onSimulationUpdate(int currentTime, List<Queue> queues, List<Client> waitingClients);
}

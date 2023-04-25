package org.example.view;

import org.example.model.Client;
import org.example.model.Queue;
import org.example.simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationGUI extends JFrame implements SimulationListener {
    private final JTextField numClientsField;
    private final JTextField numQueuesField;
    private final JTextField simulationMaxField;
    private final JTextField arrivalMinField;
    private final JTextField arrivalMaxField;
    private final JTextField serviceMinField;
    private final JTextField serviceMaxField;
    private final JTextArea queueStateTextArea;

    public SimulationGUI() {
        setTitle("Lidl Simulation");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));

        inputPanel.add(new JLabel("Number of Clients:"));
        numClientsField = new JTextField();
        inputPanel.add(numClientsField);

        inputPanel.add(new JLabel("Number of Queues:"));
        numQueuesField = new JTextField();
        inputPanel.add(numQueuesField);

        inputPanel.add(new JLabel("Simulation Max Time:"));
        simulationMaxField = new JTextField();
        inputPanel.add(simulationMaxField);

        inputPanel.add(new JLabel("Arrival Min Time:"));
        arrivalMinField = new JTextField();
        inputPanel.add(arrivalMinField);

        inputPanel.add(new JLabel("Arrival Max Time:"));
        arrivalMaxField = new JTextField();
        inputPanel.add(arrivalMaxField);

        inputPanel.add(new JLabel("Service Min Time:"));
        serviceMinField = new JTextField();
        inputPanel.add(serviceMinField);

        inputPanel.add(new JLabel("Service Max Time:"));
        serviceMaxField = new JTextField();
        inputPanel.add(serviceMaxField);

        add(inputPanel, BorderLayout.NORTH);

        queueStateTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(queueStateTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(e -> startSimulation());
        add(startButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void startSimulation() {
        int numClients = Integer.parseInt(numClientsField.getText());
        int numQueues = Integer.parseInt(numQueuesField.getText());
        int simulationMax = Integer.parseInt(simulationMaxField.getText());
        int arrivalMin = Integer.parseInt(arrivalMinField.getText());
        int arrivalMax = Integer.parseInt(arrivalMaxField.getText());
        int serviceMin = Integer.parseInt(serviceMinField.getText());
        int serviceMax = Integer.parseInt(serviceMaxField.getText());

        Simulation simulation = new Simulation(numClients, numQueues, simulationMax, arrivalMin, arrivalMax, serviceMin, serviceMax);
        simulation.setSimulationListener(this);
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();
    }

    @Override
    public void onSimulationUpdate(int currentTime, List<Queue> queues, List<Client> waitingClients) {
        StringBuilder state = new StringBuilder();
        state.append("Time ").append(currentTime).append("\n");

        state.append("Waiting clients: ");
        for (Client client : waitingClients) {
            state.append(client.toString()).append("; ");
        }
        state.append("\n");

        for (int i = 0; i < queues.size(); i++) {
            state.append("Queue ").append(i + 1).append(": ");
            queues.get(i).getClients().forEach(client -> state.append(client.toString()).append("; "));
            state.append("\n");
        }

        state.append("\n");

        queueStateTextArea.setText(queueStateTextArea.getText() + state);
    }
}

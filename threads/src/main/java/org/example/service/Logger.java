package org.example.service;

import org.example.model.Client;
import org.example.model.Queue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Logger {
    private PrintWriter logWriter;

    public Logger(String logFilePath) {
        try {
            logWriter = new PrintWriter(new FileWriter(logFilePath, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logQueueState(int currentTime, List<Queue> queues) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("Time ").append(currentTime).append("\n");

        int queueNumber = 1;
        for (Queue queue : queues) {
            logBuilder.append("Queue ").append(queueNumber).append(": ");
            for (Client client : queue.getClients()) {
                logBuilder.append(client.toString()).append("; ");
            }
            logBuilder.append("\n");
            queueNumber++;
        }

        logBuilder.append("\n");
        logWriter.println(logBuilder);
        logWriter.flush();
    }

}


package org.example.simulation;

import org.example.view.SimulationGUI;

import javax.swing.*;

public class LidlSimulation {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulationGUI simulationGUI = new SimulationGUI();
            simulationGUI.setVisible(true);
        });
    }
}


Lidl Queue Management Simulation\
Project Description\
This project simulates the queue management system at a Lidl grocery store. The program generates a specific number of clients, each with random arrival and service times, and distributes them across multiple queues in order to minimize overall waiting time.\

Features\
- The program can simulate different numbers of clients and queues.
- Each client has a random arrival and service time within a given range.
- The program logs the state of each queue at each time step, as well as the average waiting time at the end of the simulation.
- The program uses multithreading to simulate the passage of time in each queue.
 -Classes
Client:
- This class represents a client in the simulation. Each client has an ID, an arrival time, and a service time.
Queue:
- This class represents a queue in the simulation. It implements the Runnable interface, allowing it to update its state in a separate thread.
QueueManager: 
- This class manages all the queues in the simulation. It creates and starts the queues, assigns clients to the shortest queue, and calculates the average waiting time.
RandomClientGenerator: 
- This class generates random clients with random arrival and service times.
LidlSimulation: 
- This is the main class that runs the simulation.
Simulation: 
- This class runs the simulation loop. It generates all clients, assigns them to queues, and logs the state of the simulation at each time step.
Getting Started
To run the program, simply run the LidlSimulation class.\

Future Developments\
Implement a graphical user interface (GUI) to visualize the state of the queues at each time step.
Add more complex client behaviors, such as leaving the queue if the waiting time is too long.
Use a more advanced algorithm to assign clients to queues in order to further reduce waiting time.\
References\
Please see the project report for a full list of references.\

Contribution\
This project is open to contributions. Please create a new branch for your changes and submit a pull request.

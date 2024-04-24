import java.io.*;
import java.net.*;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * Group Project - Social Media
 *
 * recreates a social media platform
 *
 * @author Samantha Grief, Connor Mccarthy, Ehssan Kanamkandy, Jahiem Cruickshank, L10
 *
 * @version April 14, 2024
 *
 */

public class Server{
    //Field declarations, port set to 8080, MAX_THREADS set to 100
    private static final int port = 8080;
    private static final int MAX_THREADS = 100;
    private static Thread[] threads;
    private static ServerSocket serverSocket;
    private static Database database = new Database();

    public Server() throws IOException {
        threads = new Thread[MAX_THREADS];
        serverSocket = new ServerSocket(port);
        database.readDatabase();

        // Constructor for server class - initializes threads and creates server socket

    }

    public void run() {
        //implements the run method
        // Waits for and accepts connection from a user

        try {
            while (!Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("server established");
                int freeThreadIndex = findFreeThread(); //**
                //System.out.println("server established");
                // calls findFreeThread method to find an available thread

                if (freeThreadIndex != -1) {
                    threads[freeThreadIndex] = new Thread(new ClientHandler(clientSocket , database));
                    threads[freeThreadIndex].start();
                } else {
                    System.out.println("No free threads available"); //**
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutdown(); //**
        }

    }

    private static int findFreeThread() {
        //Iterates through threads array to find the index of the first available thread position
        for (int i = 0 ; i < threads.length; i++) {
            if (threads[i] == null || !threads[i].isAlive()) { //**
                return i;
            }
        }
        return -1;

    }

    private static void shutdown() {
        // Interrupts active threads to stop their execution an closes the server socket
        for (Thread thread : threads) {
            if (thread != null) {
                thread.interrupt(); //**
            }
            database.writeDatabase();
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // creates instance of server class and starts it in a new thread.

        Database database = new Database();
        database.readDatabase();

        try {
            Server server = new Server();
            while (!Thread.currentThread().isInterrupted()) {
                Socket clientSocket = server.serverSocket.accept();
                System.out.println("server established");
                int freeThreadIndex = findFreeThread(); //**
                //System.out.println("server established");
                // calls findFreeThread method to find an available thread

                if (freeThreadIndex != -1) {
                    threads[freeThreadIndex] = new Thread(new ClientHandler(clientSocket, database));
                    threads[freeThreadIndex].start();
                } else {
                    System.out.println("No free threads available"); //**
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

/*
        try (ServerSocket ss = new ServerSocket(8080)) {
            int threadCount = 1;
            while (true) {
                Socket socket = ss.accept();
                Thread t = new Thread(new ClientHandler(socket , database));
                t.start();
                threadCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

 */

    }

}

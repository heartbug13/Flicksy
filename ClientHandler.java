import java.io.*;
import java.net.*;

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

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //**
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true); //**

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {

                System.out.println("Received from client: " + inputLine);
                writer.println("Server received: " + inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally { //**
            try {
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

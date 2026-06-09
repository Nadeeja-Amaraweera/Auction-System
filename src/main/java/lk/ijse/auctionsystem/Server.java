package lk.ijse.auctionsystem.Controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Server is Started on port 6000");
            System.out.println("Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New Client Connected: " + socket.getInetAddress());
                System.out.println("Total clients: " + (clients.size() + 1));

                ClientHandler clientHandler = new ClientHandler(socket, clients);

                clients.add(clientHandler);
                clientHandler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

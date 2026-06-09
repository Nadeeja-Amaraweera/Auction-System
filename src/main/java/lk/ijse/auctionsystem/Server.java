package lk.ijse.auctionsystem;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("[ Auction Server Started - Port 6000 ]");

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected: "+socket.getInetAddress());
                System.out.println("Total Clients: "+(clients.size()+1));

                ClientHandler clientHandler = new ClientHandler(socket,clients);
                clients.add(clientHandler);
                clientHandler.start();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

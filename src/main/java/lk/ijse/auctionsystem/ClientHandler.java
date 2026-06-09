package lk.ijse.auctionsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread{
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private List<ClientHandler> clients;
    private String clientName;

    public ClientHandler (Socket socket,List<ClientHandler> clients){
        this.socket = socket;
        this.clients = clients;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            dataOutputStream.writeUTF("Enter your name: ");
            dataOutputStream.flush();
            clientName = dataInputStream.readUTF();
            System.out.println("Client name received: " + clientName);
            broadCastMessage(clientName + " has joined the chat!", this);

            String message;
            while (true) {
                message = dataInputStream.readUTF();

                System.out.println("Received from " + clientName + ": '" + message + "'");

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                broadCastMessage(clientName + ": " + message, this);
            }

            clients.remove(this);
            broadCastMessage(clientName + " has left the chat!", this);
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadCastMessage(String message,ClientHandler sender){
        for (ClientHandler client: clients) {
            if (client != sender){
                try {
                    client.dataOutputStream.writeUTF(message);
                    client.dataOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

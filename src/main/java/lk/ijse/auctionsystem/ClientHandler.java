package lk.ijse.auctionsystem;

import lk.ijse.auctionsystem.Controller.ClientController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread{

private Socket socket;
private List<ClientHandler> clients;
private DataInputStream dataInputStream;
private DataOutputStream dataOutputStream;
private String clientName;


    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            dataOutputStream.writeUTF("Enter your name");

            dataOutputStream.flush();
            if (dataInputStream.)
            clientName = dataInputStream.readUTF();
            System.out.println("Client Connected : "+clientName);

            String message;
            while (true){
                message = dataInputStream.readUTF();
                System.out.println("Receive message "+ message);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void broadCastMessage(String message ,ClientHandler sender){
        for (ClientHandler client: clients){
            if (client != sender){
                try{
                    dataOutputStream.writeUTF(message);
                    dataOutputStream.flush();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

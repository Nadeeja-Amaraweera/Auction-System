package lk.ijse.auctionsystem.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client02 {
    public static void main(String[] args) {
        try {

            Socket remoteSocket = new Socket("localhost",3000);
            System.out.println("Connected to the Chat Server!");

            DataInputStream dataInputStream = new DataInputStream(remoteSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            System.out.println(dataInputStream.readUTF());
            String name = scanner.next();
            dataOutputStream.writeUTF(name);
            dataOutputStream.flush();
            System.out.println("You are connected as: " + name);

            Thread receiveThread = new Thread(() -> {
                try {
                    while (true){
                        String message = dataInputStream.readUTF();
                        System.out.println(message);
                    }
                } catch (Exception e){
                    System.out.println("Connection closed by the server.");
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            while (true){
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")){
                    break;
                }
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            }

            remoteSocket.close();
            System.out.println("Disconnected from the Chat Server!");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

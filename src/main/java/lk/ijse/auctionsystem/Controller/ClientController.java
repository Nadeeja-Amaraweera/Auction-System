package lk.ijse.auctionsystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField userNameField;

    private String userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {



            Socket remoteSocket = new Socket("localhost",6000);
            String serverConnectedMessage = "Connected to the Server";
            System.out.println(serverConnectedMessage);

            DataInputStream dataInputStream = new DataInputStream(remoteSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());

            messageArea.appendText(dataInputStream.readUTF());

            userName  = dataInputStream.readUTF();

            dataOutputStream.writeUTF(userName);
            dataOutputStream.flush();
            JOptionPane.showMessageDialog(null,"User Name is "+userName);

            userNameField.setText(userName);



        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

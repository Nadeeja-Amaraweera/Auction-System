package lk.ijse.auctionsystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            Socket remoteSocket = new Socket("localhost",6000);
            String serverConnectedMessage = "Connected to the Server";
            System.out.println(serverConnectedMessage);

            DataInputStream dataInputStream = new DataInputStream(remoteSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());

            messageArea.appendText(dataInputStream.readUTF());
            String userName  = connectWithName();

            if (!userName.isEmpty()){
                dataOutputStream.writeUTF(userName);
                dataOutputStream.flush();
                System.out.println("You are Connected as"+userName);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private String connectWithName(){
        String name = userNameField.getText();
        if (name.isEmpty()){
            messageArea.appendText("Please Enter your name");
        }
        System.out.println("Name is"+name);
        return name;
    }
}

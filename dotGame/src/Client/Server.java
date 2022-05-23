package Client;
import java.net.*;
import java.io.*;
import Common.*;

public class Server implements Runnable {
    ServerSocket server;
    Socket client;
    ObjectInputStream input;
    Dot dot;  
    
    public Server(Dot d){
        dot = d;
        System.out.println("Servidor 2 online");
        try {
            server = new ServerSocket(4446);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
    public void run(){
        try {
            while(true){
                client = server.accept();
                input = new ObjectInputStream(client.getInputStream());
                dot.lastPosition =dot.currentPosition;
                Dot punto=(Dot)input.readObject();
                dot.currentPosition= punto.currentPosition;
                input.close();
                client.close();
            }
        } catch (Exception e) {
            run();
        }
    }


}

package org.example;

import java.io.*;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection extends Thread{

    private Socket socket;
    private BufferedReader in;
    public PrintWriter out;

    private  boolean buffer = true;


    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readLine();
                Server.LOGGER.log(Level.INFO,word);
                if(word.equals("/exit")) {
                    break;
                }
                for (ServerConnection client : Server.serverList) {
//                    if (this.equals(client)){
//                        continue;
//                    }
                    client.out.println(word);
                }
            }
        } catch (IOException e) {
        }
    }

}

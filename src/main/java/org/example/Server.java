package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.*;

public class Server {


    static Logger LOGGER;
    static {
        try(FileInputStream ins = new FileInputStream("logging.properties")){
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getGlobal();
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }

    public static final int PORT = 4004;

    public static LinkedList<ServerConnection> serverList = new LinkedList<>();

    public static ServerSocket server;


    public Server() {
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void start() throws IOException {
        System.out.println("Сервер запущен");


        while (true) {
            Socket socket = server.accept();
            try {
                System.out.println("gjlrk.xtybt");
                serverList.add(new ServerConnection(socket));
            } catch (IOException e) {
                socket.close();
            }
        }
    }



}

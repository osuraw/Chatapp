package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket Ssocketc;

    public static void main(String[] args)
    {
        try (ServerSocket server = new ServerSocket(5001)) {
            while (true) {
                new clients(server.accept()).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clients extends Thread {
    private Socket Client;
    private String name = null;

    public clients(Socket client) {

        Client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Client.getInputStream()));
            do {
                if (name == null) {
                    name = reader.readLine();
                    new publisher(Client, name);
                }
                else {
                    String clientMsg = reader.readLine();
                    if (clientMsg.equals("exit"))
                        break;
                    publisher.Sending(Client.getPort(),clientMsg);
                }
            } while (!Client.isClosed());
            publisher.remove(Client.getPort());
            Client.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

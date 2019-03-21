package com.company;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class publisher {
    class Clientst {

        String name;
        Socket Client;
        PrintWriter writer;

        public Clientst(String name, Socket client) {
            this.name = name;
            Client = client;
            try {
                writer = new PrintWriter(client.getOutputStream(), true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String welcome;
    private static Map<Integer, Clientst> connected = new HashMap<>();

    public publisher(Socket client, String name)
    {
        connected.put(client.getPort(), new Clientst(name, client));
        welcome = "--- " + name + " connected to chat ---";
        System.out.println(welcome);
        Sending(client.getPort(), welcome);
    }

    public static void Sending(int Senderport, String message) {
        for (int key : connected.keySet()) {
            if (key == Senderport)
                continue;
            else {
                connected.get(key).writer.println(connected.get(Senderport).name + " : " + message);
            }
        }
    }

    public static void remove(int port) {
        System.out.println(connected.get(port).name + " client exit");
        Sending(port, "----------Disconnected-----------");
        connected.remove(port);
    }
}

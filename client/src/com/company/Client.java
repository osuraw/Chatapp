package com.company;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static String name, message;
    private JPanel panel1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Name : ");
        name = in.nextLine();
        try (Socket hostserver = new Socket("localhost", 5001)) {
            PrintWriter Sendmessage = new PrintWriter(hostserver.getOutputStream(), true);
            Sendmessage.println(name);

            new listening(hostserver).start();

            System.out.println("Connected to Server");
            do {
                System.out.print("Message : ");
                message = in.nextLine();
                if (!message.equals("exit")) {
                    Sendmessage.println(message);
                }
            } while (!message.equals("exit"));
            System.out.println("Exit from Server");
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

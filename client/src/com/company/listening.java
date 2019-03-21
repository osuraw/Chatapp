package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class listening extends Thread{

    private Socket Server;
    private BufferedReader response;
    public listening(Socket Server)
    {
        this.Server=Server;
        try {
            response = new BufferedReader(new InputStreamReader(this.Server.getInputStream()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        while (true)
        {
            if (!Server.isConnected())
                break;
            try {
                System.out.println("\n"+response.readLine());
            } catch (IOException e) {
                System.out.println(" Server down Exiting.....");
                this.stop();
            }
        }
    }
}

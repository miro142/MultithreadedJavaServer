package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptedThreads extends Thread{
    ServerSocket ss;
    AcceptedThreads(){
        try {
            ss=new ServerSocket(1234);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {

            System.out.println("server is waiting for connection");
            while (true) {
                Socket s = ss.accept();
                Thread t;
                t=new Thread(new ConnectionThread(s));
                t.start();
                System.out.println("Connected");
            }
    } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                ss.close();
            } catch (IOException e){
                System.out.println("Error closing server socket");
            }
        }
}
}

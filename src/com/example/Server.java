package com.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    final static ArrayList<Offert> offerts= new ArrayList<Offert>();
    final static ArrayList<User> users = new ArrayList<User>();
    public static void main(String[] args){
        AcceptedThreads a=new AcceptedThreads();
        a.start();
    }
}

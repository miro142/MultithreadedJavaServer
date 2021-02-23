package com.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            Socket s=new Socket("127.0.0.1",1234);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            System.out.println("Enter your email: ");
            String email= sc.nextLine();
            System.out.println("Enter your password: ");
            String pass = sc.nextLine();
            out.writeUTF(email);
            out.flush();
            out.writeUTF(pass);
            out.flush();
            while (true) {
            ArrayList<Offert> offers=(ArrayList<Offert>)in.readObject();
            for (Offert o:offers){
                System.out.println(o.offertId+" "+o.destination+" "+o.nameOfCompany+" "+o.price+" "+o.rating);
            }

                System.out.println(in.readUTF());
                int resp;
                do {
                    resp = sc.nextInt();
                } while (resp < 1 || resp > 2);
                out.writeInt(resp);
                out.flush();
                System.out.println(in.readUTF());
                out.writeInt(sc.nextInt());
                out.flush();
                if (resp == 1) {
                    System.out.println(in.readUTF());
                } else {
                    in.readUTF();
                    int rate;
                    do {
                        rate = sc.nextInt();
                    } while (rate < 1 || rate > 6);
                    out.writeInt(rate);
                }
            }
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}

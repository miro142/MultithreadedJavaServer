package com.example;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

import static com.example.Server.offerts;
import static com.example.Server.users;

public class ConnectionThread implements Runnable{
    private Socket s;
    ConnectionThread(Socket sc){
     this.s=sc;
    }
    @Override
    public void run(){
        try {
            ObjectInputStream in=new ObjectInputStream(this.s.getInputStream());
            ObjectOutputStream out=new ObjectOutputStream(this.s.getOutputStream());
            String currMail=in.readUTF();
            String currPass=in.readUTF();
            User currUser =new User(currMail,currPass);
            boolean ans=users.contains(currUser);
            if(ans){
                System.out.println("this user is OK");
            }
            else {
                System.out.println("something is wrong");
            }
            while (true) {
            out.writeObject(offerts);
            out.flush();
            out.writeUTF("To make a reservation send 1, to submit a rating send 2");
            out.flush();
            int choice=in.readInt();
            out.writeUTF("Enter id:");
            out.flush();
            int id=in.readInt();
            switch (choice){
                case 1:
                    reservation(currUser,id);
                    out.writeUTF("Reservation made");
                    out.flush();
                    break;
                case 2:
                    boolean isOK=false;
                    for(Grade g: currUser.grades) {
                        if(id==g.offertId && !g.getEvaluation()){
                            isOK=true;
                            synchronized (g){
                            g.setEvaluation(true);
                            break;
                            }
                        }
                    }
                    if(isOK) {
                        out.writeUTF("Enter your grade (1 to 6):");
                        out.flush();
                        int ev = in.readInt();
                        for (Offert o:offerts) {
                            if(o.offertId==id){
                            synchronized (o) {
                                o.evaluation.add(ev);
                                int total=0;
                                for (int i :o.evaluation){
                                    total+=i;
                                }
                                o.rating=(double) total/(double) o.evaluation.size();
                            }
                            break;
                            }
                        }
                    } else {
                        out.writeUTF("You can only rate places you have visited and have not rated already!");
                    }
                    break;
                default: System.out.println("error");
            }
        }}
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    private final void reservation(User us,int id){
        for (Offert o:offerts){
            if(id==o.offertId){
                synchronized (us) {
                    us.grades.add(new Grade(id));
                }
                break;
            }
        }
    }

}

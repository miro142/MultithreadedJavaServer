package com.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Offert implements Serializable {
    int offertId;
    String destination;
    String nameOfCompany;
    double price;
    double rating;
    ArrayList<Integer> evaluation;
    Offert(int id,String des, String Cname,double pr){
        offertId=id;
        destination=des;
        nameOfCompany=Cname;
        price=pr;
    }
}

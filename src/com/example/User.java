package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {
    String email;
    String pass;
    ArrayList<Grade> grades;
    User(String email, String pass){
        this.email=email;
        this.pass=pass;
        grades=new ArrayList<Grade>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(pass, user.pass) &&
                Objects.equals(grades, user.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, pass);
    }
}

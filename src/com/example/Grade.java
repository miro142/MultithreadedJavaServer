package com.example;

import java.io.Serializable;

public class Grade implements Serializable {
    int offertId;
    private boolean evaluation;

    Grade(int offertId){
        this.offertId=offertId;
    }
    public boolean getEvaluation(){
        return evaluation;
    }
    public void setEvaluation(boolean ev){
        evaluation=ev;
    }
}

package com.robofood.octavioben.robofood;

/**
 * Created by octavioben on 28/11/16.
 */

public class Variables {
    private static Variables instance;
    private static String User;
    private static double Total;
    private Variables(){

    }

    public void setUser(String u){
        Variables.User = u;
    }

    public String getUser(){
        return Variables.User;
    }

    public void setTotal(double t){
        Variables.Total = t;
    }

    public Double getTotal(){
        return Variables.Total;
    }

    public static synchronized Variables getInstance(){
        if(instance == null){
            instance  = new Variables();
        }

        return instance;
    }
}

package com.project.iotdoorbellsystem;

public class Usingstart implements Runnable {

    //run method
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        //create the object
        Usingstart obj=new Usingstart();

        //create thread object by passing the class variable
        Thread t1 =new Thread(obj);

        // this will call run() method
        t1.start();
    }
}

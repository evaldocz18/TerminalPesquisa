package com.evaldo.terminalperquisacliente.util;

import android.os.AsyncTask;

public class Chronometer {

    private Runnable callback;
    private int timePassed;
    private int timeToWait;
    private int timeStep = 200;
    private boolean isRunning = false;
    private Thread t;

    public Chronometer(int timeToWait) {
        this.timeToWait = timeToWait;
    }

    public void start(){
        timePassed = 0;
        isRunning = true;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                while (isRunning){
                    if (timePassed >= timeToWait && isRunning){
                        callback.run();
                        isRunning = false;
                        return;
                    }
                    try {
                        Thread.sleep(timeStep);
                        timePassed += timeStep;
                        System.out.println("time passed:" + timePassed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
    public void reset(){
        timePassed = 0;
    }
    public void stop(){
        isRunning = false;
    }

    public void setCallback(Runnable run){
        callback = run;
    }

}

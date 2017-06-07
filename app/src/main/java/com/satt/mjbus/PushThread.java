package com.satt.mjbus;

import android.content.Context;

import com.satt.mjbus.Constants.Constants;

/**
 * Created by dlgow on 2017-06-07.
 */
public class PushThread implements Runnable{
    Context context;
    int poss;
    Constants.EBusState eState;
    Gps_Receiver_Push push = new Gps_Receiver_Push();
    public PushThread(Context cont, int pos, Constants.EBusState eBusState){
        this.context = cont;
        this.poss = pos;
        this.eState = eBusState;
    }


    @Override
    public void run() {
        while(true){
            push.getData("http://117.17.158.240/test/GpsSender.php", context, poss, eState);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

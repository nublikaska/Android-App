package com.example.denis.holodos;

import android.app.Application;

import com.example.denis.holodos.services.StatisticsService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Denis on 28.02.2018.
 */

public class App extends Application {
    public static final String BaseUrl = "http://192.168.0.41:8080" ;

    public static int[] getRgb(int Sum, int maxCount) {
        int count = (int)Sum;
        count = count%maxCount;
        double d = count * 0.4;
        int t = (int)d;

        int max = 40;
        int fromR  = 200;
        int fromG  = 255;
        int fromB  = 0;
        int toR    = 255;
        int toG    = 0;
        int toB    = 0;
        int deltaR = (toR - fromR) / max;
        int deltaG = (toG - fromG) / max;
        int deltaB = (toB - fromB) / max;

        int R1 = fromR + t * deltaR;
        int G  = fromG + t * deltaG;
        int B  = fromB + t * deltaB;

        return new int[] {R1, G, 0};
    }
}

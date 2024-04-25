package com.airline;

import com.airline.checkin.processor.ManualProcessor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Calling Manual Processor");
        ManualProcessor.process();
    }
}
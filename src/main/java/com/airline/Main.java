package com.airline;

import com.airline.checkin.processor.ConcurrentCheckInProcessor;
import com.airline.checkin.processor.Processor;
import com.airline.checkin.dao.SeatsRepository;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Starting manual check-in process");
        // SeatsRepository.clearAllSeats();
        // System.out.println("All seats cleared");
        // Processor manualProcessor = new ManualProcessor();
        // manualProcessor.process();
        // System.out.println("Manual check-in process completed");
        System.out.println("Starting concurrent check-in process");
        SeatsRepository.clearAllSeats();
        Processor concurrentProcessor = new ConcurrentCheckInProcessor();
        concurrentProcessor.process();
        System.out.println("Concurrent check-in process completed");
        
    }
}

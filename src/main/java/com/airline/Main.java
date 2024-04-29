package com.airline;

import com.airline.checkin.db.PoolManager;
import com.airline.checkin.processor.ConcurrentCheckInProcessor;
import com.airline.checkin.processor.ManualCheckInProcessor;
import com.airline.checkin.processor.Processor;
import com.airline.checkin.dao.SeatsRepository;

public class Main {

    private static ThreadLocal<PoolManager> poolManagerThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        init();

//         System.out.println("Starting manual check-in process");
//         SeatsRepository.clearAllSeats();
//         System.out.println("All seats cleared");
//         Processor manualProcessor = new ManualCheckInProcessor();
//         manualProcessor.process();
//         System.out.println("Manual check-in process completed");

        System.out.println("Starting concurrent check-in process");
        SeatsRepository.clearAllSeats();
        Processor concurrentProcessor = new ConcurrentCheckInProcessor();
        concurrentProcessor.process();
        System.out.println("Concurrent check-in process completed");
        
    }

    private static void init() {
        System.out.println("Initializing application");
        poolManagerThreadLocal.set(PoolManager.getInstance());
        System.out.println("Application initialized");
    }

    public static PoolManager getPoolManager() {
        return poolManagerThreadLocal.get();
    }
}

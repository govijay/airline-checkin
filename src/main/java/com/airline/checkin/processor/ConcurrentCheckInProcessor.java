package com.airline.checkin.processor;

import com.airline.checkin.dao.SeatsRepository;
import com.airline.checkin.dao.UserRepository;
import com.airline.checkin.dto.Seat;
import com.airline.checkin.dto.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentCheckInProcessor implements Processor{

    @Override
    public void process() {
        System.out.println("Processing concurrently");

        long startTime = System.currentTimeMillis();

        List<User> users = UserRepository.findAll();

        ExecutorService executorService = Executors.newFixedThreadPool(
            users.size());
        for (User user : users) {
            executorService.execute(() -> {
                Seat seat = SeatsRepository.bookSeat(user);
                if (seat != null) {
                    System.out.println(
                        "User " + user.getId() + ":" + user.getName() + " booked seat " + seat.getName());
                    return;
                }
            });
        }

        executorService.shutdown();
        try {
            // Wait for all tasks to complete or until the timeout (e.g., 10 seconds)
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                // Timeout elapsed before all tasks completed
                System.out.println("Timeout elapsed before all tasks completed");
            }
        } catch (InterruptedException e) {
            // Handle interruption
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Processing completed in " + (endTime - startTime) + " ms");
        printSeatAssignments();
    }

}

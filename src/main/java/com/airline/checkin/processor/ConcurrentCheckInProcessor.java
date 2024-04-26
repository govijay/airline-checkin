package com.airline.checkin.processor;

import com.airline.dao.SeatsRepository;
import com.airline.dao.UserRepository;
import dto.Seat;
import dto.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        long endTime = System.currentTimeMillis();
        System.out.println("Processing completed in " + (endTime - startTime) + " ms");
        printSeatAssignments();
    }

}

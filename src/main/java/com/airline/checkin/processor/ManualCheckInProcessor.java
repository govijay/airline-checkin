package com.airline.checkin.processor;

import com.airline.dao.SeatsRepository;
import com.airline.dao.UserRepository;
import dto.Seat;
import dto.User;
import java.util.List;



public class ManualCheckInProcessor implements Processor{

    @Override
    public void process() {
        long startTime = System.currentTimeMillis();

        List<User> users = UserRepository.findAll();

        for (User user : users) {
            System.out.println("Processing user: " + user.getName());
            Seat assignedSeat = SeatsRepository.bookSeat(user);
            System.out.println("Assigned seat: " + assignedSeat.getName() + " for user: " + user.getName());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Processing completed in " + (endTime - startTime) + " ms");
        printSeatAssignments();
    }

}

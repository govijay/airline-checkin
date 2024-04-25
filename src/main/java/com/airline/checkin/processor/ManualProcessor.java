package com.airline.checkin.processor;

import java.util.List;

import com.airline.dao.SeatsRepository;
import com.airline.dao.UserRepository;

import dto.Seat;
import dto.User;

public class ManualProcessor {

    public static void process() {

        SeatsRepository.clearAllSeats();
        System.out.println("All seats cleared");

        long startTime = System.currentTimeMillis();

        List<User> users = UserRepository.findAll();

        for (User user : users) {
            System.out.println("Processing user: " + user.getName());
            Seat unassignedSeat = SeatsRepository.findUnassignedSeat();
            System.out.println("Unassigned seat: " + unassignedSeat.getName() + " found");
            unassignedSeat.setUserId(user.getId());
            System.out.println("Assigning seat: " + unassignedSeat.getName() + " to user: " + user.getName());
            SeatsRepository.save(unassignedSeat);
            System.out.println("Seat assigned" + unassignedSeat.getName() + " to user: " + user.getName()+"\n");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Processing completed in " + (endTime - startTime) + " ms");


        System.out.println("Processing manually");
    }

}

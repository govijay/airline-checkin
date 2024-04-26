package com.airline.checkin.processor;

import com.airline.checkin.dao.SeatsRepository;
import com.airline.checkin.dto.Seat;
import java.util.Arrays;
import java.util.List;



public interface Processor {

    void process();

    default void printSeatAssignments() {
        System.out.println("Seat assignments:");
        List<Seat> seats = SeatsRepository.findAll();
        char[][] layout = new char[6][20];
        for (Seat seat : seats) {
            var layoutRow = (seat.getId() - 1) / 20;
            var layoutCol = (seat.getId() - 1) % 20;
            layout[layoutRow][layoutCol] = seat.getUserId() == 0 ? '-' : 'X';
        }
        for (char[] row : layout) {
            System.out.println(Arrays.toString(row).replace(",", " ").replace("[", "").replace("]", ""));
        }
        
    }

}

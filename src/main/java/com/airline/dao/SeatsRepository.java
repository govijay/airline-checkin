package com.airline.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.airline.db.ConnectionProvider;

import dto.Seat;

public class SeatsRepository {

    private static final Connection connection = ConnectionProvider.getConnection();

    private static final String UNASSIGNED_SEAT_QUERY = "SELECT * FROM seats WHERE user_id is null order by id LIMIT 1";

    public static List<Seat> findAll() {
        List<Seat> seats = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM seats");) {
            while (resultSet.next()) {
                Seat seat = new Seat();
                seat.setId(resultSet.getInt("id"));
                seat.setName(resultSet.getString("name"));
                seat.setFlightId(resultSet.getInt("flight_id"));
                seat.setUserId(resultSet.getInt("user_id"));
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    public static void save(Seat seat) {
        try (Statement statement = connection.createStatement();) {
            String query = "UPDATE seats SET user_id = " + seat.getUserId() + " WHERE id = " + seat.getId();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
     }
    }

    public static Seat findUnassignedSeat() {
        Seat seat = new Seat();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(UNASSIGNED_SEAT_QUERY);) {
            while (resultSet.next()) {
                seat.setId(resultSet.getInt("id"));
                seat.setName(resultSet.getString("name"));
                seat.setFlightId(resultSet.getInt("flight_id"));
                seat.setUserId(resultSet.getInt("user_id"));
                System.out.println("Seat ID: " + seat.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seat;
    }

    public static void clearAllSeats() {
        try (Statement statement = connection.createStatement();) {
            String query = "UPDATE seats SET user_id = null";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

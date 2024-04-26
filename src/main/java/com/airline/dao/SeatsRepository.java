package com.airline.dao;

import com.airline.db.ConnectionProvider;
import dto.Seat;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



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

    public static Seat bookSeat(User user) {
        System.out.println("Booking seat for user " + user.getId() + ":" + user.getName() + " by thread: " + Thread.currentThread().getName());

        String unassignedSeatQuery = "SELECT * FROM seats WHERE user_id is null order by id LIMIT 1 FOR UPDATE SKIP LOCKED";
        String updateSeatQuery = "UPDATE seats SET user_id = ? WHERE id = ?";

        ResultSet resultSet = null;
        Seat seat = null;

        try (PreparedStatement unassignedSeat = connection.prepareStatement(unassignedSeatQuery);
            PreparedStatement updateSeat = connection.prepareStatement(updateSeatQuery)) {
            connection.setAutoCommit(false);
            if (unassignedSeat.execute()) {
                resultSet = unassignedSeat.getResultSet();
                if (resultSet.next()) {
                    seat = new Seat();
                    seat.setId(resultSet.getInt("id"));
                    seat.setName(resultSet.getString("name"));
                    seat.setFlightId(resultSet.getInt("flight_id"));
                    seat.setUserId(user.getId());

                    updateSeat.setInt(1, user.getId());
                    updateSeat.setInt(2, seat.getId());
                    updateSeat.executeUpdate();
                } else {
                    System.out.println("No unassigned seats found");
                }
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return seat;
    }


    public static void save(Seat seat) {
        System.out.println("Saving seat by thread: " + Thread.currentThread().getName());
        try (Statement statement = connection.createStatement();) {
            String query = "UPDATE seats SET user_id = " + seat.getUserId() + " WHERE id = " + seat.getId();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
     }
    }

    public static Seat findUnassignedSeat() {
        System.out.println("Finding unassigned seat by thread: " + Thread.currentThread().getName());
        Seat seat = new Seat();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(UNASSIGNED_SEAT_QUERY);) {
            while (resultSet.next()) {
                seat.setId(resultSet.getInt("id"));
                seat.setName(resultSet.getString("name"));
                seat.setFlightId(resultSet.getInt("flight_id"));
                seat.setUserId(resultSet.getInt("user_id"));
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

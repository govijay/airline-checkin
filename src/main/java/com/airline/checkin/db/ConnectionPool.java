package com.airline.checkin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool
{
    private int checkedOut;

    private int maxConn;

    private final BlockingQueue<Connection> freeConnections;

    private String name;

    private String password;

    private String URL;

    private String user;

    private int timeout;

    public ConnectionPool(String name, String URL, String user, String password, int maxConn, int initConn,
        int timeout)
    {
        this.name = name;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.maxConn = maxConn;
        this.timeout = timeout > 0 ? timeout : 5;
        this.freeConnections = new ArrayBlockingQueue<>(maxConn);

        initPool(initConn);
    }

    private void initPool (int initConn)
    {

        for (int i = 0; i < initConn; i++) {
            Connection connection = newConnection();
            if (connection != null) {
                freeConnections.add(connection);
            }else {
                System.out.println("Failed to create new connection");
            }
        }

    }

    public Connection getConnection() throws SQLException
    {
        try {
            return getConnection(timeout * 1000);
        }catch (SQLException e) {
            throw new SQLException("No connection available");
        }

    }

    private synchronized Connection getConnection(long timeout) throws SQLException
    {
        System.out.println("Getting connection by thread: " + Thread.currentThread().getName() + " with timeout: " + timeout + " ms " + " free connections: " + freeConnections.size());
        long startTime = System.currentTimeMillis();
        long remaining = timeout;
        Connection connection = null;

        while ((connection = getPooledConnection()) == null) {
            System.out.println("No connection available from pool");
            try {
                System.out.println("Waiting for connection by thread: " + Thread.currentThread().getName() + " for " + remaining + " ms");
                wait(remaining);
            }
            catch (InterruptedException e) {
                System.out.println("Failed to get connection by thread: " + Thread.currentThread().getName());
            }
            remaining = timeout - (System.currentTimeMillis() - startTime);
            System.out.println("Remaining time: " + remaining   + " ms" + " by thread: " + Thread.currentThread().getName());
            if (remaining <= 0) {
                throw new SQLException("Timed out waiting for a free connection");
            }

        }
        if (!isConnectionOK(connection)) {
            return getConnection(remaining);
        }
        checkedOut++;
        System.out.println("Connection acquired by thread: " + Thread.currentThread().getName());
        return connection;
    }

    public synchronized void releaseConnection(Connection connection)
    {
        System.out.println("Releasing connection by thread: " + Thread.currentThread().getName());
        freeConnections.add(connection);
        checkedOut--;
        notifyAll();
        System.out.println("Connection released by thread: " + Thread.currentThread().getName());

    }

    public synchronized void release(){

        System.out.println("Releasing all connections by thread: " + Thread.currentThread().getName());
        for (Connection connection : freeConnections) {
            try {
                connection.close();
            }catch (SQLException e) {
                System.out.println("Failed to close connection by thread: " + Thread.currentThread().getName());
            }
        }
        freeConnections.clear();
        System.out.println("All connections released by thread: " + Thread.currentThread().getName());
    }

    private boolean isConnectionOK(Connection connection)
    {
        Statement statement = null;

        try {
            if (!connection.isClosed()) {
                statement = connection.createStatement();
                statement.close();
            }else {
                return false;
            }
        }catch (SQLException e) {
            if (statement != null) {
                try {
                    statement.close();
                }catch (SQLException ex) {

                }
            }
            return false;
        }
        return true;
    }

    private Connection getPooledConnection()
    {
        Connection connection = null;
        if (freeConnections.size() > 0) {
            connection = freeConnections.poll();
        }else  if (maxConn == 0 || checkedOut < maxConn) {
            connection = newConnection();
        }
        return connection;
    }

    private Connection newConnection()
    {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, user, password);
        }
        catch (SQLException e) {
            System.out.println("Failed to create new connection" + e.getMessage());
        }
        return connection;
    }


}

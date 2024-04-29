package com.airline.checkin.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class PoolManager
{
    private static int clients;

    private static PoolManager instance;

    private ConcurrentHashMap<String, ConnectionPool> pools = new ConcurrentHashMap();

    private Vector<Driver> drivers = new Vector();




    private PoolManager()
    {
        init();
    }

    private void init()
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try
        {
            properties.load(is);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        loadDrivers(properties);
        createPools(properties);

    }

    private void createPools(Properties properties)
    {
        System.out.println("Creating pools");
        Enumeration<?> propertyNames = properties.propertyNames();

        while (propertyNames.hasMoreElements()){
            String name = (String) propertyNames.nextElement();
            if (name.endsWith(".url")){
                //mysql.url jdbc:mysql://localhost:3306/airline-checkin-db
                //mysql.user airline-admin
                //mysql.password admin
                //mysql.maxconns 200
                //mysql.initialconns 200
                //mysql.timeout 5000
                String poolName = name.substring(0, name.lastIndexOf("."));
                String url = properties.getProperty(poolName + ".url");
                String user = properties.getProperty(poolName + ".user");
                String password = properties.getProperty(poolName + ".password");
                String maxConn = properties.getProperty(poolName + ".maxconns");
                String initConn = properties.getProperty(poolName + ".initialconns");
                String timeout = properties.getProperty(poolName + ".timeout");
                try
                {
                    int max = Integer.parseInt(maxConn);
                    int init = Integer.parseInt(initConn);
                    int time = Integer.parseInt(timeout);
                    ConnectionPool pool = new ConnectionPool(poolName, url, user, password, max, init, time);
                    pools.put(poolName, pool);
                }
                catch (Exception e)
                {
                    System.out.println("Failed to create pool: " + poolName);
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadDrivers(Properties properties)
    {
        System.out.println("Loading drivers");
        String driverClasses = properties.getProperty("drivers");
        StringTokenizer tokenizer = new StringTokenizer(driverClasses);

        while (tokenizer.hasMoreElements())
        {
            String driver = tokenizer.nextToken().trim();
            try
            {
                Driver driverClass = (Driver) Class.forName(driver).newInstance();
                DriverManager.registerDriver(driverClass);
                drivers.add(driverClass);
            }
            catch (Exception e)
            {
                System.out.println("Failed to load driver: " + driver);
                e.printStackTrace();
            }
        }
        System.out.println("Drivers loaded");
    }

    public static PoolManager getInstance()
    {
        clients++;
        if (instance == null)
        {
            instance = new PoolManager();
        }
        return instance;
    }

    public Connection getConnection(String name)
    {
        Connection connection = null;
        ConnectionPool pool = pools.get(name);
        if (pool != null)
        {
            try {
                connection = pool.getConnection();
            } catch (SQLException e) {
                System.out.println("Failed to get connection from pool: " + name);
                e.printStackTrace();

            }
        }
        System.out.println("Connection obtained from pool: " + name);
        return connection;
    }

    public void releaseConnection(String name, Connection connection)
    {

        ConnectionPool pool = pools.get(name);
        if (pool != null)
        {
            pool.releaseConnection(connection);
        }

    }

    public synchronized void release()
    {
        clients--;
        if (clients == 0)
        {
            System.out.println("Releasing all pools");
            for (ConnectionPool pool : pools.values())
            {
                pool.release();
            }
            for (Driver driver : drivers)
            {
                try
                {
                    DriverManager.deregisterDriver(driver);
                }
                catch (SQLException e)
                {
                    System.out.println("Failed to deregister driver: " + driver);
                    e.printStackTrace();
                }
            }
        }
    }


}

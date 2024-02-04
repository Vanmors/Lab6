package com.company.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class ServerDB {
    public Connection getConnectionServer() throws IOException {
        File file = new File("passwordDB.txt");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        String loginDB = properties.getProperty("login");
        String passwordDB = properties.getProperty("password");

        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            loginDB, passwordDB);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return c;
    }

    public void createTableServer() {
        Connection connection = null;
        Statement statement = null;
        CollectionDB connectionDB = new CollectionDB();

        connection = connectionDB.getConnection();

        try {
            String table = "CREATE TABLE IF NOT EXISTS collection(id serial, " +
                    "Name VARCHAR(200), " +
                    "CoordinateX INTEGER," +
                    "CoordinateY INTEGER," +
                    "CreationTime VARCHAR(200), " +
                    "Area VARCHAR(200), " +
                    "NumberOfRooms INTEGER, " +
                    "Furniture BOOLEAN," +
                    "TimeToMetroOnFoot INTEGER," +
                    "View VARCHAR(200)," +
                    "NameH VARCHAR(200)," +
                    "Year INTEGER," +
                    "NumberOfFlatsOnFloor INTEGER," +
                    "UserS VARCHAR(200) NOT NULL)";
            statement = connection.createStatement();
            statement.executeUpdate(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTableServer() {
        Connection connection = null;
        Statement statement = null;
        CollectionDB connectionDB = new CollectionDB();

        connection = connectionDB.getConnection();

        try {
            String firstFlat = "INSERT INTO collection(Name, CoordinateX, CoordinateY, CreationTime, " +
                    "Area, NumberOfRooms, Furniture, " +
                    "TimeToMetroOnFoot, View, NameH, " +
                    "Year, NumberOfFlatsOnFloor, Users) VALUES('Ivan',3,5,'2022-05-11',35,6,'true',50,'STREET','Ivan',18,810,'Default')";
            String secondFlat = "INSERT INTO collection(Name, CoordinateX, CoordinateY, CreationTime, " +
                    "Area, NumberOfRooms, Furniture, " +
                    "TimeToMetroOnFoot, View, NameH, " +
                    "Year, NumberOfFlatsOnFloor, Users) VALUES('Roman',43,41,'2022-05-12',78,3,'false',50,'BAD','Roman',23,400,'Default')";
            String thirdFlat = "INSERT INTO collection(Name, CoordinateX, CoordinateY, CreationTime, " +
                    "Area, NumberOfRooms, Furniture, " +
                    "TimeToMetroOnFoot, View, NameH, " +
                    "Year, NumberOfFlatsOnFloor, UserS) VALUES('Sergey',5,7,'2022-05-12',56,8,'true',50,'TERRIBLE','Sergey',45,230,'Default')";
            statement = connection.createStatement();
            statement.executeUpdate(firstFlat);
            statement.executeUpdate(secondFlat);
            statement.executeUpdate(thirdFlat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

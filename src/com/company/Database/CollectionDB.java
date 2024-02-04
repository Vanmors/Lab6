//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company.Database;

import com.company.data.Coordinates;
import com.company.data.Flat;
import com.company.data.House;
import com.company.data.View;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Properties;
import java.util.Stack;

public class CollectionDB {
    public CollectionDB() {
    }

    public void insertIntoTable(Flat flat) throws IOException {
        Connection connection = null;
        Statement statement = null;
        CollectionDB connectionDB = new CollectionDB();
        connection = connectionDB.getConnection();

        try {
            String date = String.valueOf(flat.getCreationDate().toLocalDate());
            String firstFlat = "INSERT INTO collection(Name, CoordinateX, CoordinateY, CreationTime, Area, NumberOfRooms, Furniture, TimeToMetroOnFoot, View, NameH, Year, NumberOfFlatsOnFloor, UserS) VALUES('" + flat.getName() + "'," + flat.getCoordinates().getX() + "," + flat.getCoordinates().getY() + ",'" + date + "'," + flat.getArea() + "," + flat.getNumberOfRooms() + "," + flat.getFurniture() + "," + flat.getTimeToMetroOnFoot() + ",'" + flat.getView() + "','" + flat.getHouse().getName() + "'," + flat.getHouse().getYear() + "," + flat.getHouse().getNumberOfFlatsOnFloor() + ",'" + flat.getUser() + "')";
            System.out.println(firstFlat);
            statement = connection.createStatement();
            statement.executeUpdate(firstFlat);
            System.out.println("Value inserted successfully");
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public void createTable() throws IOException {
        Connection connection = null;
        Statement statement = null;
        CollectionDB connectionDB = new CollectionDB();
        connection = connectionDB.getConnection();

        try {
            String table = "CREATE TABLE IF NOT EXISTS collection(id serial, Name VARCHAR(200), CoordinateX INTEGER,CoordinateY INTEGER,CreationTime VARCHAR(200), Area VARCHAR(200), NumberOfRooms INTEGER, Furniture BOOLEAN,TimeToMetroOnFoot INTEGER,View VARCHAR(200),NameH VARCHAR(200),Year INTEGER,NumberOfFlatsOnFloor INTEGERUserS VARCHAR(200) NOT NULL)";
            statement = connection.createStatement();
            statement.executeUpdate(table);
            System.out.println("finished");
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public Connection getConnection() {
        File file = new File("passwordDB.txt");
        Properties properties = new Properties();

        try {
            properties.load(new FileReader(file));
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        String loginDB = properties.getProperty("login");
        String passwordDB = properties.getProperty("password");
        Connection c = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", passwordDB);
        } catch (Exception var7) {
            var7.printStackTrace();
            System.err.println(var7.getClass().getName() + ": " + var7.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
        return c;
    }

    public void clearTable() throws IOException {
        Connection connection = null;
        Statement statement = null;
        CollectionDB connectionDB = new CollectionDB();
        connection = connectionDB.getConnection();

        try {
            String table = "DELETE FROM collection";
            statement = connection.createStatement();
            statement.executeUpdate(table);
            System.out.println("finished");
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void removeObject(String sqlRequest) {
        Connection connection = null;
        Statement statement = null;
        CollectionDB connectionDB = new CollectionDB();
        connection = connectionDB.getConnection();

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlRequest);
            System.out.println("finished");
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void update(int id, Flat flat) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        CollectionDB connectionDB = new CollectionDB();
        connection = connectionDB.getConnection();
        String date = String.valueOf(flat.getCreationDate().toLocalDate());
        String updateTable = "UPDATE collection SET (Name, CoordinateX, CoordinateY, CreationTime,Area, NumberOfRooms, Furniture, TimeToMetroOnFoot, View, NameH, Year, NumberOfFlatsOnFloor, UserS) = ('" + flat.getName() + "'," + flat.getCoordinates().getX() + "," + flat.getCoordinates().getY() + ",'" + date + "'," + flat.getArea() + "," + flat.getNumberOfRooms() + "," + flat.getFurniture() + "," + flat.getTimeToMetroOnFoot() + ",'" + flat.getView() + "','" + flat.getHouse().getName() + "'," + flat.getHouse().getYear() + "," + flat.getHouse().getNumberOfFlatsOnFloor() + ",'" + flat.getUser() + "') where id = '" + id + "'";
        statement = connection.createStatement();
        statement.executeUpdate(updateTable);
    }

    public void parseCommandProject(Stack<Flat> stack) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        CollectionDB connectionDB = new CollectionDB();
        connection = connectionDB.getConnection();

        try {
            String table = "SELECT * FROM collection ";
            statement = connection.createStatement();
            rs = statement.executeQuery(table);

            while(rs.next()) {
                Coordinates coordinates = new Coordinates(rs.getInt(3), rs.getLong(4));
                House house = new House(rs.getString(11), rs.getInt(12), rs.getInt(13));
                Timestamp t = rs.getTimestamp(5);
                ZonedDateTime zdt = t.toInstant().atZone(ZoneId.of("Europe/Moscow"));
                Flat flat = new Flat(rs.getInt(1), rs.getString(2), coordinates, zdt, rs.getInt(6), rs.getLong(7), rs.getBoolean(8), rs.getLong(9), View.valueOf((String)rs.getObject(10)), house, rs.getString(14));
                stack.push(flat);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

    }
}

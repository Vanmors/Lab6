package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.Database.PasswordDB;


import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import java.util.Objects;
import java.util.Random;

public class PasswordCheckDB {
    private ByteBuffer buffer;

    public PasswordCheckDB(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public String sault() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : array) {
            stringBuilder.append(String.format("%02X", b));
        }
        String salt = stringBuilder.toString().toLowerCase();

        return salt;
    }

    public String passwordCheck() throws SQLException, NoSuchAlgorithmException {
        String result = "wasEntered";
        String s = "";
        try {
            while (true) {
                s = s + (char) buffer.get();
            }
        } catch (BufferUnderflowException e) {

        }

        String[] n = s.split(" ");
        if (n[0].equals("password")) {
            String sPassword = "";
            String pepper = "e6rgf34";
            String salt = sault();

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = messageDigest.digest(n[2].getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedPassword) {
                stringBuilder.append(String.format("%02X", b));
            }
            sPassword = stringBuilder.toString().toLowerCase();

            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            CollectionDB connectionDB = new CollectionDB();

            connection = connectionDB.getConnection();

            PasswordDB passwordDB = new PasswordDB(n[1], salt + sPassword + pepper, salt);
            try {
                String table = "SELECT * FROM passwords ";
                statement = connection.createStatement();
                rs = statement.executeQuery(table);

                while (rs.next()) {
                    if (rs.getString(1).equals(n[1])) {
                        //System.out.println(hashedPassword);
                        if (Objects.equals(rs.getString(2), rs.getString(3) + sPassword + pepper)) {
                            return "Entered";
                        } else {
                            return "Incorrect password";
                        }
                    }
                }
            } catch (Exception e) {
                passwordDB.createPasswordTable();
            }
            passwordDB.insertIntoTable();
            return "Entered";
        }
        return result;
    }

}

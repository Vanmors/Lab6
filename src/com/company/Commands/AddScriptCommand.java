//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Coordinates;
import com.company.data.Flat;
import com.company.data.House;
import com.company.data.View;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class AddScriptCommand {
    public AddScriptCommand() {
    }

    public static String add(Stack<Flat> st, Scanner file, String user) throws IOException {
        try {
            String name = setName(file);
            Coordinates coordinates = setCoordinates(file);
            int area = setArea(file);
            long numberOfRooms = setNumberOfRooms(file);
            Boolean furniture = setFurniture(file);
            long timeToMetro = setTimeToMetroOnFoot(file);
            View view = setView(file);
            House house = setHouse(file);
            if (name != null && coordinates != null && area != -1 && numberOfRooms != -1L && furniture != null && timeToMetro != -1L && view != null && house != null) {
                int id;
                try {
                    id = ((Flat)st.peek()).getId() + 1;
                } catch (EmptyStackException var16) {
                    id = 1;
                }

                Flat f = new Flat(id, name, coordinates, setCreationDate(), area, numberOfRooms, furniture, timeToMetro, view, house, user);
                CollectionDB collectionDB = new CollectionDB();
                collectionDB.insertIntoTable(f);
                st.push(f);
                return "Объект добавлен в коллекцию";
            } else {
                return "Неверно введён скрипт";
            }
        } catch (NoSuchElementException var17) {
            return "Неверно введён скрипт";
        }
    }

    public static String setName(Scanner file) {
        String Name = file.nextLine();
        return Name.trim().equals("") ? null : Name;
    }

    public static Coordinates setCoordinates(Scanner file) {
        int x = file.nextInt();
        long y = file.nextLong();
        if (x >= 0 && y >= 0L) {
            return new Coordinates(x, y);
        } else {
            System.out.println("Неверно введён скрипт");
            return null;
        }
    }

    public static ZonedDateTime setCreationDate() {
        return ZonedDateTime.now();
    }

    public static int setArea(Scanner file) {
        int area = file.nextInt();
        if (area >= 0) {
            return area;
        } else {
            System.out.println("Неверно введён скрипт");
            return -1;
        }
    }

    public static Long setNumberOfRooms(Scanner file) {
        Long numberOfRooms = file.nextLong();
        if (numberOfRooms >= 0L) {
            return numberOfRooms;
        } else {
            System.out.println("Неверно введён скрипт");
            return -1L;
        }
    }

    public static Boolean setFurniture(Scanner file) {
        Boolean furniture = file.nextBoolean();
        return furniture;
    }

    public static Long setTimeToMetroOnFoot(Scanner file) {
        Long timeToMetroOnFoot = file.nextLong();
        if (timeToMetroOnFoot >= 0L) {
            return timeToMetroOnFoot;
        } else {
            System.out.println("Неверно введён скрипт");
            return -1L;
        }
    }

    public static View setView(Scanner file) {
        View v = null;
        String view = file.nextLine();
        view = file.nextLine();
        if (view.equals("TERRIBLE")) {
            v = View.TERRIBLE;
        } else if (view.equals("STREET")) {
            v = View.STREET;
        } else if (view.equals("BAD")) {
            v = View.BAD;
        } else if (view.equals("PARK")) {
            v = View.PARK;
        }

        return v;
    }

    public static House setHouse(Scanner file) {
        String Name = file.nextLine();
        Integer year = file.nextInt();
        Integer numberOfFlatsOnFloor = file.nextInt();
        if (year >= 0 && numberOfFlatsOnFloor >= 0) {
            return new House(Name, year, numberOfFlatsOnFloor);
        } else {
            System.out.println("Неверно введён скрипт");
            return null;
        }
    }
}

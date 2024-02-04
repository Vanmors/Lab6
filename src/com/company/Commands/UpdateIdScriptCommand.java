

package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Coordinates;
import com.company.data.Flat;
import com.company.data.House;
import com.company.data.View;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class UpdateIdScriptCommand {
    public UpdateIdScriptCommand() {
    }

    public static void updateIdScriptCommand(Stack<Flat> st, Scanner file, String[] n, String user) throws IOException, SQLException {
        while(true) {
            try {
                String name = setName(file);
                Coordinates coordinates = setCoordinates(file);
                int area = setArea(file);
                long numberOfRooms = setNumberOfRooms(file);
                Boolean furniture = setFurniture(file);
                long timeToMetro = setTimeToMetroOnFoot(file);
                View view = setView(file);
                House house = setHouse(file);
                if (name == null || coordinates == null || area == -1 || numberOfRooms == -1L || furniture == null || timeToMetro == -1L || view == null || house == null) {
                    System.out.println("Неверно введён скрипт");
                    continue;
                }

                try {
                    int id = Integer.parseInt(n[1]);
                    Flat f = new Flat(id, name, coordinates, setCreationDate(), area, numberOfRooms, furniture, timeToMetro, view, house, user);
                    ArrayList<Flat> list = new ArrayList<>(st);
                    if (((Flat)list.get(id - 1)).getUser().equals(user)) {
                        CollectionDB collectionDB = new CollectionDB();
                        collectionDB.update(id, (Flat)list.get(id - 1));
                        list.set(id - 1, f);

                        while(!st.empty()) {
                            st.pop();
                        }

                        Iterator var18 = list.iterator();

                        while(var18.hasNext()) {
                            Flat flat = (Flat)var18.next();
                            st.push(flat);
                            System.out.println(flat);
                        }
                    }
                } catch (IndexOutOfBoundsException var20) {
                    System.out.println("Элемента с таким id не существует");
                }
            } catch (NoSuchElementException var21) {
                System.out.println("Неверно введён скрипт");
            }

            return;
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

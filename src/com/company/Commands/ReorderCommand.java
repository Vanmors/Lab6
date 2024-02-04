package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Coordinates;
import com.company.data.Flat;
import com.company.data.House;
import com.company.data.View;
import java.io.IOException;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Stack;

public class ReorderCommand implements Serializable, ICommand {
    public ReorderCommand() {
    }

    public String execute(Stack<Flat> st) {
        ArrayList<Flat> list = new ArrayList(st);
        CollectionDB collectionDB = new CollectionDB();

        try {
            collectionDB.clearTable();
        } catch (IOException var21) {
            var21.printStackTrace();
        }

        while(!st.empty()) {
            st.pop();
        }

        int id = 0;

        while(!list.isEmpty()) {
            int i = list.size() - 1;
            ++id;
            String name = ((Flat)list.get(i)).getName();
            Coordinates coordinates = ((Flat)list.get(i)).getCoordinates();
            ZonedDateTime creationDate = ((Flat)list.get(i)).getCreationDate();
            int area = ((Flat)list.get(i)).getArea();
            long numberOfRooms = ((Flat)list.get(i)).getNumberOfRooms();
            Boolean furniture = ((Flat)list.get(i)).getFurniture();
            long timeToMetro = ((Flat)list.get(i)).getTimeToMetroOnFoot();
            View view = ((Flat)list.get(i)).getView();
            House house = ((Flat)list.get(i)).getHouse();
            String user = ((Flat)list.get(i)).getUser();
            Flat f = new Flat(id, name, coordinates, creationDate, area, numberOfRooms, furniture, timeToMetro, view, house, user);

            try {
                collectionDB.insertIntoTable(f);
            } catch (IOException var20) {
                var20.printStackTrace();
            }

            st.push(f);
            list.remove(i);
        }

        return "Complete";
    }
}


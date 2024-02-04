

package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Flat;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class AddIfMinCommand implements ICommand, Serializable {
    private Flat f;
    private int id;

    public AddIfMinCommand(Flat f, int id) {
        this.f = f;
        this.id = id;
    }

    public String execute(Stack<Flat> st) {
        ArrayList<Flat> list = new ArrayList(st);
        Long minNumberOfRooms = ((Flat)list.get(0)).getNumberOfRooms();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Flat flat = (Flat)var4.next();
            if (flat.getNumberOfRooms() < minNumberOfRooms) {
                minNumberOfRooms = flat.getNumberOfRooms();
            }
        }

        if (this.f.getNumberOfRooms() < minNumberOfRooms) {
            this.f.setID(((Flat)st.peek()).getId() + 1);
            CollectionDB collectionDB = new CollectionDB();

            try {
                collectionDB.insertIntoTable(this.f);
            } catch (IOException var6) {
                var6.printStackTrace();
            }

            st.push(this.f);
        }

        return "Complete";
    }
}
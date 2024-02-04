

package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Flat;
import com.company.data.House;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Stack;

public class RemoveAllByHouseCommand implements ICommand, Serializable {
    private House h;
    private int id;
    private String user;

    public RemoveAllByHouseCommand(House h, int id, String user) {
        this.h = h;
        this.id = id;
        this.user = user;
    }

    public String execute(Stack<Flat> st) {
        String result = "Complete";
        if (!st.empty()) {
            while(true) {
                try {
                    CollectionDB collectionDB = new CollectionDB();

                    while(!st.empty()) {
                        st.pop();
                    }

                    collectionDB.parseCommandProject(st);
                    ArrayList<Flat> copyOfCollection = new ArrayList(st);
                    Iterator var5 = copyOfCollection.iterator();

                    while(var5.hasNext()) {
                        Flat flat = (Flat)var5.next();
                        if (flat.getHouse().getName().equals(this.h.getName()) && flat.getHouse().getYear().equals(this.h.getYear()) && flat.getHouse().getNumberOfFlatsOnFloor().equals(this.h.getNumberOfFlatsOnFloor())) {
                            if (flat.getUser().equals(this.user)) {
                                collectionDB.removeObject("DELETE FROM collection WHERE id = '" + flat.getId() + "'");
                                st.remove(flat.getId());
                                result = "Item removed";
                            } else {
                                result = "You don't have permission";
                            }
                        }
                    }

                    return result;
                } catch (SQLException | InputMismatchException var7) {
                    result = "Data entered incorrectly";
                }
            }
        } else {
            return result;
        }
    }
}

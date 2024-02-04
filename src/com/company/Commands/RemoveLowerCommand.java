
package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Flat;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Stack;

public class RemoveLowerCommand implements ICommand, Serializable {
    private int id;
    private String user;

    public RemoveLowerCommand(int id, String user) {
        this.id = id;
        this.user = user;
    }

    public String execute(Stack<Flat> st) {
        String result = "Complete";
        if (!st.empty()) {
            while(true) {
                while(true) {
                    try {
                        CollectionDB collectionDB = new CollectionDB();

                        while(!st.empty()) {
                            st.pop();
                        }

                        collectionDB.parseCommandProject(st);
                        ArrayList<Flat> copyOfCollection = new ArrayList<>(st);
                        Iterator var5 = copyOfCollection.iterator();

                        while(var5.hasNext()) {
                            Flat flat = (Flat)var5.next();
                            if (flat.getNumberOfRooms() < (long)this.id) {
                                if (flat.getUser().equals(this.user)) {
                                    collectionDB.removeObject("DELETE FROM collection WHERE id = '" + flat.getId() + "'");
                                    st.remove(flat.getId());
                                } else {
                                    result = "You don't have permission";
                                }
                            }
                        }

                        if (this.id >= 0) {
                            return result;
                        }

                        result = "Data entered incorrectly";
                    } catch (SQLException | InputMismatchException var7) {
                        result = "Data entered incorrectly";
                    }
                }
            }
        } else {
            return result;
        }
    }
}

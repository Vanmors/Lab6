package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Flat;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class UpdateIdCommand implements ICommand, Serializable {
    private Flat f;
    private int id;
    private String user;

    public UpdateIdCommand(Flat f, int id, String user) {
        this.f = f;
        this.id = id;
        this.user = user;
    }

    public String execute(Stack<Flat> st) {
        String result = "Element changed";
        this.f.setID(this.id);
        CollectionDB collectionDB = new CollectionDB();
        ArrayList<Flat> list = new ArrayList<>(st);

        try {
            if (((Flat)list.get(this.id - 1)).getUser().equals(this.user)) {
                collectionDB.update(this.id, (Flat)list.get(this.id - 1));
                list.set(this.id - 1, this.f);

                while(!st.empty()) {
                    st.pop();
                }

                Iterator var5 = list.iterator();

                while(var5.hasNext()) {
                    Flat flat = (Flat)var5.next();
                    st.push(flat);
                }
            } else {
                result = "You don't have permission";
            }
        } catch (IndexOutOfBoundsException var7) {
            result = "There is no element with this id";
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        return result;
    }
}

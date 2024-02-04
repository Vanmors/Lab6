

package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Flat;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ClearCommand implements Serializable, ICommand {
    private String user;

    public ClearCommand(String user) {
        this.user = user;
    }

    public String execute(Stack<Flat> st) {
        int size = st.size();
        int userSize = 0;
        ArrayList<Flat> list = new ArrayList(st);
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Flat flat = (Flat)var5.next();
            if (flat.getUser().trim().equals(this.user.trim())) {
                ++userSize;
            }
        }

        if (userSize != size) {
            return "You don't have permission";
        } else {
            CollectionDB collectionDB = new CollectionDB();

            try {
                collectionDB.clearTable();
            } catch (IOException var7) {
                var7.printStackTrace();
            }

            while(!st.empty()) {
                st.pop();
            }

            return "Complete";
        }
    }
}


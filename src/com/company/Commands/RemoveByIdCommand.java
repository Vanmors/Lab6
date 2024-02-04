package com.company.Commands;

import com.company.Database.CollectionDB;
import com.company.data.Flat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

public class RemoveByIdCommand implements ICommand, Serializable {
    private String result;
    private int id;
    private String user;

    public RemoveByIdCommand(int id, String user) {
        this.id = id;
        this.user = user;
    }

    public String execute(Stack<Flat> st) throws ArrayIndexOutOfBoundsException {
        ArrayList<Flat> list = new ArrayList(st);
        long count = (long)list.size();

        try {
            if (this.id <= 0 && (long)this.id >= count) {
                this.result = "Data entered incorrectly";
            } else if (((Flat)list.get(this.id - 1)).getUser().equals(this.user)) {
                CollectionDB collectionDB = new CollectionDB();
                collectionDB.removeObject("DELETE FROM collection WHERE id = '" + this.id + "'");
                st.remove(st.get(this.id - 1));
                this.result = "Item removed";
            } else {
                this.result = "You don't have permission";
            }
        } catch (ArrayIndexOutOfBoundsException | InputMismatchException var6) {
            this.result = "Data entered incorrectly";
        }

        return this.result;
    }
}

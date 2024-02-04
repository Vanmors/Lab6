package com.company.Commands;

    /**
     * добавляет элемент в коллекцию
     *
     * @param st объект коллекции Stack
     */

import com.company.Commands.ICommand;
import com.company.Database.CollectionDB;
import com.company.data.Flat;

import java.io.IOException;
import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;

public class AddCommand implements Serializable, ICommand {
    private Flat f;
    private String user;

    public AddCommand(Flat f) {
        this.f = f;
    }

    public String execute(Stack<Flat> st) {
        CollectionDB collectionDB = new CollectionDB();

        try {
            collectionDB.insertIntoTable(this.f);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        try {
            this.f.setID(((Flat)st.peek()).getId() + 1);
            st.push(this.f);
        } catch (EmptyStackException var4) {
            this.f.setID(1);
            st.push(this.f);
        }

        return "Object insert in collection";
    }
}

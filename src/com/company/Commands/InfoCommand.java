package com.company.Commands;

import com.company.data.Flat;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class InfoCommand implements Serializable, ICommand {
    public InfoCommand() {
    }
    /**
     * выводит в стандартный поток вывода информацию о коллекции
     * @param st объект коллекции Stack
     */

    public String execute(Stack<Flat> st) {
        ArrayList<Flat> list = new ArrayList(st);
        ZonedDateTime initializationDate = null;
        int count = 0;

        for(Iterator var5 = list.iterator(); var5.hasNext(); ++count) {
            Flat flat = (Flat)var5.next();
            initializationDate = flat.getCreationDate();
        }

        return "Type collection: Stack\ninitialization date: " + initializationDate + "\nnumber of elements: " + count;
    }
}

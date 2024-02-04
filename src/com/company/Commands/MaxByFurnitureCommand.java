package com.company.Commands;

import com.company.data.Flat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class MaxByFurnitureCommand implements Serializable, ICommand {
    public MaxByFurnitureCommand() {
    }

    public String execute(Stack<Flat> st) {
        String maxByFurniture = "";
        if (!st.empty()) {
            ArrayList<Flat> copyOfCollection = new ArrayList<>(st);
            maxByFurniture = copyOfCollection.stream().filter(Flat::getFurniture).findFirst().toString();
        }

        return maxByFurniture;
    }
}


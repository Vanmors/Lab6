package com.company.Commands;

import com.company.data.Flat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class MaxByFurnitureCommand implements Serializable, ICommand {
    /**
     * выводит любой объект из коллекции, значение поля furniture которого является максимальным
     * @param st объект коллекции Stack
     */
    private static final long serialVersionUID = 1L;
    public String execute(Stack<Flat> st) {
        String maxByFurniture = "";
        if (!st.empty()) {
            ArrayList<Flat> copyOfCollection = new ArrayList<>(st);
            for (Flat flat : copyOfCollection) {
                if (flat.getFurniture()) {
                    maxByFurniture = maxByFurniture + flat.toString();
                    break;
                }
            }
        }
        return maxByFurniture;
    }

}

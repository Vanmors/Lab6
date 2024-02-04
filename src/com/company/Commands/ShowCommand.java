package com.company.Commands;

import com.company.data.Flat;
import java.io.Serializable;
import java.util.Stack;
import java.util.stream.Collectors;

public class ShowCommand implements Serializable, ICommand {
    public ShowCommand() {
    }

    public String execute(Stack<Flat> st) {
        return !st.empty() ? (String)st.stream().map(Flat::toString).collect(Collectors.joining("\n============\n")) : "collection is empty";
    }
}



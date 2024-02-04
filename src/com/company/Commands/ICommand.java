package com.company.Commands;

import com.company.data.Flat;
import java.util.Stack;

public interface ICommand {
    String execute(Stack<Flat> var1);
}
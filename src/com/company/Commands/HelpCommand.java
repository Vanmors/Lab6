package com.company.Commands;

import com.company.data.Flat;

import java.io.Serializable;
import java.util.Stack;

public class HelpCommand implements Serializable, ICommand {
    private static final long serialVersionUID = 6529685098267757690L;
    /**
     * выводит справку по доступным командам
     * @return
     */
    @Override
    public String execute(Stack<Flat> st)  {
            String s ="help : вывести справку по доступным командам\n" +
                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add {element} : добавить новый элемент в коллекцию\n" +
                    "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                    "remove_by_id id : удалить элемент из коллекции по его id\n" +
                    "clear : очистить коллекцию\n" +
                    "save : сохранить коллекцию в файл\n" +
                    "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "exit : завершить программу (без сохранения в файл)\n" +
                    "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                    "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                    "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                    "remove_all_by_house house : удалить из коллекции все элементы, значение поля house которого эквивалентно заданному\n" +
                    "average_of_number_of_rooms : вывести среднее значение поля numberOfRooms для всех элементов коллекции\n" +
                    "max_by_furniture : вывести любой объект из коллекции, значение поля furniture которого является максимальным";
    return s;
    }

    @Override
    public String execute(Stack<Flat> st, Flat f) {
        return null;
    }
}

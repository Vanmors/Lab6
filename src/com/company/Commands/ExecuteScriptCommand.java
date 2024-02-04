
package com.company.Commands;

import com.company.data.Flat;
import com.company.excepption.UnknownCommandException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class ExecuteScriptCommand implements ICommand, Serializable {
    private String scriptFile;
    private ArrayList<String> list;
    private String user;

    public ExecuteScriptCommand(String scriptFile, String user) {
        this.scriptFile = scriptFile;
        this.user = user;
    }

    public String execute(Stack<Flat> st) {
        String result = null;
        int id = 0;

        try {
            File file = new File(this.scriptFile);
            Scanner sc = new Scanner(file);

            while(true) {
                while(!sc.hasNextLine()) {
                }

                String command = sc.nextLine();
                String[] n = command.split(" ");

                try {
                    id = Integer.parseInt(n[1]);
                } catch (ArrayIndexOutOfBoundsException var12) {
                }

                if (command.equals("exit")) {
                    System.exit(0);
                } else if (command.equals("help")) {
                    result = result + "\n" + (new HelpCommand()).execute(st);
                } else if (command.equals("show")) {
                    result = result + "\n" + (new ShowCommand()).execute(st);
                } else {
                    String var19;
                    if (command.equals("add")) {
                        AddScriptCommand.add(st, sc, this.user);
                        var19 = sc.nextLine();
                    } else if (command.equals("remove_by_id")) {
                        result = result + "\n" + (new RemoveByIdCommand(id, this.user)).execute(st);
                    } else if (command.equals("clear")) {
                        result = result + "\n" + (new ClearCommand(this.user)).execute(st);
                    } else if (command.equals("average_of_number_of_rooms")) {
                        result = result + "\n" + (new AverageOfNumberOfRooms()).execute(st);
                    } else if (command.equals("reorder")) {
                        result = result + "\n" + (new ReorderCommand()).execute(st);
                    } else if (command.equals("max_by_furniture")) {
                        result = result + "\n" + (new MaxByFurnitureCommand()).execute(st);
                    } else if (command.equals("info")) {
                        result = result + "\n" + (new InfoCommand()).execute(st);
                    } else if (!command.equals("execute_script " + n[1])) {
                        if (n[0].equals("update_id")) {
                            try {
                                UpdateIdScriptCommand.updateIdScriptCommand(st, sc, n, this.user);
                                var19 = sc.nextLine();
                            } catch (ArrayIndexOutOfBoundsException var14) {
                                System.out.println("Команда введена неверно");
                            } catch (SQLException var15) {
                                var15.printStackTrace();
                            }
                        } else if (n[0].equals("remove_lower")) {
                            try {
                                (new RemoveLowerCommand(id, this.user)).execute(st);
                            } catch (ArrayIndexOutOfBoundsException var13) {
                                System.out.println("Команда введена неверно");
                            }
                        } else {
                            if (!command.equals("add_if_min")) {
                                throw new UnknownCommandException(command);
                            }

                            AddIfMinScriptCommand.addIfMinScriptCommand(st, sc, this.user);
                            var19 = sc.nextLine();
                        }
                    } else {
                        this.list.add(n[1]);
                        int count = this.list.size() - 1;
                        int i = 0;
                        Iterator var10 = this.list.iterator();

                        while(var10.hasNext()) {
                            String ff = (String)var10.next();
                            if (n[1].equals(ff)) {
                                ++i;
                            }
                        }

                        if (i == 2) {
                            System.out.println("Вызывает рекурсию");
                            break;
                        }

                        (new ExecuteScriptCommand(n[1], this.user)).execute(st);
                    }
                }

                if (!sc.hasNextLine()) {
                    return result;
                }
            }
        } catch (FileNotFoundException var16) {
            var16.printStackTrace();
        } catch (UnknownCommandException var17) {
            var17.printStackTrace();
        } catch (IOException var18) {
            var18.printStackTrace();
        }

        return result;
    }
}

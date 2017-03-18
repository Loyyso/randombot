package fr.loysofreezee.randombot.commands;

import fr.loysofreezee.randombot.commands.list.Ping;
import java.util.ArrayList;

public class CommandRegisterer {

    private static ArrayList<Command> commands = new ArrayList<>();
    static {
        commands.add(new Ping());
    }

    public static ArrayList<Command> getCommands() {
        return commands;
    }
}

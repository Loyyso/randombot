package fr.loysofreezee.randombot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {
    private String commandName;

    public Command(String name) {
        commandName = name;
    }

    public abstract String run(MessageReceivedEvent e);

    public String getCommandName() {
        return commandName;
    }
}
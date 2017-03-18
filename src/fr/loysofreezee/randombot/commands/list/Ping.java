package fr.loysofreezee.randombot.commands.list;

import fr.loysofreezee.randombot.commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Ping extends Command {
    public Ping() {
        super("ping");
    }

    @Override
    public String run(MessageReceivedEvent e) {
        return "Pong!";
    }
}

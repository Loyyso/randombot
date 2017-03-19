package fr.loysofreezee.randombot.listeners;

import fr.loysofreezee.randombot.commands.Command;
import fr.loysofreezee.randombot.commands.CommandRegisterer;
import fr.loysofreezee.randombot.utils.log.Log;
import fr.loysofreezee.randombot.utils.log.LogType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static fr.loysofreezee.randombot.commands.Command.COMMAND_PREFIX;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        User author = event.getAuthor();
        MessageChannel channel = event.getChannel();
        String message = event.getMessage().getContent();

        boolean log = false;
        if (!event.getAuthor().isBot()) {
            for (Command command: CommandRegisterer.getCommands()) {
                if ((message + " ").contains(COMMAND_PREFIX + command.getCommandName() + " ")) {
                    String result = command.run(event);
                    if (!result.equals("")) {
                        channel.sendMessage(result).queue();
                    }

                    // Log
                    log = true;
                    String toLog = result;
                    if (toLog.length() >= 50) toLog = toLog.substring(50, toLog.length()) + "...";
                    Log.log(LogType.INFO, new String[] { "Command" },
                            author.getName() + " ran " + COMMAND_PREFIX + command.getCommandName() + ":\n\t  > " + toLog.replace("\n", "\n\t\t"));
                }
            }
        }

        if (!log) Log.logMessage(event.getMessage());
    }
}

package fr.loysofreezee.randombot.listeners;

import fr.loysofreezee.randombot.commands.Command;
import fr.loysofreezee.randombot.commands.CommandRegisterer;
import fr.loysofreezee.randombot.utils.log.Log;
import fr.loysofreezee.randombot.utils.log.LogFlags;
import fr.loysofreezee.randombot.utils.log.LogType;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static fr.loysofreezee.randombot.commands.Command.COMMAND_PREFIX;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        MessageChannel channel = event.getChannel();
        String message = event.getMessage().getContent();

        if (!event.getAuthor().isBot()) {
            for (Command command: CommandRegisterer.getCommands()) {
                if ((message + " ").contains(COMMAND_PREFIX + command.getCommandName() + " ")) {
                    String result = command.run(event);
                    if (!result.equals("")) {
                        channel.sendMessage(result).queue();
                    }

                    // Log
                    String toLog = result;
                    if (toLog.length() >= 50) toLog = toLog.substring(50, toLog.length());
                    Log.log(LogType.INF, new LogFlags[] { LogFlags.CMD }, author.getName() + " ran " + COMMAND_PREFIX + command.getCommandName() + ":\n\t" + toLog);
                }
            }
        }

        /*if (event.isFromType(ChannelType.TEXT)) {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();

            String name = member.getEffectiveName();

            System.out.printf("[%s]#%s <%s>: %s\n", guild.getName(), textChannel.getName(), name, message);
        } else if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PRIV]<%s>: %s\n", author.getName(), message);
        } */ // TO REPLACE (with new Log system)
    }
}

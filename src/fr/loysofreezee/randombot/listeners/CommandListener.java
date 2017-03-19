package fr.loysofreezee.randombot.listeners;

import fr.loysofreezee.randombot.commands.Command;
import fr.loysofreezee.randombot.commands.CommandRegisterer;
import fr.loysofreezee.randombot.utils.log.Log;
import fr.loysofreezee.randombot.utils.log.LogFlag;
import fr.loysofreezee.randombot.utils.log.LogType;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        assert (!event.getAuthor().isBot());
        User author = event.getAuthor();
        MessageChannel channel = event.getChannel();
        String msg = event.getMessage().getContent();

        for (Command command: CommandRegisterer.getCommands()) {
            if ((msg + " ").contains("!" + command.getCommandName() + " ")) {
                String result = command.run(event);
                if (!result.equals("")) {
                    channel.sendMessage(result).queue();
                }

                // LOG
                String logResult = result;
                if (logResult.length() > 50) logResult = logResult.substring(50, logResult.length());
                Log.log(author.getName() + " sent " + command.getCommandName() + ":\n\t" + logResult, LogType.INF, LogFlag.CMD, LogFlag.PBL);
            }
        }

        if (event.isFromType(ChannelType.TEXT)) {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();

            String name = member.getEffectiveName();

            System.out.printf("[%s]#%s <%s>: %s\n", guild.getName(), textChannel.getName(), name, msg);
        } else if (event.isFromType(ChannelType.PRIVATE)) {
            PrivateChannel privateChannel = event.getPrivateChannel();

            System.out.printf("[PRIV]<%s>: %s\n", author.getName(), msg);
        }
    }
}

package fr.loysofreezee.randombot.listeners;

import fr.loysofreezee.randombot.commands.Command;
import fr.loysofreezee.randombot.commands.CommandRegisterer;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    public void onMessageRecieved(MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        long responseNumber = event.getResponseNumber();

        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();

        String msg = message.getContent();

        boolean bot = author.isBot();

        for (Command command: CommandRegisterer.getCommands()) {
            if (event.getMessage().getContent().contains("!" + command.getCommandName()) && !bot) {
                String result = command.run(event);
                if(!result.equals("")) {
                    channel.sendMessage(result).queue();
                }
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

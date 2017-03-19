package fr.loysofreezee.randombot;

import fr.loysofreezee.randombot.listeners.CommandListener;
import fr.loysofreezee.randombot.utils.log.Log;
import fr.loysofreezee.randombot.utils.log.LogFlags;
import fr.loysofreezee.randombot.utils.log.LogType;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;

    public static void main(String args[]) {
        //JDA connection
        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(args[0])
                    .addListener(new CommandListener())
                    .buildBlocking();
        } catch (LoginException e) { //Authentication Problem
            e.printStackTrace();
            Log.log(LogType.FTL, new LogFlags[]{ LogFlags.LGN }, "Connection failed");
        } catch (InterruptedException | RateLimitedException e) { // buildBlocking waiting interrupted
            e.printStackTrace();
            Log.log(LogType.FTL, new LogFlags[] {}, e.getMessage());
        } finally {
            Log.log(LogType.INF, new LogFlags[] { LogFlags.LGN }, "Successfully connected");
        }
    }
}
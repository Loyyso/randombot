package fr.loysofreezee.randombot;

import fr.loysofreezee.randombot.listeners.CommandListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String args[]) {
        //JDA connection
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(args[0])
                    .addListener(new CommandListener())
                    .buildBlocking();
        } catch(LoginException e){                //Authentication Problem
            e.printStackTrace();
            System.out.println("Connection failed");
        } catch(InterruptedException | RateLimitedException e){          //buildBlocking waiting interrupted
            e.printStackTrace();
        }
    }
}
package fr.loysofreezee.randombot;

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
                    .setToken("MjkyNzU1MzIwNTMzNTQ5MDY3.C68pNQ.bwsLdm4UVyDyLFhTWQXYtDnrX4E")
                    .addListener(new CommandListener())
                    .buildBlocking();
        }
        catch(LoginException e){                //Authentication Problem
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        catch(InterruptedException e){          //buildBlocking waiting interrupted
            e.printStackTrace();
        }
        catch(RateLimitedException e) {          //Too much simultaneous logins
            e.printStackTrace();
        }
    }
}

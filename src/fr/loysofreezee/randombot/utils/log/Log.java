package fr.loysofreezee.randombot.utils.log;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;

import java.util.Calendar;

public class Log {

    public static void log(LogType logType, String[] flags, CharSequence message) {
        String toOutput = "[" + getHour() + "] ";
        toOutput += "[" + logType.getFullName() + "]";
        for (String flag: flags) {
            toOutput += " [" + flag + "]";
        }
        toOutput += ": " + message;

        logType.getOutputStream().println(toOutput);
    }

    public static void logMessage(Message message) {
        String toOutput = "";
        if (message.getChannel().getType() == ChannelType.TEXT) toOutput += "[" + message.getGuild().getName() + "] " + message.getChannel().getName() + "@" + message.getAuthor().getName();
        else toOutput += "[PRIVATE] " + message.getAuthor().getName();

        log(LogType.INFO, new String[] { "Message" }, toOutput + ": " + message.getContent());
    }

    private static String getHour() {
        Calendar time = Calendar.getInstance();

        String hourOfDay = String.valueOf(time.get(Calendar.HOUR_OF_DAY));
        if (hourOfDay.length() == 1) {
            hourOfDay = "0" + hourOfDay;
        }

        String minute = String.valueOf(time.get(Calendar.MINUTE));
        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        String seconds = String.valueOf(time.get(Calendar.SECOND));
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }

        return hourOfDay + ":" + minute + ":" + seconds;
    }
}

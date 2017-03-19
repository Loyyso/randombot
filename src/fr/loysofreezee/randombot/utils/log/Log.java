package fr.loysofreezee.randombot.utils.log;

public class Log {

    public static void log(LogType logType, LogFlags[] flags, CharSequence message) {
        String toOutput = "";
        toOutput += "[" + logType + "]";
        for (LogFlags flag: flags) {
            toOutput += " [" + flag + "]";
        }
        toOutput += ":\t" + message;

        logType.getOutputStream().println(toOutput);
    }
}

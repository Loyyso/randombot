package fr.loysofreezee.randombot.utils.log;

import java.io.PrintStream;

public class Log {

    public static void log(LogType type, String message) {
        PrintStream output = type.getOutputStream();
        output.println("[" + type + "] " + message);
    }

    public static void log(String message, LogType type, LogFlag... flags) {
        String toOutput = "";
        for (LogFlag flag: flags) {
            toOutput += "[" + flag + "] ";
        }
        toOutput += "\t" + message;
        log(type, toOutput);
    }
}


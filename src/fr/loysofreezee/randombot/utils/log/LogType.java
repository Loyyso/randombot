package fr.loysofreezee.randombot.utils.log;

import java.io.PrintStream;

import static java.lang.System.*;

public enum LogType {
    INF(out),
    ERR(err),
    FTL(err);

    private PrintStream outputStream;
    LogType(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    PrintStream getOutputStream() { return outputStream; }
}

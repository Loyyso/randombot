package fr.loysofreezee.randombot.utils.log;

import java.io.PrintStream;

import static java.lang.System.err;
import static java.lang.System.out;

public enum LogType {
    INF(out),
    ERR(err),
    FTL(err);

    private PrintStream outputStream;
    LogType(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public PrintStream getOutputStream() { return outputStream; }
}

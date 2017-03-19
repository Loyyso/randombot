package fr.loysofreezee.randombot.utils.log;

import java.io.PrintStream;

import static java.lang.System.err;
import static java.lang.System.out;

public enum LogType {
    INFO(out, "Info"),
    ERROR(err, "Error"),
    FATAL(err, "Fatal");

    private PrintStream outputStream;
    private String fullName;
    LogType(PrintStream outputStream, String fullName) {
        this.outputStream = outputStream;
        this.fullName = fullName;
    }

    public PrintStream getOutputStream() { return outputStream; }
    public String getFullName() { return fullName; }
}

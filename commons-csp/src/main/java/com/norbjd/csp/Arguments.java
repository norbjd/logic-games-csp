package com.norbjd.csp;

public class Arguments {

    private String fileName;
    private boolean debug;

    Arguments(String fileName, boolean debug) {
        this.fileName = fileName;
        this.debug = debug;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean getDebug() {
        return debug;
    }

}

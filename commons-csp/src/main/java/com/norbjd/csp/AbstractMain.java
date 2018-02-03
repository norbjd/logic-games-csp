package com.norbjd.csp;

public abstract class AbstractMain {

    protected static Arguments parseArguments(String[] args) {
        if(args.length == 0) {
            System.err.println("Usage : Main <file> [<debug>]");
            System.exit(0);
        }

        String fileName = args[0];
        boolean debug = false;

        if(args.length >= 2) {
            debug = Boolean.parseBoolean(args[1]);
        }

        return new Arguments(fileName, debug);
    }

}

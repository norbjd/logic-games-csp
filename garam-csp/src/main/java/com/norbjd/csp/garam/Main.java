package com.norbjd.csp.garam;

import com.norbjd.csp.garam.representation.GaramFileRepresentation;
import com.norbjd.csp.garam.representation.GaramRepresentation;
import com.norbjd.csp.garam.representation.InvalidGaramRepresentationException;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File GaramFile = new File(args[0]);
        GaramRepresentation GaramRepresentation = new GaramFileRepresentation(GaramFile);

        Garam Garam = null;

        try {
            Garam = GaramReader.readFromRepresentation(GaramRepresentation);
            System.out.println("-- Initial Garam --");
            System.out.println(Garam);
        } catch(InvalidGaramRepresentationException ifre) {
            ifre.printStackTrace();
            System.exit(1);
        }

        GaramCSP GaramCSP = new GaramCSP(Garam);
        System.out.println("-- Solution --");
        // System.out.println(GaramCSP.solve());
        GaramCSP.solveAllAndPrintEachSolution();
    }

}

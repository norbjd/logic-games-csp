package com.norbjd.csp.garam;

import com.norbjd.csp.AbstractMain;
import com.norbjd.csp.Arguments;
import com.norbjd.csp.garam.choco.GaramCSP;
import com.norbjd.csp.garam.representation.GaramFileRepresentation;
import com.norbjd.csp.garam.representation.GaramRepresentation;
import com.norbjd.csp.garam.representation.exception.GaramInvalidRepresentationException;

import java.io.File;

public class Main extends AbstractMain {

	public static void main(String[] args) {
		Arguments parsedArgs = parseArguments(args);
		String fileName = parsedArgs.getFileName();
		boolean debug = parsedArgs.getDebug();

		File garamFile = new File(fileName);
		GaramRepresentation garamRepresentation = new GaramFileRepresentation(garamFile);

		Garam garam = null;
		GaramPrinter printer = new GaramPrinter(System.out);

		try {
			garam = GaramReader.readFromRepresentation(garamRepresentation);
			printer.printGaram(garam);
		} catch (GaramInvalidRepresentationException ifre) {
			ifre.printStackTrace();
			System.exit(1);
		}

		GaramCSP garamCSP = new GaramCSP(garam, debug);
		Garam solution = garamCSP.solve();
		printer.printGaram(solution);
	}

}

package com.norbjd.csp.fubuki;

import com.norbjd.csp.AbstractMain;
import com.norbjd.csp.Arguments;
import com.norbjd.csp.fubuki.choco.FubukiCSP;
import com.norbjd.csp.fubuki.representation.FubukiFileRepresentation;
import com.norbjd.csp.fubuki.representation.FubukiRepresentation;
import com.norbjd.csp.fubuki.representation.exception.FubukiInvalidRepresentationException;

import java.io.File;

public class Main extends AbstractMain {

	public static void main(String[] args) {
		Arguments parsedArgs = parseArguments(args);
		String fileName = parsedArgs.getFileName();
		boolean debug = parsedArgs.getDebug();

		File fubukiFile = new File(fileName);
		FubukiRepresentation fubukiRepresentation = new FubukiFileRepresentation(fubukiFile);

		Fubuki fubuki = null;
		FubukiPrinter printer = new FubukiPrinter(System.out);

		try {
			fubuki = FubukiReader.readFromRepresentation(fubukiRepresentation);
			printer.printFubuki(fubuki);
		} catch (FubukiInvalidRepresentationException ifre) {
			ifre.printStackTrace();
			System.exit(1);
		}

		FubukiCSP fubukiCSP = new FubukiCSP(fubuki, debug);
		Fubuki solution = fubukiCSP.solve();
		printer.printFubuki(solution);
	}

}

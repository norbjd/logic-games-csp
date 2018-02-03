package com.norbjd.csp.fubuki;

import com.norbjd.csp.fubuki.representation.FubukiFileRepresentation;
import com.norbjd.csp.fubuki.representation.FubukiRepresentation;
import com.norbjd.csp.fubuki.representation.InvalidFubukiRepresentationException;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		File fubukiFile = new File(args[0]);
		FubukiRepresentation fubukiRepresentation = new FubukiFileRepresentation(fubukiFile);

		Fubuki fubuki = null;

		try {
			fubuki = FubukiReader.readFromRepresentation(fubukiRepresentation);
			System.out.println("-- Initial fubuki --");
			System.out.println(fubuki);
		} catch (InvalidFubukiRepresentationException ifre) {
			ifre.printStackTrace();
			System.exit(1);
		}

		FubukiCSP fubukiCSP = new FubukiCSP(fubuki);
		System.out.println("-- Solution --");
		System.out.println(fubukiCSP.solve());
	}

}

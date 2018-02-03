package com.norbjd.csp.fubuki.representation;

import com.norbjd.csp.fubuki.Fubuki;
import com.norbjd.csp.fubuki.representation.exception.FubukiInvalidRepresentationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FubukiFileRepresentation implements FubukiRepresentation {

	private File file;

	public FubukiFileRepresentation(File file) {
		setFile(file);
	}

	private String getFileContent() throws IOException {
		return new String(Files.readAllBytes(file.toPath()));
	}

	private void setFile(File file) {
		this.file = file;
	}

	public Fubuki get() throws FubukiInvalidRepresentationException {
		try {
			FubukiStringRepresentation fubukiStringRepresentation = new FubukiStringRepresentation(getFileContent());
			return fubukiStringRepresentation.get();
		} catch (IOException ioe) {
			throw new FubukiInvalidRepresentationException(ioe.getMessage());
		}
	}

}

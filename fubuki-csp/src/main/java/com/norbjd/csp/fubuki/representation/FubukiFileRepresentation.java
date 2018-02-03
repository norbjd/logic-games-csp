package com.norbjd.csp.fubuki.representation;

import com.norbjd.csp.fubuki.Fubuki;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FubukiFileRepresentation implements FubukiRepresentation {

	private File file;

	public FubukiFileRepresentation(File file) {
		setFile(file);
	}

	public File getFile() {
		return file;
	}

	private String getFileContent() throws IOException {
		return new String(Files.readAllBytes(file.toPath()));
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Fubuki get() throws InvalidFubukiRepresentationException {
		try {
			FubukiStringRepresentation fubukiStringRepresentation = new FubukiStringRepresentation(getFileContent());
			return fubukiStringRepresentation.get();
		} catch (IOException ioe) {
			throw new InvalidFubukiRepresentationException(ioe.getMessage());
		}
	}

}

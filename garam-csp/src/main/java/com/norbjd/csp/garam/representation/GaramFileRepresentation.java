package com.norbjd.csp.garam.representation;

import com.norbjd.csp.garam.Garam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GaramFileRepresentation implements GaramRepresentation {

    private File file;

    public GaramFileRepresentation(File file) {
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

    public Garam get() throws InvalidGaramRepresentationException {
        try {
            GaramStringRepresentation GaramStringRepresentation = new GaramStringRepresentation(getFileContent());
            return GaramStringRepresentation.get();
        } catch(IOException ioe) {
            throw new InvalidGaramRepresentationException(ioe.getMessage());
        }
    }

}

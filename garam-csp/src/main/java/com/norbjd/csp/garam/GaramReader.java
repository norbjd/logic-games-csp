package com.norbjd.csp.garam;

import com.norbjd.csp.garam.representation.GaramRepresentation;
import com.norbjd.csp.garam.representation.InvalidGaramRepresentationException;

public class GaramReader {

    public static Garam readFromRepresentation(GaramRepresentation GaramRepresentation) throws InvalidGaramRepresentationException {
        return GaramRepresentation.get();
    }

}

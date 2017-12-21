package com.norbjd.csp.fubuki;

import com.norbjd.csp.fubuki.representation.FubukiRepresentation;
import com.norbjd.csp.fubuki.representation.InvalidFubukiRepresentationException;

public class FubukiReader {

    public static Fubuki readFromRepresentation(FubukiRepresentation fubukiRepresentation) throws InvalidFubukiRepresentationException {
        return fubukiRepresentation.get();
    }

}

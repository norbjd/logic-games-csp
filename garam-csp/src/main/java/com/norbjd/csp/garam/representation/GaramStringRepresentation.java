package com.norbjd.csp.garam.representation;

import com.norbjd.csp.garam.Garam;

import java.util.Arrays;

public class GaramStringRepresentation implements GaramRepresentation {

    private String representation;

    public GaramStringRepresentation(String representation) {
        setRepresentation(representation);
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Garam get() throws InvalidGaramRepresentationException {
        try {
            String[] lines = representation.split("\n");

            char[] operands = lines[0].toCharArray();

            String[] valuesLine = lines[1].split(",");
            int[] cellsValues = Arrays.stream(valuesLine).mapToInt(i -> {
                if(i.equals("X")) return -1;
                else return Integer.parseInt(i);
            }).toArray();

            return new Garam(operands, cellsValues);
        } catch(Exception e) {
            throw new InvalidGaramRepresentationException(e.getMessage());
        }
    }

}

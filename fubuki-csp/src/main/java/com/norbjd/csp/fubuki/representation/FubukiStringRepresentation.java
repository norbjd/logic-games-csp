package com.norbjd.csp.fubuki.representation;

import com.norbjd.csp.fubuki.Fubuki;

import java.util.Arrays;

public class FubukiStringRepresentation implements FubukiRepresentation {

    private String representation;

    public FubukiStringRepresentation(String representation) {
        setRepresentation(representation);
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Fubuki get() throws InvalidFubukiRepresentationException {
        try {
            String[] lines = representation.split("\n");

            String[] heightAndWidth = lines[0].split(",");
            int height = Integer.parseInt(heightAndWidth[0]);
            int width = Integer.parseInt(heightAndWidth[1]);

            String[] rowSumsLine = lines[1].split(",");
            int[] rowSums = Arrays.stream(rowSumsLine).mapToInt(Integer::parseInt).toArray();

            String[] colSumsLine = lines[2].split(",");
            int[] colSums = Arrays.stream(colSumsLine).mapToInt(Integer::parseInt).toArray();

            String[] valuesLine = lines[3].split(",");
            int[] values = Arrays.stream(valuesLine).mapToInt(Integer::parseInt).toArray();

            return new Fubuki(height, width, rowSums, colSums, values);
        } catch (Exception e) {
            throw new InvalidFubukiRepresentationException(e.getMessage());
        }
    }

}

package com.norbjd.csp;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;

import java.util.List;

public abstract class CSPConstraints {
    protected Model model;

    public CSPConstraints(Model model) {
        this.model = model;
    }

    public abstract List<Constraint> getConstraints();
}

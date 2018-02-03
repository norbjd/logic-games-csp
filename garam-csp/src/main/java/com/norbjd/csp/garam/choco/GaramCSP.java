package com.norbjd.csp.garam.choco;

import com.norbjd.csp.garam.Garam;
import com.norbjd.csp.settings.DebugSettings;
import com.norbjd.csp.settings.DefaultSettings;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.chocosolver.memory.trailing.EnvironmentTrailing;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Settings;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.explanations.ExplanationEngine;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GaramCSP {

	private Garam garam;
	private Model model;

	private IntVar[] cells;
	private IntVar[] twoDigitsNumbers;

	private boolean debug;

	public GaramCSP(Garam garam, boolean debug) {
		setGaram(garam);
		this.debug = debug;
		initModel();
	}

	public void setGaram(Garam garam) {
		this.garam = garam;
	}

	private List<Constraint> twoDigitsNumbersConstraints() {
		return Arrays.asList(model.arithm(twoDigitsNumbers[0], "=", cells[11].mul(10).add(cells[15]).intVar()),
				model.arithm(twoDigitsNumbers[1], "=", cells[12].mul(10).add(cells[17]).intVar()),
				model.arithm(twoDigitsNumbers[2], "=", cells[13].mul(10).add(cells[18]).intVar()),
				model.arithm(twoDigitsNumbers[3], "=", cells[14].mul(10).add(cells[20]).intVar()),
				model.arithm(twoDigitsNumbers[4], "=", cells[34].mul(10).add(cells[38]).intVar()),
				model.arithm(twoDigitsNumbers[5], "=", cells[35].mul(10).add(cells[40]).intVar()),
				model.arithm(twoDigitsNumbers[6], "=", cells[36].mul(10).add(cells[41]).intVar()),
				model.arithm(twoDigitsNumbers[7], "=", cells[37].mul(10).add(cells[43]).intVar()));
	}

	private List<Constraint> horizontalEquationsConstraints() {
		return Arrays.asList(equation(cells[0], garam.getOperator(0), cells[1], cells[2]),
				equation(cells[3], garam.getOperator(1), cells[4], cells[5]),
				equation(cells[7], garam.getOperator(6), cells[8], cells[9]),
				equation(cells[15], garam.getOperator(7), cells[16], cells[17]),
				equation(cells[18], garam.getOperator(8), cells[19], cells[20]),
				equation(cells[23], garam.getOperator(11), cells[24], cells[25]),
				equation(cells[26], garam.getOperator(12), cells[27], cells[28]),
				equation(cells[30], garam.getOperator(17), cells[31], cells[32]),
				equation(cells[38], garam.getOperator(18), cells[39], cells[40]),
				equation(cells[41], garam.getOperator(19), cells[42], cells[43]));
	}

	private List<Constraint> verticalEquationsConstraints() {
		return Arrays.asList(equation(cells[0], garam.getOperator(2), cells[6], twoDigitsNumbers[0]),
				equation(cells[2], garam.getOperator(3), cells[7], twoDigitsNumbers[1]),
				equation(cells[3], garam.getOperator(4), cells[9], twoDigitsNumbers[2]),
				equation(cells[5], garam.getOperator(5), cells[10], twoDigitsNumbers[3]),
				equation(cells[16], garam.getOperator(9), cells[21], cells[24]),
				equation(cells[19], garam.getOperator(10), cells[22], cells[27]),
				equation(cells[23], garam.getOperator(13), cells[29], twoDigitsNumbers[4]),
				equation(cells[25], garam.getOperator(14), cells[30], twoDigitsNumbers[5]),
				equation(cells[26], garam.getOperator(15), cells[32], twoDigitsNumbers[6]),
				equation(cells[28], garam.getOperator(16), cells[33], twoDigitsNumbers[7]));
	}

	private List<Constraint> presetValuesConstraints() {
		Stream<Pair<IntVar, Integer>> zippedCellsWithValues = IntStream.range(0, 43)
				.mapToObj(i -> new ImmutablePair<>(cells[i], garam.getCellsValues()[i]));

		Stream<Pair<IntVar, Integer>> zippedCellsWithPresetValues = zippedCellsWithValues
				.filter((Pair<IntVar, Integer> pair) -> pair.getRight() != -1);

		return zippedCellsWithPresetValues
				.map((Pair<IntVar, Integer> pair) -> model.arithm(pair.getLeft(), "=", pair.getRight()))
				.collect(Collectors.toList());
	}

	private Constraint equation(IntVar firstOperand, char operator, IntVar secondOperand, IntVar result) {
		return model.arithm(firstOperand, String.valueOf(operator), secondOperand, "=", result);
	}

	private Model initModel() {
		Settings settings;

		if (debug) {
			settings = new DebugSettings();
		} else {
			settings = new DefaultSettings();
		}

		model = new Model(new EnvironmentTrailing(), "Garam", settings);

		cells = model.intVarArray("cell", garam.getNumberOfCells(), 0, 9);
		twoDigitsNumbers = model.intVarArray("two-digits number", 8, 10, 99);

		presetValuesConstraints().forEach(Constraint::post);
		twoDigitsNumbersConstraints().forEach(Constraint::post);
		horizontalEquationsConstraints().forEach(Constraint::post);
		verticalEquationsConstraints().forEach(Constraint::post);

		return model;
	}

	public Garam solve() {
		Solver solver = model.getSolver();

		solver.showSolutions();

		if (debug) {
			solver.showDecisions();
			solver.showContradiction();

			solver.printStatistics();
			solver.setExplainer(new ExplanationEngine(model, true, true));
		}

		solver.solve();

		int[] cellsValues = Stream.of(cells).mapToInt(IntVar::getValue).toArray();

		Garam garamCopy = new Garam(garam);
		garamCopy.setCellsValues(cellsValues);

		return garamCopy;
	}

}

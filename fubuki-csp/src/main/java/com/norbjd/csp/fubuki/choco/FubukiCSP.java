package com.norbjd.csp.fubuki.choco;

import com.norbjd.csp.fubuki.Fubuki;
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

public class FubukiCSP {

	private Fubuki fubuki;
	private Model model;
	private IntVar[] cells;

	private boolean debug;

	public FubukiCSP(Fubuki fubuki, boolean debug) {
		setFubuki(fubuki);
		this.debug = debug;
		initModel();
	}

	private void setFubuki(Fubuki fubuki) {
		this.fubuki = fubuki;
	}

	private IntVar[] extractRowCells(IntVar[] cells, int rowIndex, int rowSize) {
		int minIndex = rowIndex * rowSize;
		int maxIndex = minIndex + rowSize;

		return Arrays.asList(cells).subList(minIndex, maxIndex).toArray(new IntVar[rowSize]);
	}

	private IntVar[] extractColCells(IntVar[] cells, int colIndex, int rowSize) {
		return IntStream.range(0, fubuki.getNumberOfCells()).mapToObj(i -> new ImmutablePair<>(i, cells[i]))
				.filter((Pair<Integer, IntVar> pair) -> pair.getLeft() % rowSize == colIndex).map(Pair::getRight)
				.toArray(IntVar[]::new);
	}

	private Constraint allDifferentConstraint() {
		return model.allDifferent(cells);
	}

	private List<Constraint> presetValuesConstraints() {
		Stream<Pair<IntVar, Integer>> zippedCellsWithValues = IntStream.range(0, fubuki.getNumberOfCells())
				.mapToObj(i -> new ImmutablePair<>(cells[i], fubuki.getCellsValues()[i]));

		Stream<Pair<IntVar, Integer>> zippedCellsWithPresetValues = zippedCellsWithValues
				.filter((Pair<IntVar, Integer> pair) -> pair.getRight() != 0);

		return zippedCellsWithPresetValues
				.map((Pair<IntVar, Integer> pair) -> model.arithm(pair.getLeft(), "=", pair.getRight()))
				.collect(Collectors.toList());
	}

	private List<Constraint> rowSumsConstraints() {
		return IntStream.range(0, fubuki.getWidth()).mapToObj(rowIndex -> {
			IntVar[] rowCells = extractRowCells(cells, rowIndex, fubuki.getWidth());
			return model.sum(rowCells, "=", fubuki.getRowSums()[rowIndex]);
		}).collect(Collectors.toList());
	}

	private List<Constraint> colSumsConstraints() {
		return IntStream.range(0, fubuki.getHeight()).mapToObj(colIndex -> {
			IntVar[] colCells = extractColCells(cells, colIndex, fubuki.getHeight());
			return model.sum(colCells, "=", fubuki.getColSums()[colIndex]);
		}).collect(Collectors.toList());
	}

	private Model initModel() {
		Settings settings = new Settings() {
			@Override
			public boolean debugPropagation() {
				return true;
			}

			@Override
			public boolean warnUser() {
				return true;
			}
		};
		model = new Model(new EnvironmentTrailing(), "Fubuki", settings);

		cells = model.intVarArray("cell", fubuki.getNumberOfCells(), 1, fubuki.getNumberOfCells());

		allDifferentConstraint().post();
		presetValuesConstraints().forEach(Constraint::post);
		rowSumsConstraints().forEach(Constraint::post);
		colSumsConstraints().forEach(Constraint::post);

		return model;
	}

	public Fubuki solve() {
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

		Fubuki fubukiCopy = new Fubuki(fubuki);
		fubukiCopy.setCellsValues(cellsValues);

		return fubukiCopy;
	}

}

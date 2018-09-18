package com.jusdt.datamining.others.viterbi;

/**
 * 维特比算法
 */
public class ViterbiExample {

	public static void main(String[] args) {
		// 状态转移概率矩阵路径
		String stmFilePath;
		// 混淆矩阵路径
		String cfFilePath;
		// 观察到的状态
		String[] observeStates;
		// 初始状态
		double[] initStatePro;
		ViterbiCore tool;

		stmFilePath = "data/viterbi/stmatrix.txt";
		cfFilePath = "data/viterbi/humidity-matrix.txt";

		initStatePro = new double[] { 0.63, 0.17, 0.20 };
		observeStates = new String[] { "Dry", "Damp", "Soggy" };

		tool = new ViterbiCore(stmFilePath, cfFilePath, initStatePro, observeStates);
		tool.calHMMObserve();
	}

}

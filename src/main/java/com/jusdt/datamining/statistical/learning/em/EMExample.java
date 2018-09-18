package com.jusdt.datamining.statistical.learning.em;

/**
 * EM期望最大化算法场景调用类
 */
public class EMExample {

	public static void main(String[] args) {
		String filePath = "data/em/input.txt";

		EMCore tool = new EMCore(filePath);
		tool.readDataFile();
		tool.exceptMaxStep();
	}

}

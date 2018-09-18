package com.jusdt.datamining.sequential.patterns.gsp;

/**
 * GSP序列模式分析算法
 */
public class GSPExample {

	public static void main(String[] args) {
		String filePath = "data/gsp/testInput.txt";
		//最小支持度阈值
		int minSupportCount = 2;
		//时间最小间隔
		int min_gap = 1;
		//施加最大间隔
		int max_gap = 5;

		GSPCore tool = new GSPCore(filePath, minSupportCount, min_gap, max_gap);
		tool.gspCalculate();
	}

}

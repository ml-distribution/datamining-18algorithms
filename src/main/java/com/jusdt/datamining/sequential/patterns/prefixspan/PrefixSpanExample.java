package com.jusdt.datamining.sequential.patterns.prefixspan;

/**
 * PrefixSpan序列模式挖掘算法
 */
public class PrefixSpanExample {

	public static void main(String[] agrs) {
		String filePath = "data/prefixspan/input.txt";
		//最小支持度阈值率
		double minSupportRate = 0.4;

		PrefixSpanCore tool = new PrefixSpanCore(filePath, minSupportRate);
		tool.prefixSpanCalculate();
	}

}

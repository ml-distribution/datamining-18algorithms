package com.jusdt.datamining.roughsets;

/**
 * 粗糙集约简算法
 */
public class RoughSetsExample {

	public static void main(String[] args) {
		String filePath = "data/roughsets/input.txt";

		RoughSetsCore tool = new RoughSetsCore(filePath);
		tool.findingReduct();
	}

}

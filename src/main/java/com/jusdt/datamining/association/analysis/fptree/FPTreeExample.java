package com.jusdt.datamining.association.analysis.fptree;

/**
 * FPTree频繁模式树算法
 */
public class FPTreeExample {

	public static void main(String[] args) {
		String filePath = "data/fptree/testInput.txt";
		//最小支持度阈值
		int minSupportCount = 2;

		FPTreeCore tool = new FPTreeCore(filePath, minSupportCount);
		tool.startBuildingTree();
	}

}

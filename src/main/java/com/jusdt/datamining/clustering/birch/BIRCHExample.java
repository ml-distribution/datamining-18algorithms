package com.jusdt.datamining.clustering.birch;

/**
 * BIRCH聚类算法调用类
 */
public class BIRCHExample {

	public static void main(String[] args) {
		String filePath = "data/birch/testInput.txt";
		//内部节点平衡因子B
		int B = 2;
		//叶子节点平衡因子L
		int L = 2;
		//簇直径阈值T
		double T = 0.6;

		BIRCHCore tool = new BIRCHCore(filePath, B, L, T);
		tool.startBuilding();
	}

}

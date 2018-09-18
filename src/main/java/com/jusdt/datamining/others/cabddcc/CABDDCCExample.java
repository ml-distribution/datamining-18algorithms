package com.jusdt.datamining.others.cabddcc;

/**
 * 基于连通图的分裂聚类算法
 */
public class CABDDCCExample {

	public static void main(String[] agrs) {
		String filePath = "data/cabddcc/graphData.txt";
		//连通距离阈值
		int length = 3;

		CABDDCCCore tool = new CABDDCCCore(filePath, length);
		tool.splitCluster();
	}

}

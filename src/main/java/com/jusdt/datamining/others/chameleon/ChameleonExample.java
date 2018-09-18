package com.jusdt.datamining.others.chameleon;

/**
 * Chameleon(变色龙)两阶段聚类算法
 */
public class ChameleonExample {

	public static void main(String[] args) {
		String filePath = "data/chameleon/graphData.txt";
		//k-近邻的k设置
		int k = 1;
		//度量函数阈值
		double minMetric = 0.1;

		ChameleonCore tool = new ChameleonCore(filePath, k, minMetric);
		tool.buildCluster();
	}

}

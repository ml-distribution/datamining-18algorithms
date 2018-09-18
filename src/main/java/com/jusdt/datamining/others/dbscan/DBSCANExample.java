package com.jusdt.datamining.others.dbscan;

/**
 * Dbscan基于密度的聚类算法测试类
 */
public class DBSCANExample {

	public static void main(String[] args) {
		String filePath = "data/dbscan/input.txt";
		//簇扫描半径
		double eps = 3;
		//最小包含点数阈值
		int minPts = 3;

		DBSCANCore tool = new DBSCANCore(filePath, eps, minPts);
		tool.dbScanCluster();
	}

}

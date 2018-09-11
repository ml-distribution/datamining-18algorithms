package com.jusdt.datamining.others.cabddcc;

/**
 * 基于连通图的分裂聚类算法
 */
public class Client {

	public static void main(String[] agrs) {
		String filePath = "C:\\Users\\lyq\\Desktop\\icon\\graphData.txt";
		//连通距离阈值
		int length = 3;

		CABDDCCTool tool = new CABDDCCTool(filePath, length);
		tool.splitCluster();
	}

}
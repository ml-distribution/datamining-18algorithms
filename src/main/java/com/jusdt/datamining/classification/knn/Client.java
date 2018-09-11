package com.jusdt.datamining.classification.knn;

/**
 * k最近邻算法场景类型
 * @author lyq
 *
 */
public class Client {

	public static void main(String[] args) {
		String trainDataPath = "C:\\Users\\lyq\\Desktop\\icon\\trainInput.txt";
		String testDataPath = "C:\\Users\\lyq\\Desktop\\icon\\testinput.txt";

		KNNTool tool = new KNNTool(trainDataPath, testDataPath);
		tool.knnCompute(3);
	}

}

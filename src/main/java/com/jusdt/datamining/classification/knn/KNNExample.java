package com.jusdt.datamining.classification.knn;

/**
 * k最近邻算法场景类型
 */
public class KNNExample {

	public static void main(String[] args) {
		String trainDataPath = "data/knn/trainInput.txt";
		String testDataPath = "data/knn/testinput.txt";

		KNNCore tool = new KNNCore(trainDataPath, testDataPath);
		tool.knnCompute(3);
	}

}

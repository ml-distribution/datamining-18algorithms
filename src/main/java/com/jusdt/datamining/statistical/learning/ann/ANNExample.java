package com.jusdt.datamining.statistical.learning.ann;

public class ANNExample {

	public static void main(String[] args) {
		// 训练集数据文件路径
		String trainDataPath = "data/ann/trainInput.txt";
		// 测试数据文件路径
		String testDataPath = "data/ann/testInput.txt";

		ANNCore tool = new ANNCore(trainDataPath);
		// 对测试数据进行ANN分类
		tool.svmPredictData(testDataPath);
	}

}

package com.jusdt.datamining.statistical.learning.svm;

/**
 * SVM支持向量机场景调用类
 */
public class Client {

	public static void main(String[] args) {
		//训练集数据文件路径
		String trainDataPath = "data/statistical-learning/svm/trainInput.txt";
		//测试数据文件路径
		String testDataPath = "data/statistical-learning/svm/testInput.txt";

		SVMTool tool = new SVMTool(trainDataPath);
		//对测试数据进行svm支持向量机分类
		tool.svmPredictData(testDataPath);
	}

}

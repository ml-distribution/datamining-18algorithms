package com.jusdt.datamining.bagging.boosting.adaboost;

/**
 * AdaBoost提升算法调用类
 */
public class Client {

	public static void main(String[] agrs) {
		String filePath = "C:\\Users\\lyq\\Desktop\\icon\\input.txt";
		//误差率阈值
		double errorValue = 0.2;

		AdaBoostTool tool = new AdaBoostTool(filePath, errorValue);
		tool.adaBoostClassify();
	}

}

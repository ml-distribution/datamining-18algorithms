package com.jusdt.datamining.bagging.boosting.adaboost;

/**
 * AdaBoost提升算法调用类
 */
public class AdaBoostExample {

	public static void main(String[] agrs) {
		String filePath = "data/adaboost/input.txt";
		//误差率阈值
		double errorValue = 0.2;

		AdaBoostCore tool = new AdaBoostCore(filePath, errorValue);
		tool.adaBoostClassify();
	}

}

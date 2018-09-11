package com.jusdt.datamining.others.ga;

/**
 * Genetic遗传算法测试类
 */
public class Client {

	public static void main(String[] args) {
		//变量最小值和最大值
		int minNum = 1;
		int maxNum = 7;
		//初始群体规模
		int initSetsNum = 4;

		GATool tool = new GATool(minNum, maxNum, initSetsNum);
		tool.geneticCal();
	}

}

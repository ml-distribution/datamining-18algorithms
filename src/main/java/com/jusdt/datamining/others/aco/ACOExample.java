package com.jusdt.datamining.others.aco;

/**
 * 蚁群算法测试类
 */
public class ACOExample {

	public static void main(String[] args) {
		//测试数据
		String filePath = "data/aco/input.txt";
		//蚂蚁数量
		int antNum;
		//蚁群算法迭代次数
		int loopCount;
		//控制参数
		double alpha;
		double beita;
		double p;
		double Q;

		antNum = 3;
		alpha = 0.5;
		beita = 1;
		p = 0.5;
		Q = 5;
		loopCount = 5;

		ACOCore tool = new ACOCore(filePath, antNum, alpha, beita, p, Q);
		tool.antStartSearching(loopCount);
	}

}

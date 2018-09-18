package com.jusdt.datamining.others.bayesnetwork;

/**
 * 贝叶斯网络场景测试类
 */
public class BayesNetWorkExample {

	public static void main(String[] args) {
		String dataFilePath = "data/bayesnetwork/input.txt";
		String attachFilePath = "data/bayesnetwork/attach.txt";
		// 查询串语句
		String queryStr;
		// 结果概率
		double result;

		// 查询语句的描述的事件是地震发生了，导致响铃响了，导致接到Mary的电话
		queryStr = "E=y,A=y,M=y";
		BayesNetWorkCore tool = new BayesNetWorkCore(dataFilePath, attachFilePath);
		result = tool.calProByNetWork(queryStr);

		if (result == -1) {
			System.out.println("所描述的事件不满足贝叶斯网络的结构，无法求其概率");
		} else {
			System.out.println(String.format("事件%s发生的概率为%s", queryStr, result));
		}
	}

}

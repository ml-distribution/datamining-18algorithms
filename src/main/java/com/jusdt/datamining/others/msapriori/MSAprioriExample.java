package com.jusdt.datamining.others.msapriori;

/**
 * 基于多支持度的Apriori算法测试类
 */
public class MSAprioriExample {

	public static void main(String[] args) {
		//是否是事务型数据
		boolean isTransaction;
		//测试数据文件地址
		String filePath = "data/msapriori/testInput.txt";
		//关系表型数据文件地址
		String tableFilePath = "data/msapriori/testInput2.txt";
		//最小支持度阈值
		double minSup;
		// 最小置信度率
		double minConf;
		//最大支持度差别阈值
		double delta;
		//多项目的最小支持度数,括号中的下标代表的是商品的ID
		double[] mis;
		//msApriori算法工具类
		MSAprioriCore tool;

		//为了测试的方便，取一个偏低的置信度值0.3
		minConf = 0.3;
		minSup = 0.1;
		delta = 0.5;
		//每项的支持度率都默认为0.1，第一项不使用
		mis = new double[] { -1, 0.1, 0.1, 0.1, 0.1, 0.1 };
		isTransaction = true;

		isTransaction = true;
		tool = new MSAprioriCore(filePath, minConf, delta, mis, isTransaction);
		tool.calFItems();
		System.out.println();

		isTransaction = false;
		//重新初始化数据
		tool = new MSAprioriCore(tableFilePath, minConf, minSup, isTransaction);
		tool.calFItems();
	}

}

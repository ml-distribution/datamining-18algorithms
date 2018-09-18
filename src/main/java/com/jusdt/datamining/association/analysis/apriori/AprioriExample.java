package com.jusdt.datamining.association.analysis.apriori;

/**
 * apriori关联规则挖掘算法调用类
 */
public class AprioriExample {

	public static void main(String[] args) {
		String filePath = "data/apriori/testInput.txt";

		AprioriCore tool = new AprioriCore(filePath, 2);
		tool.printAttachRule(0.7);
	}

}

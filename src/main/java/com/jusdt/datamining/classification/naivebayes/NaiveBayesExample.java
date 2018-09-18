package com.jusdt.datamining.classification.naivebayes;

/**
 * 朴素贝叶斯算法场景调用类
 */
public class NaiveBayesExample {

	public static void main(String[] args) {
		//训练集数据
		String filePath = "data/naivebayes/input.txt";
		String testData = "Youth Medium Yes Fair";
		NaiveBayesCore tool = new NaiveBayesCore(filePath);
		System.out.println(testData + " 数据的分类为:" + tool.naiveBayesClassificate(testData));
	}

}

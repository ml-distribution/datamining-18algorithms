package com.jusdt.datamining.link.hits;

/**
 * HITS链接分析算法
 */
public class Client {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\lyq\\Desktop\\icon\\input.txt";

		HITSTool tool = new HITSTool(filePath);
		tool.printResultPage();
	}

}

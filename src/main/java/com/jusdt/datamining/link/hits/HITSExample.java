package com.jusdt.datamining.link.hits;

/**
 * HITS链接分析算法
 */
public class HITSExample {

	public static void main(String[] args) {
		String filePath = "data/hits/input.txt";

		HITSCore tool = new HITSCore(filePath);
		tool.printResultPage();
	}

}

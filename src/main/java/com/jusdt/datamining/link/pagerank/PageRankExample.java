package com.jusdt.datamining.link.pagerank;

/**
 * PageRank计算网页重要性/排名算法
 */
public class PageRankExample {

	public static void main(String[] args) {
		String filePath = "data/pagerank/input.txt";

		PageRankCore tool = new PageRankCore(filePath);
		tool.printPageRankValue();
	}

}

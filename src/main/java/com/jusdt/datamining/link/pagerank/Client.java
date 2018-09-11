package com.jusdt.datamining.link.pagerank;

/**
 * PageRank计算网页重要性/排名算法
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\lyq\\Desktop\\icon\\input.txt";

		PageRankTool tool = new PageRankTool(filePath);
		tool.printPageRankValue();
	}
}

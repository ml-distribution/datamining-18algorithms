package com.jusdt.datamining.roughsets;

/**
 * 粗糙集约简算法
 */
public class Client {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\lyq\\Desktop\\icon\\input.txt";

		RoughSetsTool tool = new RoughSetsTool(filePath);
		tool.findingReduct();
	}

}

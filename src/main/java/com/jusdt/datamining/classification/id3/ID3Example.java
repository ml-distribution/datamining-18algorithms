package com.jusdt.datamining.classification.id3;

/**
 * ID3决策树分类算法测试场景类
 */
public class ID3Example {

	public static void main(String[] args) {
		String filePath = "data/id3/input.txt";

		ID3Core tool = new ID3Core(filePath);
		tool.startBuildingTree(true);
	}

}

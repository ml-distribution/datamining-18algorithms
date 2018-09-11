package com.jusdt.datamining.classification.cart;

public class Client {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\lyq\\Desktop\\icon\\input.txt";

		CARTTool tool = new CARTTool(filePath);

		tool.startBuildingTree();
	}

}

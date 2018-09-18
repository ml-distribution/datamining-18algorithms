package com.jusdt.datamining.classification.cart;

public class CARTExample {

	public static void main(String[] args) {
		String filePath = "data/cart/input.txt";

		CARTCore tool = new CARTCore(filePath);

		tool.startBuildingTree();
	}

}

package com.jusdt.datamining.others.ga.maze;

/**
 * 遗传算法在走迷宫游戏的应用
 */
public class GAMazeExample {

	public static void main(String[] args) {
		//迷宫地图文件数据地址
		String filePath = "data/maze/mapData.txt";
		//初始个体数量
		int initSetsNum = 10;

		GAMazeCore tool = new GAMazeCore(filePath, initSetsNum);
		tool.goOutMaze();
	}

}

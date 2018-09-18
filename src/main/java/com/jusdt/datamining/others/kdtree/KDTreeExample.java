package com.jusdt.datamining.others.kdtree;

import java.text.MessageFormat;

/**
 * KD树算法测试类
 */
public class KDTreeExample {

	public static void main(String[] args) {
		String filePath = "data/kdtree/input.txt";
		Point queryNode;
		Point searchedNode;
		KDTreeCore tool = new KDTreeCore(filePath);

		// 进行KD树的构建
		tool.createKDTree();

		// 通过KD树进行数据点的最近点查询
		queryNode = new Point(2.1, 3.1);
		searchedNode = tool.searchNearestData(queryNode);
		System.out.println(MessageFormat.format("距离查询点({0}, {1})最近的坐标点为({2}, {3})", queryNode.x, queryNode.y,
				searchedNode.x, searchedNode.y));

		//重新构造KD树,去除之前的访问记录
		tool.createKDTree();
		queryNode = new Point(2, 4.5);
		searchedNode = tool.searchNearestData(queryNode);
		System.out.println(MessageFormat.format("距离查询点({0}, {1})最近的坐标点为({2}, {3})", queryNode.x, queryNode.y,
				searchedNode.x, searchedNode.y));
	}

}

package com.jusdt.datamining.clustering.kmeans;

/**
 * K-means（K均值）算法调用类
 */
public class KMeansExample {

	public static void main(String[] args) {
		String filePath = "data/kmeans/input.txt";
		// 聚类中心数量设定
		int classNum = 3;

		KMeansCore tool = new KMeansCore(filePath, classNum);
		tool.kMeansClustering();
	}

}

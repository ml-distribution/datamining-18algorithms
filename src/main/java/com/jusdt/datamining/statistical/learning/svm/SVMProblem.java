package com.jusdt.datamining.statistical.learning.svm;

import java.io.Serializable;

/**
 * 包含了训练集数据的基本信息
 */
public class SVMProblem implements Serializable {

	private static final long serialVersionUID = 1L;

	//定义了向量的总个数
	public int l;
	//分类类型值数组
	public double[] y;
	//训练集向量表
	public SVMNode[][] x;

}

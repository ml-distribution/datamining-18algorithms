package com.jusdt.datamining.statistical.learning.svm;

import java.io.Serializable;

/**
 *
 * svm向量节点
 * @author lyq
 *
 */
public class SVMNode implements Serializable {

	private static final long serialVersionUID = 1L;

	//节点索引
	public int index;
	//节点的值
	public double value;

}

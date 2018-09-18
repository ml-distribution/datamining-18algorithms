package com.jusdt.datamining.dimensionality.reduction.pca;

public class PCAExample {

	// expected results
	private static int SIZE = 6;
	private static double[] data = new double[] { 1, 2, 3, 4, 5, 6 };

	private static double[][] pcomps = new double[][] { { -2.4530, -1.4869, -0.2693, -0.1327, -0.1141, -0.0000 },
			{ -1.9344, 0.1112, 0.1731, 0.3003, 0.2126, 0.0000 }, { -0.7959, 1.3757, 0.4091, -0.1677, -0.1651, -0.0000 },
			{ 0.7959, 1.3757, -0.4091, -0.1677, 0.1651, -0.0000 }, { 1.9344, 0.1112, -0.1731, 0.3003, -0.2126, 0.0000 },
			{ 2.4530, -1.4869, 0.2693, -0.1327, 0.1141, 0.0000 } };

	private static double[] plambda = new double[] { 4.1572, 1.6463, 0.1080, 0.0544, 0.0342, 0.0000 };

	private static double[][] pfacs = { { 0.4851, -0.0000, 0.4138, 0.0000, 0.3056, 0.7071 },
			{ 0.4562, -0.2454, -0.1519, -0.6631, -0.5185, -0.0000 },
			{ 0.2378, -0.6631, -0.5529, 0.2454, 0.3712, -0.0000 },
			{ -0.2378, -0.6631, 0.5529, 0.2454, -0.3712, 0.0000 },
			{ -0.4562, -0.2454, 0.1519, -0.6631, 0.5185, -0.0000 },
			{ -0.4851, -0.0000, -0.4138, -0.0000, -0.3056, 0.7071 } };

	public static void main(String[] args) {
		PCACoreHandler instance = new PCACoreHandler();
		PCACore result = instance.fromSimpleTimeSeries(data);

		// compare the principal components
		System.out.println("compare the principal components:");
		double[][] res_pcomp = result.getPrincipalComponents().getArray();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.println(pcomps[i][j] + "   ,   " + res_pcomp[i][j]);
			}
		}

		// compare the lambdas
		System.out.println("compare the lambdas:");
		double[] res_plambda = result.getLambda().transpose().getArray()[0];
		for (int i = 0; i < SIZE; i++) {
			System.out.println(plambda[i] + "  ,  " + res_plambda[i]);
		}

		// compare the principle factors
		System.out.println("compare the principle factors:");
		double[][] res_pfacs = result.getPrinicipalFactors().getArray();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.println(pfacs[i][j] + "  ,  " + res_pfacs[i][j]);
			}
		}
	}

}

package com.jusdt.datamining.dimensionality.reduction.pca;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 命令行操作类
 */
public class Main {

	/**
	 * Run a PCA on vector data
	 *
	 * @param av are file references containing vector data
	 * @throws Exception
	 */
	public static void main(String... av) throws Exception {

		if (av.length == 0) {
			throw new IllegalArgumentException("Usage: pca FILES...");
		}

		for (String filename : av) {
			try (DataReader dr = new DataReader(new FileReader(filename + ".data"))) {
				double[] data = dr.getData();
				System.out.println(filename + ": vector length = " + data.length);

				PCACoreHandler handler = new PCACoreHandler();
				PCACore pca = handler.fromSimpleTimeSeries(data);

				log(filename + "_pcomps.data", filename + ": principle components", pca.getPrincipalComponents());
				log(filename + "_lambda.data", filename + ": lambda", pca.getLambda());
				log(filename + "_pfacs.data", filename + ": principle factors", pca.getPrinicipalFactors());

				Matrix cc = handler.correlationCircle(pca);
				log(filename + "_cc.data", filename + ": correlation circle", cc);

				Matrix cumcon = handler.cumulativeContribution(pca);
				log(filename + "_cumcon.data", filename + ": cumulative contributions", cumcon);
			}
		}
	}

	private static void log(String filename, String tag, Matrix m) throws IOException {
		try (PrintWriter fp = new PrintWriter(new FileWriter(filename))) {
			System.out.println(tag + ":");
			MatrixHelper.print(m, fp, 1, 4);
		}
	}
}

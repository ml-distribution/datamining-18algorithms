package com.jusdt.datamining.dimensionality.reduction.pca;

/**
 * 封装PCACore的调用类
 */
public class PCACoreHandler {

	public PCACoreHandler() {
	}

	/**
	 * Run a principal component analysis of a matrix.
	 *
	 * @param m the matrix
	 * @return the principle components
	 */
	public PCACore fromMatrix(Matrix m) {
		return new PCACore(m);
	}

	/**
	 * Run a principal component analysis from a simple time series vector. We
	 * are converting the data into a Toeplitz style matrix before running the
	 * PCA.
	 *
	 * @param data the time series vector
	 * @return the principle components
	 */
	public PCACore fromSimpleTimeSeries(double[] data) {
		Matrix m = new ToeplitzMatrix(data);
		PCACore pca = new PCACore(m);
		return pca;
	}

	/**
	 * Calculate the correlations circle for two components. This is quick and
	 * dirty we are not doing any validity checks to make sure the PCA has
	 * completed successfully.
	 *
	 * @param pca the PCA
	 * @param compare the principal factor columns to compare
	 * @return the correlations circle
	 */
	public Matrix correlationCircle(PCACore pca, int[] compare) {
		double[][] F = pca.getPrinicipalFactors().getArray();
		double[][] L = pca.getLambda().getArray();

		// calculate the correlation circle
		Matrix cc = new Matrix(F.length, compare.length);
		double[][] CC = cc.getArray();

		for (int n = 0; n < compare.length; n++) {
			int index = compare[n];
			double s = Math.sqrt(L[index][0]);
			for (int m = 0; m < F.length; m++) {
				double f = F[m][index];

				CC[m][n] = s * f;
			}
		}
		return cc;
	}

	/**
	 * Calculate the correlations circle for the two largest eigenvalues.
	 *
	 * @param pca the pca
	 * @return the correlations circle
	 */
	public Matrix correlationCircle(PCACore pca) {
		return correlationCircle(pca, new int[] { 0, 1 });
	}

	/**
	 * Normalize the eigenvalues so we can create a scree plot.
	 *
	 * @param pca the pca
	 * @return the normalized eigenvalues;
	 */
	public Matrix normalizeLambda(PCACore pca) {

		double[][] L = pca.getLambda().getArrayCopy();
		Matrix nl = new Matrix(L);
		double sum = 0;
		for (int n = 0; n < L.length; n++) {
			sum += L[n][0];
		}
		for (int n = 0; n < L.length; n++) {
			L[n][0] = L[n][0] / sum;
		}
		return nl;
	}

	/**
	 * Calculate the cumulative contribution of the eigenvectors
	 *
	 * @param pca is the pca
	 * @return the cumulative contributions of the eigenvectors
	 */
	public Matrix cumulativeContribution(PCACore pca) {
		Matrix nl = normalizeLambda(pca);
		double[][] CC = nl.getArrayCopy();
		Matrix cc = new Matrix(CC);
		double cum = 0;
		for (int n = 0; n < CC.length; n++) {
			cum = CC[n][0] = CC[n][0] + cum;
		}
		return cc;
	}
}

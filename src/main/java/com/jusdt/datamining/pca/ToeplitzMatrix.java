package com.jusdt.datamining.pca;

/**
 * Toeplitz matrix
 */
public class ToeplitzMatrix extends Matrix {

	/**
	 * Toeplitz matrix styles
	 */
	public static enum Type {

		Triangular, Symmetrical, Circulant
	};

	/**
	 * Create a symmetrical Toeplitz-style matrix from a vector.
	 *
	 * @param v
	 */
	public ToeplitzMatrix(double[] v) {
		this(v, Type.Symmetrical);
	}

	/**
	 * Create a Toeplitz matrix from a vector.
	 *
	 * @param v the vector
	 * @param type the matrix style
	 */
	public ToeplitzMatrix(double[] v, Type type) {
		super(v.length, v.length);
		int n = v.length;
		double[][] arr = getArray();

		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j <= i; j++) {
				int index = i - j;
				arr[i][j] = v[i - j];
				switch (type) {
				default:
				case Triangular:
					// do nothing
					break;
				case Symmetrical:
					arr[j][i] = v[i - j];
					break;
				case Circulant:
					if (j != i) {
						arr[j][i] = v[n - index];
					}
					break;
				}
			}
		}
	}
}

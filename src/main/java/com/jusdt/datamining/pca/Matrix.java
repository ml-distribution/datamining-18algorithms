package com.jusdt.datamining.pca;

/**
 * 矩阵对象
 * The Matrix Class provides the fundamental operations of numerical linear
 * algebra. This is not a general purpose matrix class, but provides enough
 * support for our needs.
 *
 * Derived and modified from work of the National Institute of Standards and
 * Technology (NIST).
 */
public class Matrix {

	// Array for internal storage of elements.
	private final double[][] A;
	// Row and column dimensions.
	private final int m, n;

	/**
	 * Construct an m-by-n matrix of zeros.
	 *
	 * @param m Number of rows.
	 * @param n Number of colums.
	 */
	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		A = new double[m][n];
	}

	/**
	 * Construct an m-by-n constant matrix.
	 *
	 * @param m Number of rows.
	 * @param n Number of colums.
	 * @param s Fill the matrix with this scalar value.
	 */
	public Matrix(int m, int n, double s) {
		this.m = m;
		this.n = n;
		A = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = s;
			}
		}
	}

	/**
	 * Construct a matrix from a 2-D array.
	 *
	 * @param A Two-dimensional array of doubles.
	 */
	public Matrix(double[][] A) {
		m = A.length;
		n = A[0].length;
		for (int i = 0; i < m; i++) {
			if (A[i].length != n) {
				throw new MatrixException("All rows must have the same length.");
			}
		}
		this.A = A;
	}

	/**
	 * Construct a matrix quickly without checking arguments.
	 *
	 * @param A Two-dimensional array of doubles.
	 * @param m Number of rows.
	 * @param n Number of colums.
	 */
	public Matrix(double[][] A, int m, int n) {
		this.A = A;
		this.m = m;
		this.n = n;
	}

	/**
	 * Construct a matrix from a one-dimensional packed array
	 *
	 * @param vals One-dimensional array of doubles, packed by columns (ala
	 * Fortran).
	 * @param m Number of rows.
	 */
	public Matrix(double vals[], int m) {
		this.m = m;
		n = (m != 0 ? vals.length / m : 0);
		if (m * n != vals.length) {
			throw new MatrixException("Array length must be a multiple of m.");
		}
		A = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = vals[i + j * m];
			}
		}
	}

	/**
	 * Center the matrix values
	 *
	 * @return
	 */
	public Matrix center() {
		Matrix c = new Matrix(m, n);
		double[][] C = c.getArray();
		for (int i = 0; i < m; i++) {
			double sum = 0.;
			for (int j = 0; j < n; j++) {
				sum += A[i][j];
			}

			double mean = sum / n;
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] - mean;
			}
		}
		return c;
	}

	/**
	 * Center and weight Sample variance for grouped data
	 *
	 * s^2 = SUM(Mi - xbar)^2 / (n-1)
	 *
	 * @return
	 */
	public Matrix wcenter() {
		// center the data
		Matrix s = center().transpose();
		double[][] S = s.getArray();

		// calculate the sample variance
		double[] sigma = new double[n];
		for (int j = 0; j < n; j++) {
			double ssum = 0;
			for (int i = 0; i < m; i++) {
				ssum += (S[i][j] * S[i][j]);
			}
			sigma[j] = Math.sqrt(ssum / (m - 1));
		}

		// weigh the data
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				S[i][j] = S[i][j] / sigma[j];
			}
		}

		return s;
	}

	/**
	 * Get the diagonal as a 1-col matrix
	 *
	 * @return diagonal
	 */
	public Matrix diag() {

		int nrows = Math.min(m, n);
		Matrix c = new Matrix(nrows, 1);
		double[][] C = c.getArray();

		for (int i = 0; i < nrows; i++) {
			C[i][0] = A[i][i];
		}

		return c;
	}

	/**
	 * Access the internal two-dimensional array.
	 *
	 * @return Pointer to the two-dimensional array of matrix elements.
	 */
	public double[][] getArray() {
		return A;
	}

	/**
	 * Copy the internal two-dimensional array.
	 *
	 * @return Two-dimensional array copy of matrix elements.
	 */
	public double[][] getArrayCopy() {
		double[][] C = new double[m][n];
		for (int i = 0; i < m; i++) {
			System.arraycopy(A[i], 0, C[i], 0, n);
		}
		return C;
	}

	/**
	 * Get row dimension.
	 *
	 * @return m, the number of rows.
	 */
	public int getNRows() {
		return m;
	}

	/**
	 * Get column dimension.
	 *
	 * @return n, the number of columns.
	 */
	public int getNCols() {
		return n;
	}

	/**
	 * Get a single element.
	 *
	 * @param i Row index.
	 * @param j Column index.
	 * @return A(i,j)
	 */
	public double get(int i, int j) {
		return A[i][j];
	}

	/**
	 * Get a submatrix.
	 *
	 * @param i0 Initial row index
	 * @param i1 Final row index
	 * @param j0 Initial column index
	 * @param j1 Final column index
	 * @return A(i0:i1,j0:j1)
	 */
	public Matrix getMatrix(int i0, int i1, int j0, int j1) {
		Matrix X = new Matrix(i1 - i0 + 1, j1 - j0 + 1);
		double[][] B = X.getArray();
		for (int i = i0; i <= i1; i++) {
			for (int j = j0; j <= j1; j++) {
				B[i - i0][j - j0] = A[i][j];
			}
		}
		return X;
	}

	/**
	 * Get a submatrix.
	 *
	 * @param r Array of row indices.
	 * @param c Array of column indices.
	 * @return A(r(:),c(:))
	 */
	public Matrix getMatrix(int[] r, int[] c) {
		Matrix X = new Matrix(r.length, c.length);
		double[][] B = X.getArray();
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < c.length; j++) {
				B[i][j] = A[r[i]][c[j]];
			}
		}
		return X;
	}

	/**
	 * Get a submatrix.
	 *
	 * @param i0 Initial row index
	 * @param i1 Final row index
	 * @param c Array of column indices.
	 * @return A(i0:i1,c(:))
	 */
	public Matrix getMatrix(int i0, int i1, int[] c) {
		Matrix X = new Matrix(i1 - i0 + 1, c.length);
		double[][] B = X.getArray();
		for (int i = i0; i <= i1; i++) {
			for (int j = 0; j < c.length; j++) {
				B[i - i0][j] = A[i][c[j]];
			}
		}
		return X;
	}

	/**
	 * Get a submatrix.
	 *
	 * @param r Array of row indices.
	 * @param i0 Initial column index
	 * @param i1 Final column index
	 * @return A(r(:),j0:j1)
	 */
	public Matrix getMatrix(int[] r, int j0, int j1) {
		Matrix X = new Matrix(r.length, j1 - j0 + 1);
		double[][] B = X.getArray();
		for (int i = 0; i < r.length; i++) {
			for (int j = j0; j <= j1; j++) {
				B[i][j - j0] = A[r[i]][j];
			}
		}
		return X;
	}

	/**
	 * Set a single element.
	 *
	 * @param i Row index.
	 * @param j Column index.
	 * @param s A(i,j).
	 */
	public void set(int i, int j, double s) {
		A[i][j] = s;
	}

	/**
	 * Set a submatrix.
	 *
	 * @param i0 Initial row index
	 * @param i1 Final row index
	 * @param j0 Initial column index
	 * @param j1 Final column index
	 * @param X A(i0:i1,j0:j1)
	 */
	public void setMatrix(int i0, int i1, int j0, int j1, Matrix X) {
		for (int i = i0; i <= i1; i++) {
			for (int j = j0; j <= j1; j++) {
				A[i][j] = X.get(i - i0, j - j0);
			}
		}
	}

	/**
	 * Set a submatrix.
	 *
	 * @param r Array of row indices.
	 * @param c Array of column indices.
	 * @param X A(r(:),c(:))
	 */
	public void setMatrix(int[] r, int[] c, Matrix X) {
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < c.length; j++) {
				A[r[i]][c[j]] = X.get(i, j);
			}
		}
	}

	/**
	 * Set a submatrix.
	 *
	 * @param r Array of row indices.
	 * @param j0 Initial column index
	 * @param j1 Final column index
	 * @param X A(r(:),j0:j1)
	 */
	public void setMatrix(int[] r, int j0, int j1, Matrix X) {
		for (int i = 0; i < r.length; i++) {
			for (int j = j0; j <= j1; j++) {
				A[r[i]][j] = X.get(i, j - j0);
			}
		}
	}

	/**
	 * Set a submatrix.
	 *
	 * @param i0 Initial row index
	 * @param i1 Final row index
	 * @param c Array of column indices.
	 * @param X A(i0:i1,c(:))
	 */
	public void setMatrix(int i0, int i1, int[] c, Matrix X) {
		for (int i = i0; i <= i1; i++) {
			for (int j = 0; j < c.length; j++) {
				A[i][c[j]] = X.get(i - i0, j);
			}
		}
	}

	/**
	 * Matrix transpose.
	 *
	 * @return A'
	 */
	public Matrix transpose() {
		Matrix X = new Matrix(n, m);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[j][i] = A[i][j];
			}
		}
		return X;
	}

	/**
	 * One norm
	 *
	 * @return maximum column sum.
	 */
	public double norm1() {
		double f = 0;
		for (int j = 0; j < n; j++) {
			double s = 0;
			for (int i = 0; i < m; i++) {
				s += Math.abs(A[i][j]);
			}
			f = Math.max(f, s);
		}
		return f;
	}

	/**
	 * Two norm
	 *
	 * @return maximum singular value.
	 */
	public double norm2() {
		return (new SVD(this).norm2());
	}

	/**
	 * Infinity norm
	 *
	 * @return maximum row sum.
	 */
	public double normInf() {
		double f = 0;
		for (int i = 0; i < m; i++) {
			double s = 0;
			for (int j = 0; j < n; j++) {
				s += Math.abs(A[i][j]);
			}
			f = Math.max(f, s);
		}
		return f;
	}

	/**
	 * Frobenius norm
	 *
	 * @return sqrt of sum of squares of all elements.
	 */
	public double normF() {
		double f = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				f = Math.hypot(f, A[i][j]);
			}
		}
		return f;
	}

	/**
	 * Unary minus
	 *
	 * @return -A
	 */
	public Matrix uminus() {
		Matrix X = new Matrix(m, n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = -A[i][j];
			}
		}
		return X;
	}

	/**
	 * C = A + B
	 *
	 * @param B another matrix
	 * @return A + B
	 */
	public Matrix plus(Matrix B) {
		checkMatrixDimensions(B);
		Matrix X = new Matrix(m, n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] + B.A[i][j];
			}
		}
		return X;
	}

	/**
	 * A = A + B
	 *
	 * @param B another matrix
	 * @return A + B
	 */
	public Matrix plusEquals(Matrix B) {
		checkMatrixDimensions(B);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] + B.A[i][j];
			}
		}
		return this;
	}

	/**
	 * C = A - B
	 *
	 * @param B another matrix
	 * @return A - B
	 */
	public Matrix minus(Matrix B) {
		checkMatrixDimensions(B);
		Matrix X = new Matrix(m, n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] - B.A[i][j];
			}
		}
		return X;
	}

	/**
	 * A = A - B
	 *
	 * @param B another matrix
	 * @return A - B
	 */
	public Matrix minusEquals(Matrix B) {
		checkMatrixDimensions(B);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] - B.A[i][j];
			}
		}
		return this;
	}

	/**
	 * Element-by-element multiplication, C = A.*B
	 *
	 * @param B another matrix
	 * @return A.*B
	 */
	public Matrix arrayTimes(Matrix B) {
		checkMatrixDimensions(B);
		Matrix X = new Matrix(m, n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] * B.A[i][j];
			}
		}
		return X;
	}

	/**
	 * Element-by-element multiplication in place, A = A.*B
	 *
	 * @param B another matrix
	 * @return A.*B
	 */
	public Matrix arrayTimesEquals(Matrix B) {
		checkMatrixDimensions(B);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] * B.A[i][j];
			}
		}
		return this;
	}

	/**
	 * Element-by-element right division, C = A./B
	 *
	 * @param B another matrix
	 * @return A./B
	 */
	public Matrix arrayRightDivide(Matrix B) {
		checkMatrixDimensions(B);
		Matrix X = new Matrix(m, n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] / B.A[i][j];
			}
		}
		return X;
	}

	/**
	 * Element-by-element right division in place, A = A./B
	 *
	 * @param B another matrix
	 * @return A./B
	 */
	public Matrix arrayRightDivideEquals(Matrix B) {
		checkMatrixDimensions(B);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] / B.A[i][j];
			}
		}
		return this;
	}

	/**
	 * Element-by-element left division, C = A.\B
	 *
	 * @param B another matrix
	 * @return A.\B
	 */
	public Matrix arrayLeftDivide(Matrix B) {
		checkMatrixDimensions(B);
		Matrix X = new Matrix(m, n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = B.A[i][j] / A[i][j];
			}
		}
		return X;
	}

	/**
	 * Element-by-element left division in place, A = A.\B
	 *
	 * @param B another matrix
	 * @return A.\B
	 */
	public Matrix arrayLeftDivideEquals(Matrix B) {
		checkMatrixDimensions(B);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = B.A[i][j] / A[i][j];
			}
		}
		return this;
	}

	/**
	 * Multiply a matrix by a scalar, C = s*A
	 *
	 * @param s scalar
	 * @return s*A
	 */
	public Matrix times(double s) {
		Matrix X = new Matrix(m, n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = s * A[i][j];
			}
		}
		return X;
	}

	/**
	 * Multiply a matrix by a scalar in place, A = s*A
	 *
	 * @param s scalar
	 * @return replace A by s*A
	 */
	public Matrix timesEquals(double s) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = s * A[i][j];
			}
		}
		return this;
	}

	/**
	 * Linear algebraic matrix multiplication, A * B
	 *
	 * @param B another matrix
	 * @return Matrix product, A * B
	 */
	public Matrix times(Matrix B) {
		if (B.m != n) {
			throw new MatrixException("Matrix inner dimensions must agree.");
		}
		Matrix X = new Matrix(m, B.n);
		double[][] C = X.getArray();
		double[] Bcolj = new double[n];
		for (int j = 0; j < B.n; j++) {
			for (int k = 0; k < n; k++) {
				Bcolj[k] = B.A[k][j];
			}
			for (int i = 0; i < m; i++) {
				double[] Arowi = A[i];
				double s = 0;
				for (int k = 0; k < n; k++) {
					s += Arowi[k] * Bcolj[k];
				}
				C[i][j] = s;
			}
		}
		return X;
	}

	/**
	 * Matrix rank
	 *
	 * @return effective numerical rank, obtained from SVD.
	 */
	public int rank() {
		return new SVD(this).rank();
	}

	/**
	 * Matrix condition (2 norm)
	 *
	 * @return ratio of largest to smallest singular value.
	 */
	public double cond() {
		return new SVD(this).cond();
	}

	/**
	 * Matrix trace.
	 *
	 * @return sum of the diagonal elements.
	 */
	public double trace() {
		double t = 0;
		for (int i = 0; i < Math.min(m, n); i++) {
			t += A[i][i];
		}
		return t;
	}

	/**
	 * Generate matrix with random elements
	 *
	 * @param m Number of rows.
	 * @param n Number of colums.
	 * @return An m-by-n matrix with uniformly distributed random elements.
	 */
	public static Matrix random(int m, int n) {
		Matrix A = new Matrix(m, n);
		double[][] X = A.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = Math.random();
			}
		}
		return A;
	}

	/**
	 * Generate identity matrix
	 *
	 * @param m Number of rows.
	 * @param n Number of colums.
	 * @return An m-by-n matrix with ones on the diagonal and zeros elsewhere.
	 */
	public static Matrix identity(int m, int n) {
		Matrix A = new Matrix(m, n);
		double[][] X = A.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = (i == j ? 1.0 : 0.0);
			}
		}
		return A;
	}

	/**
	 * Check if size(A) == size(B)
	 */
	private void checkMatrixDimensions(Matrix B) {
		if (B.m != m || B.n != n) {
			throw new IllegalArgumentException("Matrix dimensions must agree.");
		}
	}

	@Override
	public String toString() {
		return "Matrix{" + "nrows=" + m + ", ncols=" + n + '}';
	}
}

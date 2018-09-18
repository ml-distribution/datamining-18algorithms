package com.jusdt.datamining.pca;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 矩阵的操作类
 */
public class MatrixHelper {

	/**
	 * Print the matrix to stdout. Line the elements up in columns with a
	 * Fortran-like 'Fw.d' style format.
	 *
	 * @param w Column width.
	 * @param d Number of digits after the decimal.
	 */
	public static void print(Matrix a, int w, int d) {
		print(a, new PrintWriter(System.out, true), w, d);
	}

	/**
	 * Print the matrix to the output stream. Line the elements up in columns
	 * with a Fortran-like 'Fw.d' style format.
	 *
	 * @param output Output stream.
	 * @param w Column width.
	 * @param d Number of digits after the decimal.
	 */
	public static void print(Matrix a, PrintWriter output, int w, int d) {
		DecimalFormat format = new DecimalFormat();
		format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		format.setMinimumIntegerDigits(1);
		format.setMaximumFractionDigits(d);
		format.setMinimumFractionDigits(d);
		format.setGroupingUsed(false);
		print(a, output, format, w + 2);
	}

	/**
	 * Print the matrix to stdout. Line the elements up in columns. Use the
	 * format object, and right justify within columns of width characters. Note
	 * that is the matrix is to be read back in, you probably will want to use a
	 * NumberFormat that is set to US Locale.
	 *
	 * @param format A Formatting object for individual elements.
	 * @param width Field width for each column.
	 * @see java.text.DecimalFormat#setDecimalFormatSymbols
	 */
	public static void print(Matrix a, NumberFormat format, int width) {
		print(a, new PrintWriter(System.out, true), format, width);
	}

	// DecimalFormat is a little disappointing coming from Fortran or C's printf.
	// Since it doesn't pad on the left, the elements will come out different
	// widths.  Consequently, we'll pass the desired column width in as an
	// argument and do the extra padding ourselves.
	/**
	 * Print the matrix to the output stream. Line the elements up in columns.
	 * Use the format object, and right justify within columns of width
	 * characters. Note that is the matrix is to be read back in, you probably
	 * will want to use a NumberFormat that is set to US Locale.
	 *
	 * @param output the output stream.
	 * @param format A formatting object to format the matrix elements
	 * @param width Column width.
	 * @see java.text.DecimalFormat#setDecimalFormatSymbols
	 */
	public static void print(Matrix a, PrintWriter output, NumberFormat format, int width) {
		output.println(); // start on new line.
		int m = a.getNRows();
		int n = a.getNCols();
		double[][] A = a.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				String s = format.format(A[i][j]); // format the number
				int padding = Math.max(1, width - s.length()); // At _least_ 1 space
				for (int k = 0; k < padding; k++) {
					output.print(' ');
				}
				output.print(s);
			}
			output.println();
		}
		output.println(); // end with blank line.
	}
}

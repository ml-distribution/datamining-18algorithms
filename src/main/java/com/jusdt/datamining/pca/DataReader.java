package com.jusdt.datamining.pca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple vector data reader
 *
 * @author uwe
 */
public class DataReader extends BufferedReader {

	public DataReader(Reader in, int sz) {
		super(in, sz);
	}

	public DataReader(Reader in) {
		super(in);
	}

	/**
	 * Get the (vector) data contained in the file. The data is stored one value
	 * per line. Empty lines are ignored.
	 *
	 * @return the data
	 */
	public double[] getData() throws IOException {
		List<Double> dataList = new ArrayList<>();
		String line;
		while ((line = readLine()) != null) {
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}
			dataList.add(Double.valueOf(line));
		}

		double[] vector = new double[dataList.size()];
		int i = 0;
		for (Double d : dataList) {
			vector[i++] = d;
		}
		return vector;
	}
}

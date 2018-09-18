package com.jusdt.datamining.pca;

import junit.framework.TestCase;

public class ToeplitzMatrixTest extends TestCase {

	public ToeplitzMatrixTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testToeplitz() {
		double[] data = new double[] { 1, 2, 3, 4, 5, 6 };

		ToeplitzMatrix m = new ToeplitzMatrix(data);
		//		MatrixHelper.print(m, 1, 3);
		assertTrue("nrows wrong", m.getNRows() == 6);
		assertTrue("ncols wrong", m.getNCols() == 6);
		double[][] a = m.getArray();

		assertEquals("0,0", 1., a[0][0]);
		assertEquals("0,1", 2., a[0][1]);
		assertEquals("0,2", 3., a[0][2]);
		assertEquals("0,3", 4., a[0][3]);
		assertEquals("0,4", 5., a[0][4]);
		assertEquals("0,5", 6., a[0][5]);

		assertEquals("1,0", 2., a[1][0]);
		assertEquals("1,1", 1., a[1][1]);
		assertEquals("1,2", 2., a[1][2]);
		assertEquals("1,3", 3., a[1][3]);
		assertEquals("1,4", 4., a[1][4]);
		assertEquals("1,5", 5., a[1][5]);

		assertEquals("2,0", 3., a[2][0]);
		assertEquals("2,1", 2., a[2][1]);
		assertEquals("2,2", 1., a[2][2]);
		assertEquals("2,3", 2., a[2][3]);
		assertEquals("2,4", 3., a[2][4]);
		assertEquals("2,5", 4., a[2][5]);

		assertEquals("3,0", 4., a[3][0]);
		assertEquals("3,1", 3., a[3][1]);
		assertEquals("3,2", 2., a[3][2]);
		assertEquals("3,3", 1., a[3][3]);
		assertEquals("3,4", 2., a[3][4]);
		assertEquals("3,5", 3., a[3][5]);

		assertEquals("4,0", 5., a[4][0]);
		assertEquals("4,1", 4., a[4][1]);
		assertEquals("4,2", 3., a[4][2]);
		assertEquals("4,3", 2., a[4][3]);
		assertEquals("4,4", 1., a[4][4]);
		assertEquals("4,5", 2., a[4][5]);

		assertEquals("5,0", 6., a[5][0]);
		assertEquals("5,1", 5., a[5][1]);
		assertEquals("5,2", 4., a[5][2]);
		assertEquals("5,3", 3., a[5][3]);
		assertEquals("5,4", 2., a[5][4]);
		assertEquals("5,5", 1., a[5][5]);
	}

}

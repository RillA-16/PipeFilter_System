/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.CommonFilter;
import Framework.CommonFilterImpl;

public class StudentSourceFilter extends CommonFilterImpl {
	private String sourceFile;

	public StudentSourceFilter(int outPort, int inPort) {
		super(outPort, inPort);
	}

	@Override
	public boolean specificComputationForFilter() throws IOException {
		int byte_read;
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(sourceFile)));

		while (true) {

			byte_read = br.read();
			if (byte_read == -1) {
				return true;
			}
			out.get(0).write(byte_read);
		}
	}

	@Override
	public void setFileName(String fileName) {
		this.sourceFile = fileName;
	}

}

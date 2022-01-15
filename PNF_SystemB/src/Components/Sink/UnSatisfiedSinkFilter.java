/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Sink;

import java.io.FileWriter;
import java.io.IOException;

import Framework.CommonFilterImpl;

public class UnSatisfiedSinkFilter extends CommonFilterImpl {
	private String sinkFile;

	public UnSatisfiedSinkFilter(int outPort, int inPort) {
		super(outPort, inPort);
	}

	@Override
	public boolean specificComputationForFilter() throws IOException {
		int byte_read;
		FileWriter fw = new FileWriter(this.sinkFile);
		while (true) {

			byte_read = in.get(0).read();
			if (byte_read == -1) {
				fw.close();
				System.out.println("::Filtering is finished; Output file -2 is created.");
				return true;
			}
			fw.write((char) byte_read);
		}
	}

	@Override
	public void setFileName(String fileName) {
		this.sinkFile = fileName;
	}
}

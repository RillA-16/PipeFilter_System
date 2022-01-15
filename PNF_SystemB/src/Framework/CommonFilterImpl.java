/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Vector;

public abstract class CommonFilterImpl implements CommonFilter {
	protected Vector<PipedInputStream> in = new Vector<PipedInputStream>();
	protected Vector<PipedOutputStream> out = new Vector<PipedOutputStream>();

	private int outPort, inPort;

	public CommonFilterImpl(int outPort, int inPort) {
		this.outPort = outPort;
		this.inPort = inPort;

		for (int i = 0; i < outPort; i++) {
			out.add(i, new PipedOutputStream());
		}

		for (int i = 0; i < inPort; i++) {
			in.add(i, new PipedInputStream());
		}
	}

	public void connectOutputTo(int outPutPort, CommonFilter nextFilter, int inPutPort) throws IOException {
		out.get(outPutPort).connect(nextFilter.getPipedInputStream().get(inPutPort));
	}

	public void connectInputTo(int outPutPort, CommonFilter previousFilter, int inPutPort) throws IOException {
		in.get(inPutPort).connect(previousFilter.getPipedOutputStream().get(outPutPort));
	}

	public Vector<PipedInputStream> getPipedInputStream() {
		return in;
	}

	public Vector<PipedOutputStream> getPipedOutputStream() {
		return out;
	}

	abstract public boolean specificComputationForFilter() throws IOException;

	// Implementation defined in Runnable interface for thread
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException)
				return;
			else
				System.out.println(e);
		} finally {
			closePorts();
		}
	}

	private void closePorts() {
		try {
			for (int i = 0; i < outPort; i++) {
				out.get(i).close();
			}
			for (int i = 0; i < inPort; i++) {
				in.get(i).close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
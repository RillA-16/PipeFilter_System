/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */
package Framework;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Vector;

public interface CommonFilter extends Runnable {
	public void connectOutputTo(int opuPutPort, CommonFilter filter, int inPutPort) throws IOException;

	public void connectInputTo(int opuPutPort, CommonFilter filter, int inPutPort) throws IOException;

	public Vector<PipedInputStream> getPipedInputStream();

	public Vector<PipedOutputStream> getPipedOutputStream();

	public void setFileName(String fileName);
}

package org.zkoss.zss.performance;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.util.PerformanceMeter;

/**
 * To measure file importing time.
 * @author hawk
 *
 */
public class ImportTimeMeter implements PerformanceMeter {

	private long startAtClient;
	private String requestId;
	
	@Override
	public void requestStartAtClient(String requestId, Execution exec, long time) {
	}

	@Override
	public void requestReceiveAtClient(String requestId, Execution exec,
			long time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestCompleteAtClient(String requestId, Execution exec,
			long time) {
		if (this.requestId.equals(requestId)){
			System.out.println("importing time: "+((time-startAtClient)/1000)+" s");
		}
	}

	@Override
	public void requestStartAtServer(String requestId, Execution exec, long time) {
		String requestURI = ((HttpServletRequest) exec.getNativeRequest())
				.getRequestURI();
		if (requestURI.endsWith("zul")){
			System.out.println("start "+requestURI+" file="+exec.getParameter("file"));
			startAtClient = time;
			this.requestId = requestId;
		}

	}

	@Override
	public void requestCompleteAtServer(String requestId, Execution exec,
			long time) {
		// TODO Auto-generated method stub

	}

}

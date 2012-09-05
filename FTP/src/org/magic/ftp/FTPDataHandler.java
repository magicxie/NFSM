package org.magic.ftp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class FTPDataHandler implements IoHandler {

	private int linkId;
	
	public FTPDataHandler(int linkId) {
		this.linkId = linkId;
	}
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
		while(true){
			if(Main.sessionShareLink.containsKey(linkId)){
				System.out.println("OY : " + linkId);
				byte[] data = Main.sessionShareLink.get(linkId);
				session.write(IoBuffer.wrap(data));
				Main.sessionShareLink.remove(linkId);
				break;
			}
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub

	}

}

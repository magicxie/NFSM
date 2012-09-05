package org.magic.ftp;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.magic.ftp.command.Command;
import org.magic.ftp.protocal.FTPRequest;
import org.magic.ftp.protocal.FTPResponse;

public class FTPHandler implements IoHandler {
	
	@Override
	public void exceptionCaught(IoSession session, Throwable t)
			throws Exception {
		t.printStackTrace();
		session.write(new FTPResponse(Command.SC_530, t.getMessage()));

	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		FTPRequest request = (FTPRequest) message;
		FTPResponse response = request.getCommand().execute(request.getParameter(), session);
		session.write(response);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		session.removeAttribute("CMD");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		session.write(IoBuffer.wrap("220 OK \r\n".getBytes()));
	}

}

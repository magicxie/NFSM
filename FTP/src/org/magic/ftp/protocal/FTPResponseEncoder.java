package org.magic.ftp.protocal;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.magic.ftp.command.Command;

public class FTPResponseEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		
		FTPResponse response = (FTPResponse) message;

		StringBuilder sb = new StringBuilder();
		sb.append(response.getStatusCode())
			.append(Command.SP)
			.append(response.getMessage())
			.append(Command.CRLF);
		
		out.write(IoBuffer.wrap(sb.toString().getBytes()));
		
		
	}


}

package org.magic.ftp.protocal;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.magic.ftp.command.Command;

public class FTPRequestDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		int limit = in.limit();
		byte[] dst = new byte[limit];
		in.get(dst, 0, limit);

		String commandLine = new String(dst);
		//...
		if(commandLine.endsWith(Command.CRLF)){
			commandLine = session.getAttribute("CMD") == null ? commandLine :  session.getAttribute("CMD") + commandLine;
			String[] cap = commandLine.replace(Command.CRLF, "").split(" ", 2);
			Command cmd = Command.get(cap[0].toUpperCase());
			
			out.write(new FTPRequest(cmd, cap.length > 1 ? cap[1] : ""));
			session.removeAttribute("CMD");
			return true;
		}else{
			String oldValue = (String) session.getAttribute("CMD");
			if(oldValue == null){
				session.setAttribute("CMD", commandLine);
			}else{
				session.replaceAttribute("CMD", oldValue, oldValue + commandLine);
			}
			return false;
		}

	}

}

package org.magic.ftp.command;

import org.apache.mina.core.session.IoSession;
import org.magic.ftp.protocal.FTPResponse;

public class CMD_USER extends Command {

	@Override
	public FTPResponse execute(String parameter, IoSession session) {
		return new FTPResponse(Command.SC_230, "OK");
		
	}

}

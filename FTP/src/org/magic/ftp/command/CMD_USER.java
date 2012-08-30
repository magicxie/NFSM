package org.magic.ftp.command;

import org.magic.ftp.protocal.FTPResponse;

public class CMD_USER extends Command {

	@Override
	public FTPResponse execute(String parameter) {
		return new FTPResponse(Command.SC_230, "OK");
		
	}

}

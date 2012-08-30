package org.magic.ftp.command;

import org.magic.ftp.protocal.FTPResponse;

public class CMD_SYST extends Command {

	@Override
	public FTPResponse execute(String parameter) {
		return new FTPResponse(Command.SC_215, System.getenv("OS") );
	}

}

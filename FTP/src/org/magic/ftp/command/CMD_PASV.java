package org.magic.ftp.command;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.magic.ftp.Main;
import org.magic.ftp.protocal.FTPResponse;

public class CMD_PASV extends Command {

	@Override
	public FTPResponse execute(String parameter) {
		
		NioSocketAcceptor acceptor = Main.openDataConnection();
		try {
			int port = 1026;
			int x = port / 256;
			int r = port % 256;
			acceptor.bind(new InetSocketAddress(port));
			return new FTPResponse(Command.SC_227,
					"Entering Passive Mode (127,0,0,1,"+x + "," + r + ")");
		} catch (IOException e) {
			return new FTPResponse(Command.SC_227,  "Entering Passive Mode (127,0,0,1,4,0)");
		}
		
	}

}

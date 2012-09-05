package org.magic.ftp.command;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.magic.ftp.Main;
import org.magic.ftp.protocal.FTPResponse;

public class CMD_PASV extends Command {

	@Override
	public FTPResponse execute(String parameter, IoSession session) {
		
		try {
			int port = 1026;
			int x = port / 256;
			int r = port % 256;
			
//			ServerSocket dc =  Main.newDataConnection(port);
//			dc.setSoTimeout(50000);
//			session.setAttribute("DC", dc);
			
			session.setAttribute("SHARELINKID", Main.newNIODataConnection(port).hashCode());
			
			return new FTPResponse(Command.SC_227,
					"Entering Passive Mode (127,0,0,1,"+x + "," + r + ")");
		} catch (IOException e) {
			return new FTPResponse(Command.SC_227,  "Entering Passive Mode (127,0,0,1,4,0)");
		}
		
	}

}

package org.magic.ftp.command;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.magic.ftp.Main;
import org.magic.ftp.protocal.FTPResponse;

public class CMD_LIST extends Command {

	@Override
	public FTPResponse execute(String parameter, IoSession session) {
		
		
//		try {
//			ServerSocket dc =  (ServerSocket) session.getAttribute("DC");
//			if(dc == null){
//				return new FTPResponse(Command.SC_503,
//						"PASV or PORT first!");
//			}
//			session.write(new FTPResponse(Command.SC_150,  "File status okay; about to open data connection."));
//			Socket sc = dc.accept();
			
			
			Integer linkId = (Integer) session.getAttribute("SHARELINKID");
			
			if(linkId == null){
				return new FTPResponse(Command.SC_425,  "Can't open data connection.");
			}
			System.out.println("SC!!");
			byte[] bytes = ("04-18-12  03:50PM              9635168 [POPGO][Mobile_Suit_Gundam_00_the_Movie_A_wakening_of_the_Trailblazer][Fonts].rar\n" +
										"04-18-12  03:49PM               236624 [POPGO][Mobile_Suit_Gundam_00_the_Movie_A_wakening_of_the_Trailblazer][x264_10bit_trueHD][1080P][BluRay](D0580BB8).chs.ass\n" +
										"04-18-12  03:48PM               236620 [POPGO][Mobile_Suit_Gundam_00_the_Movie_A_wakening_of_the_Trailblazer][x264_10bit_trueHD][1080P][BluRay](D0580BB8).cht.ass\n" +
										"04-18-12  04:31PM           7955834741 [POPGO][Mobile_Suit_Gundam_00_the_Movie_A_wakening_of_the_Trailblazer][x264_10bit_trueHD][1080P][BluRay](D0580BB8).mkv\n").getBytes();

			Main.sessionShareLink.put(linkId, bytes);
			while(Main.sessionShareLink.containsKey(linkId)){
				//
			}
			System.out.println("REMOVE OK!");
			session.removeAttribute("SHARELINKID");
			
			Main.dataConnections.get(linkId).unbind();
			
//			sc.getOutputStream().write(bytes);
//			sc.close();
//			dc.close();
//			session.removeAttribute("DC");
			return new FTPResponse(Command.SC_226,
					"Closing data connection.");
//		} catch (IOException e) {
//			return new FTPResponse(Command.SC_425,  "Can't open data connection.");
//		}
		
	}

}

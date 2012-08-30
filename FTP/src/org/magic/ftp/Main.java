package org.magic.ftp;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.magic.ftp.protocal.FTPCodecFactory;

public class Main {

	public static void main(String[] args) throws IOException {

		NioSocketAcceptor messageAcceptor = new NioSocketAcceptor();
		messageAcceptor.setReuseAddress(true);

		messageAcceptor.setHandler(new FTPHandler());

		messageAcceptor.getFilterChain().addLast("protocal",
				new ProtocolCodecFilter(new FTPCodecFactory()));
		messageAcceptor.bind(new InetSocketAddress(21));

	}

	public static NioSocketAcceptor openDataConnection() {

		NioSocketAcceptor dataAcceptor = new NioSocketAcceptor();

		dataAcceptor.setReuseAddress(true);

		dataAcceptor.setHandler(new FTPDataHandler());

		return dataAcceptor;
	}
	
	

}

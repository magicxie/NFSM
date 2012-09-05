package org.magic.ftp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.magic.ftp.protocal.FTPCodecFactory;

public class Main {

	public static ConcurrentHashMap<Integer, byte[]> sessionShareLink = new ConcurrentHashMap<Integer, byte[]>();
	
	public static ConcurrentHashMap<Integer,NioSocketAcceptor> dataConnections = new ConcurrentHashMap<Integer,NioSocketAcceptor>();
	
	public static void main(String[] args) throws IOException {

		NioSocketAcceptor messageAcceptor = new NioSocketAcceptor();
		messageAcceptor.setReuseAddress(true);

		messageAcceptor.setHandler(new FTPHandler());

		messageAcceptor.getFilterChain().addLast("protocal",
				new ProtocolCodecFilter(new FTPCodecFactory()));
		messageAcceptor.bind(new InetSocketAddress(221));

	}

	public static ServerSocket newDataConnection(int port) throws IOException {

		ServerSocket socket = new ServerSocket(port, 0, InetAddress.getLocalHost());
		return socket;
	}
	
	public static NioSocketAcceptor newNIODataConnection(int port) throws IOException {

		NioSocketAcceptor messageAcceptor = new NioSocketAcceptor();
		messageAcceptor.setReuseAddress(true);

		messageAcceptor.setHandler(new FTPDataHandler(messageAcceptor.hashCode()));
		messageAcceptor.bind(new InetSocketAddress(port));
		dataConnections.put(messageAcceptor.hashCode(), messageAcceptor);
		
		return messageAcceptor;
	}

}

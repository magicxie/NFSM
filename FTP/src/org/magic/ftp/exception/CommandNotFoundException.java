package org.magic.ftp.exception;

public class CommandNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String command;
	
	public CommandNotFoundException(String command) {
		this.command = command;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Command:[" + command +" ] not found!";
	}
	
}

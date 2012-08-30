package org.magic.ftp.protocal;

import org.magic.ftp.command.Command;

public class FTPRequest {

	
	
	public FTPRequest(Command command, String parameter) {
		super();
		this.command = command;
		Parameter = parameter;
	}
	private Command command;
	private String Parameter;
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public String getParameter() {
		return Parameter;
	}
	public void setParameter(String parameter) {
		Parameter = parameter;
	}
}

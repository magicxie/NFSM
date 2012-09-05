package org.magic.ftp.command;

import java.util.LinkedHashMap;

import org.apache.mina.core.session.IoSession;
import org.magic.ftp.exception.CommandNotFoundException;
import org.magic.ftp.protocal.FTPResponse;

public abstract class Command {

	private static LinkedHashMap<String, Command> commandMapping = new LinkedHashMap<String, Command>();
	
	public static Command get(String command){
		
		Command cmd = commandMapping.get(command);
		if(cmd == null){
			
			try {
				
				cmd = (Command) Class.forName("org.magic.ftp.command.CMD_" + command).newInstance();
				commandMapping.put(command, cmd);
			
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				throw new CommandNotFoundException(command);
			}
		}
		if(cmd == null){
			throw new CommandNotFoundException(command);
		}
		
		return cmd;
		
	}
	
	public abstract FTPResponse execute(String parameter, IoSession session);
	
	public static final String CRLF = "\r\n";
	public static final String SP = " ";
	
	public static final int SC_110 = 110;//	新文件指示器上的重启标记
	public static final int SC_120 = 120;//	服务器准备就绪的时间（分钟数）
	public static final int SC_125 = 125;//	打开数据连接，开始传输
	public static final int SC_150 = 150;//	打开连接
	public static final int SC_200 = 200;//	成功
	public static final int SC_202 = 202;//	命令没有执行
	public static final int SC_211 = 211;//	系统状态回复
	public static final int SC_212 = 212;//	目录状态回复
	public static final int SC_213 = 213;//	文件状态回复
	public static final int SC_214 = 214;//	帮助信息回复
	public static final int SC_215 = 215;//	系统类型回复
	public static final int SC_220 = 220;//	服务就绪
	public static final int SC_221 = 221;//	退出网络
	public static final int SC_225 = 225;//	打开数据连接
	public static final int SC_226 = 226;//	结束数据连接
	public static final int SC_227 = 227;//	进入被动模式（IP 地址、ID 端口）
	public static final int SC_230 = 230;//	登录因特网
	public static final int SC_250 = 250;//	文件行为完成
	public static final int SC_257 = 257;//	路径名建立
	public static final int SC_331 = 331;//	要求密码
	public static final int SC_332 = 332;//	要求帐号
	public static final int SC_350 = 350;//	文件行为暂停
	public static final int SC_421 = 421;//	服务关闭
	public static final int SC_425 = 425;//	无法打开数据连接
	public static final int SC_426 = 426;//	结束连接
	public static final int SC_450 = 450;//	文件不可用
	public static final int SC_451 = 451;//	遇到本地错误
	public static final int SC_452 = 452;//	磁盘空间不足
	public static final int SC_500 = 500;//	无效命令
	public static final int SC_501 = 501;//	错误参数
	public static final int SC_502 = 502;//	命令没有执行
	public static final int SC_503 = 503;//	错误指令序列
	public static final int SC_504 = 504;//	无效命令参数
	public static final int SC_530 = 530;//	未登录网络
	public static final int SC_532 = 532;//	存储文件需要帐号
	public static final int SC_550 = 550;//	文件不可用
	public static final int SC_551 = 551;//	不知道的页类型
	public static final int SC_552 = 552;//	超过存储分配
	public static final int SC_553 = 553;//	文件名不允许
	
}

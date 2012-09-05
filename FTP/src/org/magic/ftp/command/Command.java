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
	
	public static final int SC_110 = 110;//	���ļ�ָʾ���ϵ��������
	public static final int SC_120 = 120;//	������׼��������ʱ�䣨��������
	public static final int SC_125 = 125;//	���������ӣ���ʼ����
	public static final int SC_150 = 150;//	������
	public static final int SC_200 = 200;//	�ɹ�
	public static final int SC_202 = 202;//	����û��ִ��
	public static final int SC_211 = 211;//	ϵͳ״̬�ظ�
	public static final int SC_212 = 212;//	Ŀ¼״̬�ظ�
	public static final int SC_213 = 213;//	�ļ�״̬�ظ�
	public static final int SC_214 = 214;//	������Ϣ�ظ�
	public static final int SC_215 = 215;//	ϵͳ���ͻظ�
	public static final int SC_220 = 220;//	�������
	public static final int SC_221 = 221;//	�˳�����
	public static final int SC_225 = 225;//	����������
	public static final int SC_226 = 226;//	������������
	public static final int SC_227 = 227;//	���뱻��ģʽ��IP ��ַ��ID �˿ڣ�
	public static final int SC_230 = 230;//	��¼������
	public static final int SC_250 = 250;//	�ļ���Ϊ���
	public static final int SC_257 = 257;//	·��������
	public static final int SC_331 = 331;//	Ҫ������
	public static final int SC_332 = 332;//	Ҫ���ʺ�
	public static final int SC_350 = 350;//	�ļ���Ϊ��ͣ
	public static final int SC_421 = 421;//	����ر�
	public static final int SC_425 = 425;//	�޷�����������
	public static final int SC_426 = 426;//	��������
	public static final int SC_450 = 450;//	�ļ�������
	public static final int SC_451 = 451;//	�������ش���
	public static final int SC_452 = 452;//	���̿ռ䲻��
	public static final int SC_500 = 500;//	��Ч����
	public static final int SC_501 = 501;//	�������
	public static final int SC_502 = 502;//	����û��ִ��
	public static final int SC_503 = 503;//	����ָ������
	public static final int SC_504 = 504;//	��Ч�������
	public static final int SC_530 = 530;//	δ��¼����
	public static final int SC_532 = 532;//	�洢�ļ���Ҫ�ʺ�
	public static final int SC_550 = 550;//	�ļ�������
	public static final int SC_551 = 551;//	��֪����ҳ����
	public static final int SC_552 = 552;//	�����洢����
	public static final int SC_553 = 553;//	�ļ���������
	
}

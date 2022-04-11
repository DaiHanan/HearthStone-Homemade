package view;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import config.Config;
import controller.Controller;

public class AboutView extends JDialog {
	private static final long serialVersionUID = 1L;
	
	ShadePane back = new ShadePane("˵��", Color.blue, Color.pink);//����
	JTabbedPane tabbedPane;//��ҳ�ؼ�
	JScrollPane scrollPane;//�����ؼ�
	JLabel aboutLabel;//���ܱ�ǩ
	JTextArea ruleText;//�����ı���
	
	public AboutView(Controller con) {
		this.setTitle("��Ϸ����  " + Config.title);
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, (int)(Config.mainWeight / 1.8), (int)(Config.mainHeight / 1.5));
		this.setLocationRelativeTo(con.mainV);
		this.setLayout(null);
		/**����*/
		String text = "<html><br/><br/><br/><br/>"
				+ "����ϷΪ�¡�¯ʯ��˵������Ϸ�������<br/>"
				+ "����Ϸ�淨�Ϳ������ԣ������Լ���ǳ��<br/>"
				+ "֪ʶ����������ϣ���ܻ���������顣<br/><br/><br/>"
				+ "�����ߣ������֡����Ҷ�<br/><br/><br/>"
				+ "��ϸ��Ϸ�淨��ת����һҳ<br/></html>";
		
		aboutLabel = new JLabel();
		aboutLabel.setFont(new Font("����", Font.BOLD, 20));
		aboutLabel.setForeground(Color.white);
		aboutLabel.setText(text);
		aboutLabel.setBounds(20, -100, this.getWidth(), this.getHeight());
		
		back.setBounds(0, 0, this.getWidth(), this.getHeight());
		back.setLayout(null);
		back.add(aboutLabel);
		/**����*/
		ruleText = new JTextArea();
		ruleText.setLineWrap(true);
		ruleText.setEditable(false);
		ruleText.setFont(new Font("����", Font.PLAIN, 15));
		
		//��src/config/rule.txt�ļ��ж�ȡ�������
		StringBuffer rule = new StringBuffer();
		FileReader reader = null;
		BufferedReader bReader = null;
		try {
			reader = new FileReader(new File("src/config/rule.txt"));
			bReader = new BufferedReader(reader);
			String str = null;
			while((str = bReader.readLine()) != null) {
				rule.append(str);
				rule.append("\r\n");
			}
		} catch (Exception e) {
			System.out.println("�ļ���ȡʧ��");
		} finally {
			try {
				bReader.close();
				reader.close();
			} catch (IOException e) {
				System.out.println("�ļ��ر�ʧ��");
			}
		}
//		
//		String rule = "1����Ϸ��ʼ�����ǰ���֣����һ���Ϸ���Ϊ���֣���Ҷ����·���Ϊ���֡�\r\n" + 
//				"����3�ſ��ơ�����4�ſ��ƣ��������������������еĿ��ơ�\r\n" + 
//				"����Ϸ�����У�ÿһ���غϻ������������һ�������ƿ��е�һ�š�\r\n" + 
//				"��һ�ֶ�ս�У��غ�Ϊ50�غ����ޣ������50�غ���û��һ��Ѫ��Ϊ0.��ô�ж�Ϊƽ�֣�\r\n" + 
//				"��ͬ���ڻغ����У���һ��Ѫ��Ϊ0�����ж�Ϊ�䡣\r\n" + 
//				"2������Ϸ��ʼʱ��˫���������з���ˮ������Ҫ��÷���ˮ������Ҫ���ж�λغϣ�\r\n" + 
//				"��ÿһ�غ�ϵͳ����ˮ��ֻ������1�㡣�ﵽ10��ʱΪ�����Ҳ����������\r\n" + 
//				"ÿһ�غϵķ���ˮ������������ʹ�ÿ��ƻ�Ӣ�ۼ��ܵķ���ֵ�Ķ��٣�����ʹ�õ�ǰ�غϷ���ֵ���µĿ���Ϊ׼��\r\n" + 
//				"ÿһ�غϿ�ʼʱ���ᷨ��ˮ�����Զ�����������ˮ���ڽ����ҷ���������ɫΪ��ʹ��ˮ����\r\n" + 
//				"3����Ϸ�������ӿ��������Ͽ������ñ��棬��ʵ����ÿһ��ʹ�ö����ܻ�������������ֵ��������ֵΪ0ʱ����������ʧ ��\r\n" + 
//				"��������ֻ��ʹ��һ�Σ�ʹ�ú󲻿��ûأ�����Ч����Ч����ʧ��\r\n" + 
//				"4����ӿ�����ֵ���ܣ�\r\n" + 
//				"1�����Ϸ�Ϊ��ӵ����ԣ��ɱ�ĳЩ��Ƭ����Ч��\r\n" + 
//				"Ŀǰ��Ϸ���������У�\r\n" + 
//				"��ͨ����ҡ�Ұ�ޡ�����\r\n" + 
//				"2����Ƭ���Ϸ�Ϊ����ս��������ɱ�ĳЩ��Ƭ��������Ч����\r\n" + 
//				"3����Ƭ���м�Ϊ������ƣ�\r\n" + 
//				"4����Ƭ���·�Ϊ��ӹ����������·�Ϊ�������ֵ������������໥����ʱ˫���Ĺ�������Ӱ��Է�������ֵ��\r\n" + 
//				"���е�һ������Ϊ��ң��������ֵС�ڵ��������Ϸֱ�ӽ�����\r\n" + 
//				"5����Ƭ���·�Ϊ���ԣ���ͬ�������в�ͬ�Ķ���Ч�������ж��\r\n" + 
//				"Ŀǰ��Ϸ���������У�\r\n" + 
//				"��ŭ_һ�غ��ܹ��������Ρ�\r\n" + 
//				"����_�Է������ȹ�����������ӡ�\r\n" + 
//				"�綾_�ܵ�����������˺��ķ�Ӣ�����������\r\n" + 
//				"���_�³����ɹ�����\r\n" + 
//				"ʥ��_�ֵ�һ���˺���\r\n" + 
//				"6���������ָ��Ƭ�����ƻ�Ӣ�ۼ���ʱ������ʾ������Ƭ�����кͿ�Ƭ��ͬ������������Ƶ���Ƭ���Ϸ���\r\n" + 
//				"��������Ƶ���Ƭ���Ϸ�������Ƭ�м����ʾ����ӣ���������Ч���������Ч���Ļ�����\r\n" + 
//				"5����ҵ�Ӣ�ۼ������û�п�ƬЧ���Ļ�ÿ�غ�ֻ��ʹ��һ�Ρ�\r\n" + 
//				"6����һ����Ҳ��������󣬵���غϽ�����ť�������Ȩ�����Է���ҡ�\r\n" + 
//				"7����ǰ���ֻ�ܲ����Լ��Ŀ�Ƭ�������Բ鿴�Է���ս����Ƭ��塣\r\n" + 
//				"8��������ƿ�Ϊ��֮���ٴγ鿨��ҵ�����ֵ���ܵ�һ����ƣ�ͳͷ�������ֵΪ��1��2��3...\r\n" + 
//				"9����һ��Ҫ���ؽ��б���Ϸ��������κβ�����رձ���Ϸ��";
		ruleText.setText(rule.toString());
		
		scrollPane = new JScrollPane(ruleText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//ƴ��
		tabbedPane = new JTabbedPane();
		this.setContentPane(tabbedPane);
		tabbedPane.add("����", back);
		tabbedPane.addTab("����", scrollPane);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new AboutView(new Controller());
	}

}


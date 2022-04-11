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
	
	ShadePane back = new ShadePane("说明", Color.blue, Color.pink);//背景
	JTabbedPane tabbedPane;//翻页控件
	JScrollPane scrollPane;//滚动控件
	JLabel aboutLabel;//介绍标签
	JTextArea ruleText;//规则文本框
	
	public AboutView(Controller con) {
		this.setTitle("游戏介绍  " + Config.title);
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, (int)(Config.mainWeight / 1.8), (int)(Config.mainHeight / 1.5));
		this.setLocationRelativeTo(con.mainV);
		this.setLayout(null);
		/**介绍*/
		String text = "<html><br/><br/><br/><br/>"
				+ "本游戏为仿“炉石传说”的游戏，借鉴了<br/>"
				+ "其游戏玩法和卡牌属性，利用自己的浅薄<br/>"
				+ "知识开发出来，希望能获得愉悦体验。<br/><br/><br/>"
				+ "开发者：李钰林、付家栋<br/><br/><br/>"
				+ "详细游戏玩法请转向下一页<br/></html>";
		
		aboutLabel = new JLabel();
		aboutLabel.setFont(new Font("黑体", Font.BOLD, 20));
		aboutLabel.setForeground(Color.white);
		aboutLabel.setText(text);
		aboutLabel.setBounds(20, -100, this.getWidth(), this.getHeight());
		
		back.setBounds(0, 0, this.getWidth(), this.getHeight());
		back.setLayout(null);
		back.add(aboutLabel);
		/**规则*/
		ruleText = new JTextArea();
		ruleText.setLineWrap(true);
		ruleText.setEditable(false);
		ruleText.setFont(new Font("宋体", Font.PLAIN, 15));
		
		//从src/config/rule.txt文件中读取规则介绍
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
			System.out.println("文件读取失败");
		} finally {
			try {
				bReader.close();
				reader.close();
			} catch (IOException e) {
				System.out.println("文件关闭失败");
			}
		}
//		
//		String rule = "1、游戏开始后分配前后手，玩家一（上方）为先手，玩家二（下方）为后手。\r\n" + 
//				"先手3张卡牌、后手4张卡牌，并且随机分配玩家套牌中的卡牌。\r\n" + 
//				"在游戏过程中，每一个回合机器人随机发放一张你套牌库中的一张。\r\n" + 
//				"在一局对战中，回合为50回合上限，如果在50回合中没有一方血量为0.那么判定为平局，\r\n" + 
//				"但同理在回合数中，由一方血量为0，则判定为输。\r\n" + 
//				"2、在游戏开始时，双方都不会有法力水晶，想要获得法力水晶就需要进行多次回合，\r\n" + 
//				"而每一回合系统法力水晶只会增加1点。达到10点时为最大。再也不会增长。\r\n" + 
//				"每一回合的法力水晶消耗在于你使用卡牌或英雄技能的法力值的多少，并且使用当前回合法力值以下的卡牌为准。\r\n" + 
//				"每一回合开始时都会法术水晶会自动充满。法力水晶在界面右方。（深蓝色为已使用水晶）\r\n" + 
//				"3、游戏过程里，随从卡牌理论上可以永久保存，但实际上每一次使用都可能会消耗它的生命值，当生命值为0时，卡牌则消失 。\r\n" + 
//				"而法术牌只能使用一次，使用后不可拿回，法术效果生效后消失。\r\n" + 
//				"4、随从卡面数值介绍：\r\n" + 
//				"1）正上方为随从的属性，可被某些卡片触发效果\r\n" + 
//				"目前游戏包括属性有：\r\n" + 
//				"普通、玩家、野兽、龙；\r\n" + 
//				"2）卡片左上方为所在战棋格数，可被某些卡片触发额外效果；\r\n" + 
//				"3）卡片正中间为随从名称；\r\n" + 
//				"4）卡片左下方为随从攻击力、右下方为随从生命值，当两个随从相互攻击时双方的攻击力会影响对方的生命值。\r\n" + 
//				"其中第一个格子为玩家，玩家生命值小于等于零后游戏直接结束。\r\n" + 
//				"5）卡片正下方为特性，不同的特性有不同的额外效果，可有多个\r\n" + 
//				"目前游戏包括特性有：\r\n" + 
//				"风怒_一回合能够攻击两次、\r\n" + 
//				"嘲讽_对方必须先攻击该特性随从、\r\n" + 
//				"剧毒_受到该特性随从伤害的非英雄随从死亡、\r\n" + 
//				"冲锋_下场即可攻击、\r\n" + 
//				"圣盾_抵挡一次伤害、\r\n" + 
//				"6）当用鼠标指向卡片、手牌或英雄技能时，会显示描述卡片，其中和卡片不同的是随从属性移到卡片左上方，\r\n" + 
//				"随从名称移到卡片正上方，而卡片中间会显示该随从（法术）的效果（如果有效果的话）。\r\n" + 
//				"5、玩家的英雄技能如果没有卡片效果的话每回合只能使用一次。\r\n" + 
//				"6、当一个玩家操作结束后，点击回合结束按钮会把掌握权交给对方玩家。\r\n" + 
//				"7、当前玩家只能操作自己的卡片，仅可以查看对方的战场卡片面板。\r\n" + 
//				"8、当玩家牌库为空之后，再次抽卡玩家的生命值会受到一定的疲劳惩罚，其数值为：1、2、3...\r\n" + 
//				"9、请一定要愉快地进行本游戏，如果有任何不适请关闭本游戏。";
		ruleText.setText(rule.toString());
		
		scrollPane = new JScrollPane(ruleText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//拼接
		tabbedPane = new JTabbedPane();
		this.setContentPane(tabbedPane);
		tabbedPane.add("介绍", back);
		tabbedPane.addTab("规则", scrollPane);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new AboutView(new Controller());
	}

}


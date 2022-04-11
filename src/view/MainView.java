package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import config.Config;
import model.IdBtn;
import model.card.attend.Attend;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JMenuBar menuBar;//菜单栏
	public JMenu menu;//菜单项
	public JMenuItem chooseMI;//选择选项
	public JMenuItem aboutMI;//说明选项
	
//	public AboutView aboutView = new AboutView(this);//说明窗口
//	public ChooseView chooseView = new ChooseView(this);//设置窗口
	
	public JPanel forePanel;//前景面板
	public JPanel backPanel;//背景面板
	public JPanel[] backPanels;//双方的背景(颜色)面板
	
	public JLabel ruleStr;//中心线
	public JLabel handStr;//手牌区域划分线
	public JButton turnChange;//回合结束按钮
	public IdBtn[] skillBtns = new IdBtn[2];//玩家英雄技能
	
	public JLabel[] libLables = new JLabel[2];//玩家剩余手牌显示标签
	
	public CostPane[][][] costs = new CostPane[2][Config.costMax][2];//玩家水晶数组, 0-未用1-已用
	
	public StandCard[][] stands = new StandCard[2][Config.standMaxCount + 1];//玩家站场随从卡片数组
//	public LinkedList<StandCard> stand_1;//玩家一站场随从卡片
//	public LinkedList<StandCard> stand_2;//玩家二站场随从卡片
	
	public LinkedList<LinkedList<IdBtn> > hands = new LinkedList<>();//玩家手牌按钮数组
//	public LinkedList<JButton> hand_1;//玩家一手牌按钮
//	public LinkedList<JButton> han2_2;//玩家二手牌按钮
	
	public DescPane[] descPanes;//玩家的描述面板数组(2个)
	
	public MainView() {
		//避免菜单被其他重量级组件遮挡
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		//初始化菜单
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menu = new JMenu("菜单");
		menuBar.add(menu);
		
		chooseMI = new JMenuItem("设置(A)");
		chooseMI.setMnemonic('A');
		//chooseMI.setAccelerator(KeyStroke.getKeyStroke('A'));
		aboutMI = new JMenuItem("关于(B)");
		aboutMI.setMnemonic('B');
		//aboutMI.setAccelerator(KeyStroke.getKeyStroke('B'));
		menu.add(chooseMI);
		menu.add(aboutMI);
		
		//初始化前景面板
		forePanel = new JPanel(null);
		forePanel.setBounds(0, 0, Config.mainWeight, Config.mainHeight);
		forePanel.setOpaque(false);
		//初始化背景面板
		backPanel = new JPanel(null);
		backPanel.setBackground(Color.cyan);
		backPanel.setOpaque(false);
		backPanel.setBounds(0, 0, Config.mainWeight, Config.mainHeight);
		//设置前背景面板
		this.getLayeredPane().add(backPanel, JLayeredPane.FRAME_CONTENT_LAYER);//最下层
		this.getLayeredPane().add(forePanel, JLayeredPane.DRAG_LAYER);//最上层
		//this.getLayeredPane().moveToBack(backPanel);
		//初始化双方背景面板
		backPanels = new JPanel[2];
		for(int i = 0; i < 2; i++) {
			backPanels[i] = new JPanel();
			backPanels[i].setBackground(Config.mainColor);
			this.getContentPane().add(backPanels[i]);
		}
		backPanels[0].setBounds(0, 0, 
				Config.mainWeight - Config.nowTurnBorderWeight, (int)(Config.ruleStrY / 1.09));
		backPanels[1].setBounds(0, (int)(Config.ruleStrY / 1.08), 
				Config.mainWeight - Config.nowTurnBorderWeight, 
				Config.mainHeight - (int)(Config.ruleStrY / 0.99) - 7 * Config.nowTurnBorderWeight);
		//开始回合边框
		backPanels[0].setBorder(Config.nowTurnBorder);
		
//		初始化站场随从数组
//		stand_1 = new LinkedList<>();
//		stand_2 = new LinkedList<>();
//		stands.add(new LinkedList<StandCard>());
//		stands.add(new LinkedList<StandCard>());
		//初始化手牌按钮数组
		hands.add(new LinkedList<IdBtn>());
		hands.add(new LinkedList<IdBtn>());
		//设置主界面基础属性
		this.setTitle(Config.title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, Config.mainWeight, Config.mainHeight);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		//设置主界面背景
		this.getContentPane().setBackground(Config.mainColor);
		//绘制中心线
		ruleStr = new JLabel();
		ruleStr.setBounds(0, Config.ruleStrY, Config.ruleStrLen, 1);
		ruleStr.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		forePanel.add(ruleStr);
		//绘制手牌区分割线
		handStr = new JLabel();
		handStr.setBounds(Config.handStrX, 0, 1, Config.mainHeight);
		handStr.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		forePanel.add(handStr);
		//设置回合按钮
		turnChange = new JButton("回合结束");
		turnChange.setBounds(Config.turnChangeX, Config.turnChangeY, Config.btnWeight, Config.btnHeight);
		turnChange.setFont(Config.turnFont);
		turnChange.setForeground(Config.turnForeColor);
		turnChange.setBackground(Config.turnBackColor);
		turnChange.setMargin(new Insets(Config.turnInterval, Config.turnInterval, 
				Config.turnInterval, Config.turnInterval));
		
		forePanel.add(turnChange);
		//设置英雄技能按钮
		skillBtns[0] = new IdBtn(0, "英雄技能1");
		skillBtns[1] = new IdBtn(1, "英雄技能2");
		for(int i = 0; i < 2; i++) {
			skillBtns[i].setBounds(Config.skillX[i], Config.skillY[i], Config.skillWeight, Config.skillHeight);
			skillBtns[i].setFont(Config.btnFont);
			skillBtns[i].setForeground(Config.btnForeColor);
			skillBtns[i].setBackground(Config.btnBackColor);
			skillBtns[i].setMargin(new Insets(Config.btnInterval, Config.btnInterval, 
					Config.btnInterval, Config.btnInterval));
		}
		
		forePanel.add(skillBtns[0]);
		forePanel.add(skillBtns[1]);
		//显示剩余牌数
		libLables[0] = new JLabel("剩余卡牌:30");
		libLables[1] = new JLabel("剩余卡牌:30");
		libLables[0].setBounds(Config.libX[0], Config.libY[0], Config.libWeight, Config.libHeight);
		libLables[1].setBounds(Config.libX[1], Config.libY[1], Config.libWeight, Config.libHeight);
		libLables[0].setFont(Config.libFont);
		libLables[1].setFont(Config.libFont);
		libLables[0].setForeground(Config.libColor);
		libLables[1].setForeground(Config.libColor);
		
		forePanel.add(libLables[0]);
		forePanel.add(libLables[1]);
		//初始化水晶数组
		costs[0] = new CostPane[Config.costMax][2];//玩家一水晶数组(0-未用水晶和1-已用水晶)
		costs[1] = new CostPane[Config.costMax][2];//玩家二水晶数组(0-未用水晶和1-已用水晶)
		//绘制水晶
		//开始都为已用水晶
		for(int i = 0; i < Config.costMax; i++) {
			//传入参数为水晶坐标
			//玩家一
			costs[0][i][0] = new CostNew(Config.costX[0],
					Config.costY[0] + i * (Config.costDiameter+ Config.costInterval));
			costs[0][i][1] = new CostUsed(Config.costX[0],
					Config.costY[0] + i * (Config.costDiameter+ Config.costInterval));
			//玩家二
			costs[1][i][0] = new CostNew(Config.costX[1],
					Config.costY[1] + i * (Config.costDiameter + Config.costInterval));
			costs[1][i][1] = new CostUsed(Config.costX[1],
					Config.costY[1] + i * (Config.costDiameter + Config.costInterval));
			//添加到主界面
			forePanel.add(costs[0][i][0]);
			forePanel.add(costs[0][i][1]);
			forePanel.add(costs[1][i][0]);
			forePanel.add(costs[1][i][1]);
			//开局水晶不显示
			costs[0][i][0].setVisible(false);
			costs[1][i][0].setVisible(false);
			costs[0][i][1].setVisible(false);
			costs[1][i][1].setVisible(false);
		}
		//初始化描述面板
		descPanes = new DescPane[2];
		descPanes[0] = new DescPane(new Attend(), Config.handInitX[0], Config.handInitY[0]);
		descPanes[1] = new DescPane(new Attend(), Config.handInitX[1], Config.handInitY[1]);
		forePanel.add(descPanes[0]);
		forePanel.add(descPanes[1]);
//		//设置玩家血条显示
//		JLabel heal_1 = new JLabel("" + player[0].hp);
//		JLabel heal_2 = new JLabel("" + player[1].hp);
//		heal_1.setBounds(c.heal_1_X, c.heal_1_Y, c.btnWeight, c.btnHeight);
//		heal_2.setBounds(c.heal_2_X, c.heal_2_Y, c.btnWeight, c.btnHeight);
//		heal_1.setForeground(Color.red);
//		heal_2.setForeground(Color.red);
//		Font headF = new Font("黑体", Font.BOLD, 20);
//		heal_1.setFont(headF);
//		heal_2.setFont(headF);
		
//		this.add(heal_1);
//		this.add(heal_2);
//		//手牌显示
//		JButton card1 = new JButton("富家栋");
//		JButton card2 = new JButton("穷家栋");
//		card1.setBounds(Config.cardInit_1_X, Config.cardInit_1_Y + (Config.cardHeight + Config.cardIntervalY) * 4, 
//				Config.cardWeight, Config.cardHeight);
//		card2.setBounds(Config.cardInit_2_X + Config.cardIntervalX + Config.cardWeight, Config.cardInit_2_Y, Config.cardWeight, Config.cardHeight);
//		card1.setEnabled(false);
//		
//		this.add(card1);
//		this.add(card2);
//		//随从显示
//		JButton attend1 = new JButton("付减栋");
//		JButton attend2 = new JButton("穷乘栋");
//		attend1.setBounds(Config.attendInit_1_X, Config.attendInit_1_Y, Config.attendWeight, Config.attendHeight);
//		attend2.setBounds(Config.attendInit_2_X, Config.attendInit_2_Y, Config.attendWeight, Config.attendHeight);
//		
//		
//		this.add(attend1);
//		this.add(attend2);
		
		//设置主界面可见
		this.setVisible(true);
	}
	
	//更新站场模板，并返回随从是否死亡
	public boolean setStandCard(Attend attend, int idx, int id) {
		//获得操作数组
		StandCard[] stand = stands[id];
		//如果只是修改原卡片信息
		if(stand[idx] != null) {
			if(attend.hp <= 0) {//卡片死亡
				
				stand[idx].setVisible(false);
				stand[idx] = null;
				return true;
			}
			else
				stand[idx].setAttend(attend);
		}
		//增加卡片
		else {
			//设置该卡片坐标
			int x = Config.attendInitX[id] + idx % 4 * (Config.attendIntervalX + Config.attendWeight);
			int y = Config.attendInitY[id] + idx / 4 * (Config.attendIntervalY +Config.attendHeight);
			//创建卡片
			StandCard temp = new StandCard(id, attend, x, y, idx);
			//消除旧卡片
			if(stand[idx] != null)
				stand[idx].setVisible(false);
			//更新到数组
			stand[idx] = temp;
			//显示新卡片
			forePanel.add(temp);
		}
		
		return false;
	}
	
//	//更新手牌按钮(暂未添加监听器)
//	public JFrame setHandCard(Attend attend, int idx, int id) {
//		//获得操作数组
//		LinkedList<JButton> hand = hands.get(id);
//		if(hand.size() == idx) {//新增
//			//设置该按钮坐标
//			int x = Config.handInitX[id] + idx % 2 * (Config.handIntervalX + Config.handWeight);
//			int y = Config.handInitY[id] + idx / 2 * (Config.handIntervalY + Config.handHeight);
//			//创建新按钮
//			JButton btn = new JButton(attend.name);
//			btn.setBounds(x, y, Config.handWeight, Config.handHeight);
//			btn.setBackground(Config.btnBackColor);
//			btn.setForeground(Config.turnForeColor);
//			btn.setFont(Config.btnFont);
//			btn.setMargin(new Insets(Config.btnInterval, Config.btnInterval, 
//					Config.btnInterval, Config.btnInterval));
//			//添加按钮
//			this.add(btn);
//			//立即显示按钮
//			btn.repaint();
//			//添加数组
//			hand.add(btn);
//		}else {//修改
//			JButton btn = hand.get(idx);
//			btn.setName(attend.name);
//			//立即显示按钮
//			btn.repaint();
//		}
//		
//		return this;
//	}
	//添加手牌按钮
	public JFrame addHandCard(int id) {
		//获得操作数组
		LinkedList<IdBtn> hand = hands.get(id);
		//获得当前按钮的数量
		int size = hand.size();
		if(size < Config.handMaxCount) {
			//设置该按钮坐标
			int x = Config.handInitX[id] + size % 2 * (Config.handIntervalX + Config.handWeight);
			int y = Config.handInitY[id] + size / 2 * (Config.handIntervalY + Config.handHeight);
			//创建新按钮
			IdBtn btn = new IdBtn(id, "" + (size + 1));
			btn.setBounds(x, y, Config.handWeight, Config.handHeight);
			btn.setBackground(Config.btnBackColor);
			btn.setForeground(Config.turnForeColor);
			btn.setFont(Config.btnFont);
			btn.setMargin(new Insets(Config.btnInterval, Config.btnInterval, 
					Config.btnInterval, Config.btnInterval));
			//显示按钮
			forePanel.add(btn);
			//立即显示按钮
			btn.repaint();
			//添加按钮到数组
			hand.add(btn);
		}
		
		return this;
	}
	//删除手牌
	public void delHandCard(int id, int count) {
		if((id == 0 || id == 1) && count > 0 && count <= this.hands.get(id).size()) {
			for(int i = 0; i < count; i++) {
				//获得删除按钮
				IdBtn btn = this.hands.get(id).getLast();
				//删除按钮
				this.hands.get(id).removeLast();
				//取消显示
				btn.setVisible(false);
			}
		}
	}
	
	//更新水晶显示,type为水晶类型：0-未用、1-已用、2-未显示
	public void setCostPane(int id, int idx, int type) {
		if((id == 0 || id == 1) && idx >= 0 && idx < Config.costMax) {
			if(type == 0 || type == 1) {
				costs[id][idx][type].setVisible(true);
				costs[id][idx][1 - type].setVisible(false);
				
				//costs[id][idx][type].repaint();
			}
			else {
				costs[id][idx][0].setVisible(false);
				costs[id][idx][1].setVisible(false);
			}
		}
	}
	//批量更新水晶显示
	public void setCostPanes(int id, int news, int count) {
		if((id == 0 || id == 1) && news >= 0 && count <= Config.costMax) {
			int i = 0;
			//未用水晶
			for(; i < news; i++) {
				costs[id][i][0].setVisible(true);
				costs[id][i][1].setVisible(false);
				//costs[id][i][0].repaint();
			}
			//已用水晶
			for(; i < count; i++) {
				costs[id][i][1].setVisible(true);
				costs[id][i][0].setVisible(false);
				//costs[id][i][1].repaint();
			}
			//未显示水晶
			for(; i < Config.costMax; i++) {
				costs[id][i][1].setVisible(false);
				costs[id][i][0].setVisible(false);
			}
		}
	}
	//设置牌库剩余牌数
	public void setLibLable(int id, int count) {
		if((id == 0 || id == 1) && count >= 0) {
			this.libLables[id].setText("剩余卡牌:" + count);
		}
	}
	
	//重新开始游戏
	public void regame() {
		for(int i = 0; i < 2; i++) {
			//重置玩家英雄技能监听器
			this.remove(skillBtns[i]);
			skillBtns[i] = new IdBtn(i, "英雄技能" + i);
			this.add(skillBtns[i]);
			//重置剩余手牌显示标签
			libLables[i].setText("剩余卡牌:30");
			//重置玩家水晶
			this.setCostPanes(i, 0, 0);
			//重置玩家站场随从卡片数组
			for(int j = 0; j < Config.standMaxCount + 1; j++) {
				if(this.stands[i][j] != null) {
					//删除视图
					this.forePanel.remove(this.stands[i][j]);
					//删除数据
					this.stands[i][j] = null;
				}
			}
			//重置玩家手牌按钮数组
			//删除视图
			for(int j = 0; j < hands.get(i).size(); j++) {
				this.forePanel.remove(hands.get(i).get(j));
			}
			//删除数据
			hands.get(i).clear();
			
			//刷新视图
			this.validate();
		}
	}
	
	public static void main(String[] args) {
		new MainView();
	}
}

//水晶模板基类
class CostPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//private int x, y;//坐标
	public CostPane(int x, int y) {
		this.setBounds(x, y, Config.costDiameter, Config.costDiameter);
		//设置背景颜色(和主界面背景一样)
		this.setBackground(Config.mainColor);
	}
}
//已用水晶模板
class CostUsed extends CostPane {
	private static final long serialVersionUID = 1L;
	
	public CostUsed(int x, int y) {
		super(x, y);
	}
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D)g1;
		//设置“画笔”
		GradientPaint paint = new GradientPaint(0, 0, Color.blue, 
				Config.costDiameter, Config.costDiameter, Color.black);
		g.setPaint(paint);
		//设置抗锯齿开
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//绘制已使用的水晶
		g.fillOval(0, 0, Config.costDiameter, Config.costDiameter);
	}
}
//未用水晶模板
class CostNew extends CostPane {
	private static final long serialVersionUID = 1L;
	
	public CostNew(int x, int y) {
		super(x, y);
	}
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D)g1;
		//设置“画笔”
		GradientPaint paint = new GradientPaint(Config.costDiameter, 0, Color.white, 
				0, Config.costDiameter, Color.blue);
		g.setPaint(paint);
		//设置抗锯齿开
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//绘制已使用的水晶
		g.fillOval(0, 0, Config.costDiameter, Config.costDiameter);
	}
}


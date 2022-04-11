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
	
	public JMenuBar menuBar;//�˵���
	public JMenu menu;//�˵���
	public JMenuItem chooseMI;//ѡ��ѡ��
	public JMenuItem aboutMI;//˵��ѡ��
	
//	public AboutView aboutView = new AboutView(this);//˵������
//	public ChooseView chooseView = new ChooseView(this);//���ô���
	
	public JPanel forePanel;//ǰ�����
	public JPanel backPanel;//�������
	public JPanel[] backPanels;//˫���ı���(��ɫ)���
	
	public JLabel ruleStr;//������
	public JLabel handStr;//�������򻮷���
	public JButton turnChange;//�غϽ�����ť
	public IdBtn[] skillBtns = new IdBtn[2];//���Ӣ�ۼ���
	
	public JLabel[] libLables = new JLabel[2];//���ʣ��������ʾ��ǩ
	
	public CostPane[][][] costs = new CostPane[2][Config.costMax][2];//���ˮ������, 0-δ��1-����
	
	public StandCard[][] stands = new StandCard[2][Config.standMaxCount + 1];//���վ����ӿ�Ƭ����
//	public LinkedList<StandCard> stand_1;//���һվ����ӿ�Ƭ
//	public LinkedList<StandCard> stand_2;//��Ҷ�վ����ӿ�Ƭ
	
	public LinkedList<LinkedList<IdBtn> > hands = new LinkedList<>();//������ư�ť����
//	public LinkedList<JButton> hand_1;//���һ���ư�ť
//	public LinkedList<JButton> han2_2;//��Ҷ����ư�ť
	
	public DescPane[] descPanes;//��ҵ������������(2��)
	
	public MainView() {
		//����˵�����������������ڵ�
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		//��ʼ���˵�
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menu = new JMenu("�˵�");
		menuBar.add(menu);
		
		chooseMI = new JMenuItem("����(A)");
		chooseMI.setMnemonic('A');
		//chooseMI.setAccelerator(KeyStroke.getKeyStroke('A'));
		aboutMI = new JMenuItem("����(B)");
		aboutMI.setMnemonic('B');
		//aboutMI.setAccelerator(KeyStroke.getKeyStroke('B'));
		menu.add(chooseMI);
		menu.add(aboutMI);
		
		//��ʼ��ǰ�����
		forePanel = new JPanel(null);
		forePanel.setBounds(0, 0, Config.mainWeight, Config.mainHeight);
		forePanel.setOpaque(false);
		//��ʼ���������
		backPanel = new JPanel(null);
		backPanel.setBackground(Color.cyan);
		backPanel.setOpaque(false);
		backPanel.setBounds(0, 0, Config.mainWeight, Config.mainHeight);
		//����ǰ�������
		this.getLayeredPane().add(backPanel, JLayeredPane.FRAME_CONTENT_LAYER);//���²�
		this.getLayeredPane().add(forePanel, JLayeredPane.DRAG_LAYER);//���ϲ�
		//this.getLayeredPane().moveToBack(backPanel);
		//��ʼ��˫���������
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
		//��ʼ�غϱ߿�
		backPanels[0].setBorder(Config.nowTurnBorder);
		
//		��ʼ��վ���������
//		stand_1 = new LinkedList<>();
//		stand_2 = new LinkedList<>();
//		stands.add(new LinkedList<StandCard>());
//		stands.add(new LinkedList<StandCard>());
		//��ʼ�����ư�ť����
		hands.add(new LinkedList<IdBtn>());
		hands.add(new LinkedList<IdBtn>());
		//�����������������
		this.setTitle(Config.title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, Config.mainWeight, Config.mainHeight);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		//���������汳��
		this.getContentPane().setBackground(Config.mainColor);
		//����������
		ruleStr = new JLabel();
		ruleStr.setBounds(0, Config.ruleStrY, Config.ruleStrLen, 1);
		ruleStr.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		forePanel.add(ruleStr);
		//�����������ָ���
		handStr = new JLabel();
		handStr.setBounds(Config.handStrX, 0, 1, Config.mainHeight);
		handStr.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		forePanel.add(handStr);
		//���ûغϰ�ť
		turnChange = new JButton("�غϽ���");
		turnChange.setBounds(Config.turnChangeX, Config.turnChangeY, Config.btnWeight, Config.btnHeight);
		turnChange.setFont(Config.turnFont);
		turnChange.setForeground(Config.turnForeColor);
		turnChange.setBackground(Config.turnBackColor);
		turnChange.setMargin(new Insets(Config.turnInterval, Config.turnInterval, 
				Config.turnInterval, Config.turnInterval));
		
		forePanel.add(turnChange);
		//����Ӣ�ۼ��ܰ�ť
		skillBtns[0] = new IdBtn(0, "Ӣ�ۼ���1");
		skillBtns[1] = new IdBtn(1, "Ӣ�ۼ���2");
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
		//��ʾʣ������
		libLables[0] = new JLabel("ʣ�࿨��:30");
		libLables[1] = new JLabel("ʣ�࿨��:30");
		libLables[0].setBounds(Config.libX[0], Config.libY[0], Config.libWeight, Config.libHeight);
		libLables[1].setBounds(Config.libX[1], Config.libY[1], Config.libWeight, Config.libHeight);
		libLables[0].setFont(Config.libFont);
		libLables[1].setFont(Config.libFont);
		libLables[0].setForeground(Config.libColor);
		libLables[1].setForeground(Config.libColor);
		
		forePanel.add(libLables[0]);
		forePanel.add(libLables[1]);
		//��ʼ��ˮ������
		costs[0] = new CostPane[Config.costMax][2];//���һˮ������(0-δ��ˮ����1-����ˮ��)
		costs[1] = new CostPane[Config.costMax][2];//��Ҷ�ˮ������(0-δ��ˮ����1-����ˮ��)
		//����ˮ��
		//��ʼ��Ϊ����ˮ��
		for(int i = 0; i < Config.costMax; i++) {
			//�������Ϊˮ������
			//���һ
			costs[0][i][0] = new CostNew(Config.costX[0],
					Config.costY[0] + i * (Config.costDiameter+ Config.costInterval));
			costs[0][i][1] = new CostUsed(Config.costX[0],
					Config.costY[0] + i * (Config.costDiameter+ Config.costInterval));
			//��Ҷ�
			costs[1][i][0] = new CostNew(Config.costX[1],
					Config.costY[1] + i * (Config.costDiameter + Config.costInterval));
			costs[1][i][1] = new CostUsed(Config.costX[1],
					Config.costY[1] + i * (Config.costDiameter + Config.costInterval));
			//��ӵ�������
			forePanel.add(costs[0][i][0]);
			forePanel.add(costs[0][i][1]);
			forePanel.add(costs[1][i][0]);
			forePanel.add(costs[1][i][1]);
			//����ˮ������ʾ
			costs[0][i][0].setVisible(false);
			costs[1][i][0].setVisible(false);
			costs[0][i][1].setVisible(false);
			costs[1][i][1].setVisible(false);
		}
		//��ʼ���������
		descPanes = new DescPane[2];
		descPanes[0] = new DescPane(new Attend(), Config.handInitX[0], Config.handInitY[0]);
		descPanes[1] = new DescPane(new Attend(), Config.handInitX[1], Config.handInitY[1]);
		forePanel.add(descPanes[0]);
		forePanel.add(descPanes[1]);
//		//�������Ѫ����ʾ
//		JLabel heal_1 = new JLabel("" + player[0].hp);
//		JLabel heal_2 = new JLabel("" + player[1].hp);
//		heal_1.setBounds(c.heal_1_X, c.heal_1_Y, c.btnWeight, c.btnHeight);
//		heal_2.setBounds(c.heal_2_X, c.heal_2_Y, c.btnWeight, c.btnHeight);
//		heal_1.setForeground(Color.red);
//		heal_2.setForeground(Color.red);
//		Font headF = new Font("����", Font.BOLD, 20);
//		heal_1.setFont(headF);
//		heal_2.setFont(headF);
		
//		this.add(heal_1);
//		this.add(heal_2);
//		//������ʾ
//		JButton card1 = new JButton("���Ҷ�");
//		JButton card2 = new JButton("��Ҷ�");
//		card1.setBounds(Config.cardInit_1_X, Config.cardInit_1_Y + (Config.cardHeight + Config.cardIntervalY) * 4, 
//				Config.cardWeight, Config.cardHeight);
//		card2.setBounds(Config.cardInit_2_X + Config.cardIntervalX + Config.cardWeight, Config.cardInit_2_Y, Config.cardWeight, Config.cardHeight);
//		card1.setEnabled(false);
//		
//		this.add(card1);
//		this.add(card2);
//		//�����ʾ
//		JButton attend1 = new JButton("������");
//		JButton attend2 = new JButton("��˶�");
//		attend1.setBounds(Config.attendInit_1_X, Config.attendInit_1_Y, Config.attendWeight, Config.attendHeight);
//		attend2.setBounds(Config.attendInit_2_X, Config.attendInit_2_Y, Config.attendWeight, Config.attendHeight);
//		
//		
//		this.add(attend1);
//		this.add(attend2);
		
		//����������ɼ�
		this.setVisible(true);
	}
	
	//����վ��ģ�壬����������Ƿ�����
	public boolean setStandCard(Attend attend, int idx, int id) {
		//��ò�������
		StandCard[] stand = stands[id];
		//���ֻ���޸�ԭ��Ƭ��Ϣ
		if(stand[idx] != null) {
			if(attend.hp <= 0) {//��Ƭ����
				
				stand[idx].setVisible(false);
				stand[idx] = null;
				return true;
			}
			else
				stand[idx].setAttend(attend);
		}
		//���ӿ�Ƭ
		else {
			//���øÿ�Ƭ����
			int x = Config.attendInitX[id] + idx % 4 * (Config.attendIntervalX + Config.attendWeight);
			int y = Config.attendInitY[id] + idx / 4 * (Config.attendIntervalY +Config.attendHeight);
			//������Ƭ
			StandCard temp = new StandCard(id, attend, x, y, idx);
			//�����ɿ�Ƭ
			if(stand[idx] != null)
				stand[idx].setVisible(false);
			//���µ�����
			stand[idx] = temp;
			//��ʾ�¿�Ƭ
			forePanel.add(temp);
		}
		
		return false;
	}
	
//	//�������ư�ť(��δ��Ӽ�����)
//	public JFrame setHandCard(Attend attend, int idx, int id) {
//		//��ò�������
//		LinkedList<JButton> hand = hands.get(id);
//		if(hand.size() == idx) {//����
//			//���øð�ť����
//			int x = Config.handInitX[id] + idx % 2 * (Config.handIntervalX + Config.handWeight);
//			int y = Config.handInitY[id] + idx / 2 * (Config.handIntervalY + Config.handHeight);
//			//�����°�ť
//			JButton btn = new JButton(attend.name);
//			btn.setBounds(x, y, Config.handWeight, Config.handHeight);
//			btn.setBackground(Config.btnBackColor);
//			btn.setForeground(Config.turnForeColor);
//			btn.setFont(Config.btnFont);
//			btn.setMargin(new Insets(Config.btnInterval, Config.btnInterval, 
//					Config.btnInterval, Config.btnInterval));
//			//��Ӱ�ť
//			this.add(btn);
//			//������ʾ��ť
//			btn.repaint();
//			//�������
//			hand.add(btn);
//		}else {//�޸�
//			JButton btn = hand.get(idx);
//			btn.setName(attend.name);
//			//������ʾ��ť
//			btn.repaint();
//		}
//		
//		return this;
//	}
	//������ư�ť
	public JFrame addHandCard(int id) {
		//��ò�������
		LinkedList<IdBtn> hand = hands.get(id);
		//��õ�ǰ��ť������
		int size = hand.size();
		if(size < Config.handMaxCount) {
			//���øð�ť����
			int x = Config.handInitX[id] + size % 2 * (Config.handIntervalX + Config.handWeight);
			int y = Config.handInitY[id] + size / 2 * (Config.handIntervalY + Config.handHeight);
			//�����°�ť
			IdBtn btn = new IdBtn(id, "" + (size + 1));
			btn.setBounds(x, y, Config.handWeight, Config.handHeight);
			btn.setBackground(Config.btnBackColor);
			btn.setForeground(Config.turnForeColor);
			btn.setFont(Config.btnFont);
			btn.setMargin(new Insets(Config.btnInterval, Config.btnInterval, 
					Config.btnInterval, Config.btnInterval));
			//��ʾ��ť
			forePanel.add(btn);
			//������ʾ��ť
			btn.repaint();
			//��Ӱ�ť������
			hand.add(btn);
		}
		
		return this;
	}
	//ɾ������
	public void delHandCard(int id, int count) {
		if((id == 0 || id == 1) && count > 0 && count <= this.hands.get(id).size()) {
			for(int i = 0; i < count; i++) {
				//���ɾ����ť
				IdBtn btn = this.hands.get(id).getLast();
				//ɾ����ť
				this.hands.get(id).removeLast();
				//ȡ����ʾ
				btn.setVisible(false);
			}
		}
	}
	
	//����ˮ����ʾ,typeΪˮ�����ͣ�0-δ�á�1-���á�2-δ��ʾ
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
	//��������ˮ����ʾ
	public void setCostPanes(int id, int news, int count) {
		if((id == 0 || id == 1) && news >= 0 && count <= Config.costMax) {
			int i = 0;
			//δ��ˮ��
			for(; i < news; i++) {
				costs[id][i][0].setVisible(true);
				costs[id][i][1].setVisible(false);
				//costs[id][i][0].repaint();
			}
			//����ˮ��
			for(; i < count; i++) {
				costs[id][i][1].setVisible(true);
				costs[id][i][0].setVisible(false);
				//costs[id][i][1].repaint();
			}
			//δ��ʾˮ��
			for(; i < Config.costMax; i++) {
				costs[id][i][1].setVisible(false);
				costs[id][i][0].setVisible(false);
			}
		}
	}
	//�����ƿ�ʣ������
	public void setLibLable(int id, int count) {
		if((id == 0 || id == 1) && count >= 0) {
			this.libLables[id].setText("ʣ�࿨��:" + count);
		}
	}
	
	//���¿�ʼ��Ϸ
	public void regame() {
		for(int i = 0; i < 2; i++) {
			//�������Ӣ�ۼ��ܼ�����
			this.remove(skillBtns[i]);
			skillBtns[i] = new IdBtn(i, "Ӣ�ۼ���" + i);
			this.add(skillBtns[i]);
			//����ʣ��������ʾ��ǩ
			libLables[i].setText("ʣ�࿨��:30");
			//�������ˮ��
			this.setCostPanes(i, 0, 0);
			//�������վ����ӿ�Ƭ����
			for(int j = 0; j < Config.standMaxCount + 1; j++) {
				if(this.stands[i][j] != null) {
					//ɾ����ͼ
					this.forePanel.remove(this.stands[i][j]);
					//ɾ������
					this.stands[i][j] = null;
				}
			}
			//����������ư�ť����
			//ɾ����ͼ
			for(int j = 0; j < hands.get(i).size(); j++) {
				this.forePanel.remove(hands.get(i).get(j));
			}
			//ɾ������
			hands.get(i).clear();
			
			//ˢ����ͼ
			this.validate();
		}
	}
	
	public static void main(String[] args) {
		new MainView();
	}
}

//ˮ��ģ�����
class CostPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//private int x, y;//����
	public CostPane(int x, int y) {
		this.setBounds(x, y, Config.costDiameter, Config.costDiameter);
		//���ñ�����ɫ(�������汳��һ��)
		this.setBackground(Config.mainColor);
	}
}
//����ˮ��ģ��
class CostUsed extends CostPane {
	private static final long serialVersionUID = 1L;
	
	public CostUsed(int x, int y) {
		super(x, y);
	}
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D)g1;
		//���á����ʡ�
		GradientPaint paint = new GradientPaint(0, 0, Color.blue, 
				Config.costDiameter, Config.costDiameter, Color.black);
		g.setPaint(paint);
		//���ÿ���ݿ�
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//������ʹ�õ�ˮ��
		g.fillOval(0, 0, Config.costDiameter, Config.costDiameter);
	}
}
//δ��ˮ��ģ��
class CostNew extends CostPane {
	private static final long serialVersionUID = 1L;
	
	public CostNew(int x, int y) {
		super(x, y);
	}
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D)g1;
		//���á����ʡ�
		GradientPaint paint = new GradientPaint(Config.costDiameter, 0, Color.white, 
				0, Config.costDiameter, Color.blue);
		g.setPaint(paint);
		//���ÿ���ݿ�
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//������ʹ�õ�ˮ��
		g.fillOval(0, 0, Config.costDiameter, Config.costDiameter);
	}
}


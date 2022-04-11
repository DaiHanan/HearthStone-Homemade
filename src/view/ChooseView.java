package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import config.Config;
import config.Deck;
import controller.Controller;

public class ChooseView extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	//控制器
	Controller con;
	
	//背景
	public ShadePane back = new ShadePane("套卡选择", Color.white, Color.gray);
	//暂三套卡
	public JLabel[] playerLabels = new JLabel[2];//玩家昵称
	public JLabel[] deckLabels = new JLabel[3];//卡组名称
	public JLabel[] deckPtos = new JLabel[3];//卡组图片
	public JRadioButton[][] chooseBtns = new JRadioButton[2][3];//单选框
	//选项
	public JButton sureBtn = new JButton("确定");
	public JButton noBtn = new JButton("取消");
	//字体颜色
	public Font font = new Font("宋体", Font.BOLD, 25);
	
	public ChooseView(Controller con) {
		this.con = con;
		
		this.setTitle("套卡设置  " + Config.title);
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, (int)(Config.mainWeight / 1.8), (int)(Config.mainHeight / 1.5));
		this.setLocationRelativeTo(con.mainV);
		this.setLayout(null);
		this.setContentPane(back);
		//玩家昵称
		playerLabels[0] = new JLabel("玩家一");
		playerLabels[1] = new JLabel("玩家二");
		//卡组名称
		deckLabels[0] = new JLabel("法师套牌");
		deckLabels[1] = new JLabel("德鲁伊套牌");
		deckLabels[2] = new JLabel("骑士套牌");
		
		for(int i = 0; i < 3; i++) {
			deckPtos[i] = new JLabel();
		}
		//卡组名称及卡片
		ImageIcon[] icons = new ImageIcon[3];
		icons[0] = new ImageIcon("src/photo/fashi.png");
		icons[1] = new ImageIcon("src/photo/deluyi.png");
		icons[2] = new ImageIcon("src/photo/shengqi.png");
		for(int i = 0; i < 3; i++) {
			deckLabels[i].setBounds(86, 60 + i * 80, 100, 30);
			
			icons[i].setImage(icons[i].getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH));
			deckPtos[i].setIcon(icons[i]);
			deckPtos[i].setBounds(15, 90 + i * 80, 200, 50);
			
			back.add(deckLabels[i]);
			back.add(deckPtos[i]);
		}
		//单选框
		for(int i = 0; i < 2; i++) {
			//玩家名标签
			playerLabels[i].setBounds(230 + i * 120, 30, 100, 50);
			playerLabels[i].setFont(font);
			back.add(playerLabels[i]);
			//玩家卡组单选框集合
			ButtonGroup g = new ButtonGroup();
			
			for(int j = 0; j < 3; j++) {
				//单选框
				chooseBtns[i][j] = new JRadioButton();
				chooseBtns[i][j].setBounds(260 + i * 120, 90 + j * 80, 50, 50);
				chooseBtns[i][j].setOpaque(false);
				g.add(chooseBtns[i][j]);
				back.add(chooseBtns[i][j]);
				
				
			}
			//刷新当前所选卡组
			chooseBtns[i][Deck.now[i]].setSelected(true);
		}
		//按钮
		sureBtn.setBounds(120, deckPtos[2].getY() + 80, 100, 30);
		noBtn.setBounds(240, deckPtos[2].getY() + 80, 100, 30);
		sureBtn.setFont(Config.btnFont);
		noBtn.setFont(Config.btnFont);
		sureBtn.setForeground(Config.btnForeColor);
		noBtn.setForeground(Config.btnForeColor);
		sureBtn.setBackground(Config.btnBackColor);
		noBtn.setBackground(Config.btnBackColor);
		
		back.add(sureBtn);
		back.add(noBtn);
		
		sureBtn.addActionListener(this);
		noBtn.addActionListener(this);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		//如果是确定
		if(btn == sureBtn) {
			//先询问是否确定修改卡组
			int choose = JOptionPane.showConfirmDialog(con.mainV, 
					"修改后会重开游戏，是否继续？", Config.title, 
					JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
			//确定
			if(choose == JOptionPane.YES_OPTION) {
				//修改卡组
				for(int i = 0; i < 2; i++) {
					for(int j = 0; j < Deck.count; j++) {
						if(this.chooseBtns[i][j].isSelected()) {
							Deck.now[i] = j;
							break;
						}
					}
				}
				//关闭窗口
				this.dispose();
				//重开游戏
				con.regame();
			}
		}
		//如果是取消
		else {
			this.dispose();
		}
	}
	
	public static void main(String[] args) {
		new ChooseView(new Controller());
	}
}

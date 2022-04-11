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

	//������
	Controller con;
	
	//����
	public ShadePane back = new ShadePane("�׿�ѡ��", Color.white, Color.gray);
	//�����׿�
	public JLabel[] playerLabels = new JLabel[2];//����ǳ�
	public JLabel[] deckLabels = new JLabel[3];//��������
	public JLabel[] deckPtos = new JLabel[3];//����ͼƬ
	public JRadioButton[][] chooseBtns = new JRadioButton[2][3];//��ѡ��
	//ѡ��
	public JButton sureBtn = new JButton("ȷ��");
	public JButton noBtn = new JButton("ȡ��");
	//������ɫ
	public Font font = new Font("����", Font.BOLD, 25);
	
	public ChooseView(Controller con) {
		this.con = con;
		
		this.setTitle("�׿�����  " + Config.title);
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, (int)(Config.mainWeight / 1.8), (int)(Config.mainHeight / 1.5));
		this.setLocationRelativeTo(con.mainV);
		this.setLayout(null);
		this.setContentPane(back);
		//����ǳ�
		playerLabels[0] = new JLabel("���һ");
		playerLabels[1] = new JLabel("��Ҷ�");
		//��������
		deckLabels[0] = new JLabel("��ʦ����");
		deckLabels[1] = new JLabel("��³������");
		deckLabels[2] = new JLabel("��ʿ����");
		
		for(int i = 0; i < 3; i++) {
			deckPtos[i] = new JLabel();
		}
		//�������Ƽ���Ƭ
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
		//��ѡ��
		for(int i = 0; i < 2; i++) {
			//�������ǩ
			playerLabels[i].setBounds(230 + i * 120, 30, 100, 50);
			playerLabels[i].setFont(font);
			back.add(playerLabels[i]);
			//��ҿ��鵥ѡ�򼯺�
			ButtonGroup g = new ButtonGroup();
			
			for(int j = 0; j < 3; j++) {
				//��ѡ��
				chooseBtns[i][j] = new JRadioButton();
				chooseBtns[i][j].setBounds(260 + i * 120, 90 + j * 80, 50, 50);
				chooseBtns[i][j].setOpaque(false);
				g.add(chooseBtns[i][j]);
				back.add(chooseBtns[i][j]);
				
				
			}
			//ˢ�µ�ǰ��ѡ����
			chooseBtns[i][Deck.now[i]].setSelected(true);
		}
		//��ť
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
		//�����ȷ��
		if(btn == sureBtn) {
			//��ѯ���Ƿ�ȷ���޸Ŀ���
			int choose = JOptionPane.showConfirmDialog(con.mainV, 
					"�޸ĺ���ؿ���Ϸ���Ƿ������", Config.title, 
					JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
			//ȷ��
			if(choose == JOptionPane.YES_OPTION) {
				//�޸Ŀ���
				for(int i = 0; i < 2; i++) {
					for(int j = 0; j < Deck.count; j++) {
						if(this.chooseBtns[i][j].isSelected()) {
							Deck.now[i] = j;
							break;
						}
					}
				}
				//�رմ���
				this.dispose();
				//�ؿ���Ϸ
				con.regame();
			}
		}
		//�����ȡ��
		else {
			this.dispose();
		}
	}
	
	public static void main(String[] args) {
		new ChooseView(new Controller());
	}
}

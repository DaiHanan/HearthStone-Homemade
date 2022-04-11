package config;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class Config {
	//�����汳����ɫ(֮���Ǳ���ͼƬ)
	public static final Color mainColor = Color.gray;
	//�����浱ǰ�غϱ߿�Ŀ��
		public static final int nowTurnBorderWeight = 5;
	//�����浱ǰ�غϱ߿�
	public static final Border nowTurnBorder = BorderFactory.
			createLineBorder(new Color(255, 255, 153), nowTurnBorderWeight, false);//����ɫ
	//�����泤��
	public static final int mainWeight = 800;
	public static final int mainHeight = 630;
	//������ť����
	public static final int btnWeight = 100;
	public static final int btnHeight = 50;
	//������ť�������ɫ�ͼ��
	public static final Font btnFont = new Font("����", Font.BOLD, 18);
	public static final Color btnForeColor = Color.white;
	public static final Color btnBackColor = new Color(204, 153, 255);//����ɫ
	public static final int btnInterval = 3;
	//�غϰ�ťλ��
	public static final int turnChangeX = 680;
	public static final int turnChangeY = 280;
	//�غϰ�ť�������ɫ�ͼ��
	public static final Font turnFont = new Font("����", Font.BOLD, 18);
	public static final Color turnForeColor = Color.black;
	public static final Color turnBackColor = new Color(255, 255, 204);//����ɫ
	public static final int turnInterval = 5;
	//��������ʼY����(X����Ϊ0)�ͳ���
	public static final int ruleStrY = mainHeight / 2 - btnHeight / 2 + 17;
	public static final int ruleStrLen = turnChangeX;
	//ʣ��������ʾλ�úʹ�С
	public static final int[] libX = {turnChangeX, turnChangeX};
	public static final int[] libY = {turnChangeY - (int)(btnHeight * 0.8), 
			turnChangeY + (int)(btnHeight * 0.8)};
	//public static final int lib_1_X = turnChangeX + 8;
	//public static final int lib_1_Y = turnChangeY - (int)(btnHeight * 0.8);
	//public static final int lib_2_X = lib_1_X;
	//public static final int lib_2_Y = turnChangeY + (int)(btnHeight * 0.8);
	public static final int libWeight = 2 * btnWeight;
	public static final int libHeight = btnHeight;
	//ʣ��������ʾ�������ɫ
	public static final Font libFont = new Font("����", Font.BOLD, 18);
	public static final Color libColor = Color.white;
	//���Ʒָ�����ʼ���꣬620Ϊԭ�غϽ�����ťλ��
	public static final int handStrX = 2 * 620 - (int)(0.9 * mainWeight);
//	//���Ѫ��λ��
//	public static final int heal_1_X = handStrX / 2 - 2; //��Ϊx����Ϊ���ֿ�ʼλ�ã�������Ҫ������һ��
//	public static final int heal_1_Y = lib_1_Y;
//	public static final int heal_2_X = heal_1_X;
//	public static final int heal_2_Y = 375;
	//���ư�ť�ļ���ͳ���
	public static final int handIntervalX = 20;
	public static final int handIntervalY = 5;
	public static final int handWeight = btnWeight;
	public static final int handHeight = 40;
	//������ʾ��ʼλ��
	public static final int[] handInitX = {handStrX + handIntervalX, handStrX + handIntervalX};
	public static final int[] handInitY = {7 * handIntervalY, libY[1] + btnHeight - 10};
	//public static final int cardInit_1_X = handStrX + cardIntervalX;
	//public static final int cardInit_1_Y = cardIntervalY;
	//public static final int cardInit_2_X = cardInit_1_X;
	//public static final int cardInit_2_Y = lib_2_Y + btnHeight + cardIntervalY / 2;
	//Ӣ�ۼ��ܰ�ťλ�úͳ�������������
	public static final int[] skillX = {handInitX[0], handInitX[0]};
	public static final int[] skillY = {handInitY[0] + 5 * (handIntervalY + handHeight), 
			ruleStrY + 2 * handIntervalY};
	//public static final int skill_1_X = cardInit_1_X;
	//public static final int skill_1_Y = cardInit_1_Y + 5 * (cardIntervalY + cardHeight);
	//public static final int skill_2_X = skill_1_X;
	//public static final int skill_2_Y = ruleStrY + cardIntervalY;
	public static final int skillWeight = handWeight;
	public static final int skillHeight = handHeight;
	//���(���)�ļ���ͳ���
	public static final int attendIntervalX = 5;
	public static final int attendIntervalY = 10;
	public static final int attendWeight = handStrX / 4 - attendIntervalX;
	public static final int attendHeight = mainHeight / 5;
	//���(���)��ʾ��ʼλ��
	public static final int[] attendInitX = {attendIntervalX, attendIntervalX};
	public static final int[] attendInitY = {3 * attendIntervalY + 5, ruleStrY + attendIntervalY};
	//public static final int attendInit_1_X = attendIntervalX;
	//public static final int attendInit_1_Y = attendIntervalY;
	//public static final int attendInit_2_X = attendInit_1_X;
	//public static final int attendInit_2_Y = ruleStrY + attendIntervalY;
	//��ӿ�Ƭ�ĸ�������
	public static final Font natureFont = new Font("����", Font.BOLD, 15);
	public static final Font nameFont = new Font("����", Font.BOLD, 20);
	public static final Font nameSmallFont = new Font("����", Font.BOLD, 15);
	public static final Font hpFont = new Font("����", Font.BOLD, 30);
	public static final Font atkFont = hpFont;
	public static final Font featureFont = natureFont;
	public static final Font armorFont = hpFont;
	//��ҿ�Ƭ�Ļ�����ɫ
	public static final Color armorColor = new Color(204, 204, 204);
	//��ӿ�Ƭ��Ѫ����������ɫ
	public static final Color hpMAXColor = Color.green;
	public static final Color hpColor = Color.red;
	public static final Color atkColor = Color.yellow;
	//��ӵ�������ɫ
	public static final Color nameColor = Color.orange;
	//��Ƭ��λ����ɫ������
	public static final Color numberColor = Color.white;
	public static final Font numberFont = natureFont;
	//��Ƭ�ɹ����߿�
	public static final Border canAtkBorder = 
			BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(102, 255, 0));//����
	//��Ƭ����߿�
	public static final Border tauntBorder = 
			BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(153, 51, 0));//��ɫ
	//��Ƭ�����߿�
		public static final Border froozenBorder = 
				BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 255, 255));//����ɫ
	//ˮ������(ֱ��)
	public static final int costDiameter = 17;
	//ˮ����ʼλ�úͼ��
	public static final int[] costX = {handInitX[0] + 2 * handWeight + (int)(1.2 * handIntervalX), 
			handInitX[0] + 2 * handWeight + (int)(1.2 * handIntervalX)};
	public static final int[] costY = {6 * nowTurnBorderWeight + 7, libY[1] + (int)(libHeight / 1.1)};
	//public static final int cost_1_X = handInit_1_X + 2 * handWeight + (int)(1.2 * handIntervalX);
	//public static final int cost_1_Y = 0;
	//public static final int cost_2_X = cost_1_X;
	//public static final int cost_2_Y = lib_2_Y + (int)(libHeight / 1.4);
	public static final int costInterval = 5;
	//�ƿ��ʼ��
	public static final int LibInitCount = 30;
	//��������
	public static final int handMaxCount = 10;
	//վ������
	public static final int standMaxCount = 7;
	//��ʼ��������
	public static final int deckCount = 30;
	//���غ�ʱ������
	public static final int timeMax = 60;
	//�غ�����
	public static final int turnMax = 100;
	//���ˮ����
	public static final int costMax = 10;
	//�������ö�ٶ�Ӧ����
	public static final String[] featureName = {
			"��",
			"��ŭ",
			"����",
			"�綾",
			"���",
			"ʥ��",
	};
	//�������ö�ٶ�Ӧ������ɫ
	public static final Color[] featureColor = {
			Color.white,//�ޣ���ɫ
			new Color(153, 255, 255),//��ŭ������
			new Color(153, 51, 0),//������ɫ
			Color.green,//�綾����ɫ
			new Color(255, 51, 0),//��棬��(һ����)
			Color.yellow,//ʥ�ܣ���ɫ
	};
	//�������ö�ٶ�Ӧ����
	public static final String[] natureName = {
			"��ͨ", 
			"���",
			"Ұ��",
			"��",
	};
	//�������ö�ٶ�Ӧ��ɫ
	public static final Color[] natureColor = {
			Color.white, //��ͨ-��ɫ
			new Color(255, 51, 102),//���-�Һ�
			new Color(153, 0, 0),//Ұ��-��ɫ
			Color.yellow,//��-��ɫ
	};
	//�������ö�ٶ�Ӧ�ı�����ɫ
	public static final Color[] standBackColor = {
			new Color(204, 153, 102),//��ͨ-ɳĮɫ
			new Color(104, 104, 104),//���-��ɫ
			new Color(204, 102, 51),//Ұ��-ǳ��ɫ
			new Color(153, 153, 0),//��-���ɫ
	};
	//����Ϊ���ʱ��ӵ�������ɫ����ʾ����
	public static final Color moreFeatureColor = Color.white;//��ɫ
	public static final String moreFeatureStr = "��";
	
//	//վ����Ƭ��������Y������
//	public static final float attendNatureY = attendHeight / (float)4;
//	//վ����Ƭ��������Y������
//	public static final float attendNameY = attendHeight / (float)2;
//	//վ����Ƭ��������Y�����꣬ͬʱҲ�ǹ���Ѫ����ʾ��Y������
//	public static final float attendFeatureY = attendHeight / (float)1.2;
	
	//�������ĳ���
	public static final int descPaneWeight = handWeight * 2 + handIntervalX;
	public static final int descPaneHeight = handHeight * 5 + handIntervalY * 4;
	//������ǩ�������ɫ
	public static final Font descFont = new Font("����", Font.PLAIN, 20);
	public static final Color descColor = Color.white;
	//���ñ�ǩ�������ɫ
	public static final Font costFont = nameFont;
	public static final Color costColor = new Color(0, 51, 255);//��
	
	//������巨���Ʊ�����ɫ
	public static final Color magicDescColor = new Color(102, 0, 51);//���
	
	//��ʾ�����ӳ�ʱ��(ms)
	public static final int descTimeInterval = 233;
	
	//����
	public static final String title = "�¡�¯ʯ��˵����Ϸ";
	//����ͼ���ַ
	public static final ImageIcon inqueryIcon = new ImageIcon("src/config/pic/inquery.jpg");//ѯ��
	public static final ImageIcon winIcon = new ImageIcon("src/config/pic/win.jpg");//ʤ��
	public static final ImageIcon scaredIcon = new ImageIcon("src/config/pic/scared.jpg");//����
}

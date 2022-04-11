package config;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class Config {
	//主界面背景颜色(之后考虑背景图片)
	public static final Color mainColor = Color.gray;
	//主界面当前回合边框的宽度
		public static final int nowTurnBorderWeight = 5;
	//主界面当前回合边框
	public static final Border nowTurnBorder = BorderFactory.
			createLineBorder(new Color(255, 255, 153), nowTurnBorderWeight, false);//淡黄色
	//主界面长宽
	public static final int mainWeight = 800;
	public static final int mainHeight = 630;
	//基础按钮长宽
	public static final int btnWeight = 100;
	public static final int btnHeight = 50;
	//基础按钮字体和颜色和间距
	public static final Font btnFont = new Font("楷体", Font.BOLD, 18);
	public static final Color btnForeColor = Color.white;
	public static final Color btnBackColor = new Color(204, 153, 255);//淡紫色
	public static final int btnInterval = 3;
	//回合按钮位置
	public static final int turnChangeX = 680;
	public static final int turnChangeY = 280;
	//回合按钮字体和颜色和间距
	public static final Font turnFont = new Font("楷体", Font.BOLD, 18);
	public static final Color turnForeColor = Color.black;
	public static final Color turnBackColor = new Color(255, 255, 204);//淡黄色
	public static final int turnInterval = 5;
	//中心线起始Y坐标(X坐标为0)和长度
	public static final int ruleStrY = mainHeight / 2 - btnHeight / 2 + 17;
	public static final int ruleStrLen = turnChangeX;
	//剩余牌数显示位置和大小
	public static final int[] libX = {turnChangeX, turnChangeX};
	public static final int[] libY = {turnChangeY - (int)(btnHeight * 0.8), 
			turnChangeY + (int)(btnHeight * 0.8)};
	//public static final int lib_1_X = turnChangeX + 8;
	//public static final int lib_1_Y = turnChangeY - (int)(btnHeight * 0.8);
	//public static final int lib_2_X = lib_1_X;
	//public static final int lib_2_Y = turnChangeY + (int)(btnHeight * 0.8);
	public static final int libWeight = 2 * btnWeight;
	public static final int libHeight = btnHeight;
	//剩余牌数显示字体和颜色
	public static final Font libFont = new Font("黑体", Font.BOLD, 18);
	public static final Color libColor = Color.white;
	//手牌分割线起始坐标，620为原回合结束按钮位置
	public static final int handStrX = 2 * 620 - (int)(0.9 * mainWeight);
//	//玩家血条位置
//	public static final int heal_1_X = handStrX / 2 - 2; //因为x坐标为数字开始位置，所以需要往左移一格
//	public static final int heal_1_Y = lib_1_Y;
//	public static final int heal_2_X = heal_1_X;
//	public static final int heal_2_Y = 375;
	//手牌按钮的间隔和长宽
	public static final int handIntervalX = 20;
	public static final int handIntervalY = 5;
	public static final int handWeight = btnWeight;
	public static final int handHeight = 40;
	//手牌显示起始位置
	public static final int[] handInitX = {handStrX + handIntervalX, handStrX + handIntervalX};
	public static final int[] handInitY = {7 * handIntervalY, libY[1] + btnHeight - 10};
	//public static final int cardInit_1_X = handStrX + cardIntervalX;
	//public static final int cardInit_1_Y = cardIntervalY;
	//public static final int cardInit_2_X = cardInit_1_X;
	//public static final int cardInit_2_Y = lib_2_Y + btnHeight + cardIntervalY / 2;
	//英雄技能按钮位置和长宽，和手牌相似
	public static final int[] skillX = {handInitX[0], handInitX[0]};
	public static final int[] skillY = {handInitY[0] + 5 * (handIntervalY + handHeight), 
			ruleStrY + 2 * handIntervalY};
	//public static final int skill_1_X = cardInit_1_X;
	//public static final int skill_1_Y = cardInit_1_Y + 5 * (cardIntervalY + cardHeight);
	//public static final int skill_2_X = skill_1_X;
	//public static final int skill_2_Y = ruleStrY + cardIntervalY;
	public static final int skillWeight = handWeight;
	public static final int skillHeight = handHeight;
	//随从(玩家)的间隔和长宽
	public static final int attendIntervalX = 5;
	public static final int attendIntervalY = 10;
	public static final int attendWeight = handStrX / 4 - attendIntervalX;
	public static final int attendHeight = mainHeight / 5;
	//随从(玩家)显示起始位置
	public static final int[] attendInitX = {attendIntervalX, attendIntervalX};
	public static final int[] attendInitY = {3 * attendIntervalY + 5, ruleStrY + attendIntervalY};
	//public static final int attendInit_1_X = attendIntervalX;
	//public static final int attendInit_1_Y = attendIntervalY;
	//public static final int attendInit_2_X = attendInit_1_X;
	//public static final int attendInit_2_Y = ruleStrY + attendIntervalY;
	//随从卡片的各种字体
	public static final Font natureFont = new Font("楷书", Font.BOLD, 15);
	public static final Font nameFont = new Font("楷书", Font.BOLD, 20);
	public static final Font nameSmallFont = new Font("楷书", Font.BOLD, 15);
	public static final Font hpFont = new Font("楷书", Font.BOLD, 30);
	public static final Font atkFont = hpFont;
	public static final Font featureFont = natureFont;
	public static final Font armorFont = hpFont;
	//玩家卡片的护甲颜色
	public static final Color armorColor = new Color(204, 204, 204);
	//随从卡片的血量攻击力颜色
	public static final Color hpMAXColor = Color.green;
	public static final Color hpColor = Color.red;
	public static final Color atkColor = Color.yellow;
	//随从的名字颜色
	public static final Color nameColor = Color.orange;
	//卡片的位置颜色和字体
	public static final Color numberColor = Color.white;
	public static final Font numberFont = natureFont;
	//卡片可攻击边框
	public static final Border canAtkBorder = 
			BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(102, 255, 0));//翠绿
	//卡片嘲讽边框
	public static final Border tauntBorder = 
			BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(153, 51, 0));//棕色
	//卡片冰冻边框
		public static final Border froozenBorder = 
				BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 255, 255));//天蓝色
	//水晶长宽(直径)
	public static final int costDiameter = 17;
	//水晶起始位置和间隔
	public static final int[] costX = {handInitX[0] + 2 * handWeight + (int)(1.2 * handIntervalX), 
			handInitX[0] + 2 * handWeight + (int)(1.2 * handIntervalX)};
	public static final int[] costY = {6 * nowTurnBorderWeight + 7, libY[1] + (int)(libHeight / 1.1)};
	//public static final int cost_1_X = handInit_1_X + 2 * handWeight + (int)(1.2 * handIntervalX);
	//public static final int cost_1_Y = 0;
	//public static final int cost_2_X = cost_1_X;
	//public static final int cost_2_Y = lib_2_Y + (int)(libHeight / 1.4);
	public static final int costInterval = 5;
	//牌库初始数
	public static final int LibInitCount = 30;
	//手牌上限
	public static final int handMaxCount = 10;
	//站场上限
	public static final int standMaxCount = 7;
	//初始卡组数量
	public static final int deckCount = 30;
	//单回合时间上限
	public static final int timeMax = 60;
	//回合上限
	public static final int turnMax = 100;
	//最大水晶数
	public static final int costMax = 10;
	//随从特性枚举对应名称
	public static final String[] featureName = {
			"无",
			"风怒",
			"嘲讽",
			"剧毒",
			"冲锋",
			"圣盾",
	};
	//随从特性枚举对应字体颜色
	public static final Color[] featureColor = {
			Color.white,//无，白色
			new Color(153, 255, 255),//风怒，天蓝
			new Color(153, 51, 0),//嘲讽，棕色
			Color.green,//剧毒，绿色
			new Color(255, 51, 0),//冲锋，红(一点点黄)
			Color.yellow,//圣盾，黄色
	};
	//随从属性枚举对应名称
	public static final String[] natureName = {
			"普通", 
			"玩家",
			"野兽",
			"龙",
	};
	//随从属性枚举对应颜色
	public static final Color[] natureColor = {
			Color.white, //普通-白色
			new Color(255, 51, 102),//玩家-桃红
			new Color(153, 0, 0),//野兽-棕色
			Color.yellow,//龙-黄色
	};
	//随从属性枚举对应的背景颜色
	public static final Color[] standBackColor = {
			new Color(204, 153, 102),//普通-沙漠色
			new Color(104, 104, 104),//玩家-灰色
			new Color(204, 102, 51),//野兽-浅棕色
			new Color(153, 153, 0),//龙-深黄色
	};
	//特性为多个时随从的特性颜色和显示字体
	public static final Color moreFeatureColor = Color.white;//白色
	public static final String moreFeatureStr = "多";
	
//	//站场卡片的属性栏Y轴坐标
//	public static final float attendNatureY = attendHeight / (float)4;
//	//站场卡片的名称栏Y轴坐标
//	public static final float attendNameY = attendHeight / (float)2;
//	//站场卡片的特性栏Y轴坐标，同时也是攻击血量显示的Y轴坐标
//	public static final float attendFeatureY = attendHeight / (float)1.2;
	
	//描述面板的长宽
	public static final int descPaneWeight = handWeight * 2 + handIntervalX;
	public static final int descPaneHeight = handHeight * 5 + handIntervalY * 4;
	//描述标签字体和颜色
	public static final Font descFont = new Font("楷书", Font.PLAIN, 20);
	public static final Color descColor = Color.white;
	//费用标签字体和颜色
	public static final Font costFont = nameFont;
	public static final Color costColor = new Color(0, 51, 255);//蓝
	
	//描述面板法术牌背景颜色
	public static final Color magicDescColor = new Color(102, 0, 51);//深粉
	
	//显示描述延迟时长(ms)
	public static final int descTimeInterval = 233;
	
	//标题
	public static final String title = "仿“炉石传说”游戏";
	//各种图标地址
	public static final ImageIcon inqueryIcon = new ImageIcon("src/config/pic/inquery.jpg");//询问
	public static final ImageIcon winIcon = new ImageIcon("src/config/pic/win.jpg");//胜利
	public static final ImageIcon scaredIcon = new ImageIcon("src/config/pic/scared.jpg");//害怕
}

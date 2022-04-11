package controller;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import config.Config;
import config.Deck;
import model.Feature;
import model.Nature;
import model.Player;
import model.card.Card;
import model.card.attend.Attend;
import model.card.magic.neutral.LuckyCoin;
import view.AboutView;
import view.MainView;
import view.StandCard;

public class Controller {
	public Player[] player;//角色数组
	public MainView mainV;
	
	public int turn = -1;//当前回合数
	
	//Thread mainT = new mainThread();//游戏主线程
	Random random = new Random();
	
	public Controller() {
		//初始化卡组
		new Deck();
		//初始化角色
		player = new Player[2];
		player[0] = new Player(Deck.decks[Deck.now[0]], Deck.skills[Deck.now[0]]);
		player[1] = new Player(Deck.decks[Deck.now[1]], Deck.skills[Deck.now[1]]);
		//初始化主界面
		mainV = new MainView();
		//更新玩家模板
		mainV.setStandCard(player[0], 0, 0);
		mainV.setStandCard(player[1], 0, 1);
		/**设置监听器*/
		new Listen(this);
		Listen.setTurnChangeListener(this.mainV.turnChange);//回合交换
		for(int i = 0; i < 2; i++)
			Listen.setSkillListener(this.mainV.skillBtns[i]);//技能
		Listen.setAboutListener(this.mainV.aboutMI);//说明窗口
		Listen.setChooseListener(this.mainV.chooseMI);//选择卡片窗口
		//主线程开启
		(new mainThread()).start();
		//开启介绍页面
		new AboutView(this);
	}
	
	//获得当前回合玩家id
	public int getNowId() {
		return turn % 2;
	}
	
	//从牌库抽一张卡片(不删除)
	public Card getOneLib(int id) {
		if((id == 0 || id == 1) && player[id].lib.size() > 0)
			return player[id].lib.get(random.nextInt(player[id].lib.size()));
		return null;
	}
	//从牌库删除指定卡片,并更新牌库剩余卡牌标签
	public void delOneLib(int id, Card card) {
		if(id == 0 || id == 1) {
			//注：如果card不存在不会提示
			if(card != null) {
				player[id].lib.remove(card);
				mainV.setLibLable(id, player[id].lib.size());
			}
		}
	}
	//从牌库抽一张牌(删除)
	public Card drawOneLib(int id) {
		Card card = getOneLib(id);
		//System.out.println(card.desc + "3");
		delOneLib(id, card);
		return card;
	}
	//添加手牌指定卡片
	public void addOneHand(int id, Card card) {
		if(id == 0 || id == 1) {
			if(player[id].hand.size() < Config.handMaxCount) {
				//数据
				//System.out.println(card.desc + "3");
				player[id].hand.add(card);
				//添加手牌按钮
				this.mainV.addHandCard(id);
				//为当前添加在手牌末尾的按钮设置监听器
				Listen.setHandListener(this.mainV.hands.get(id).get(this.player[id].hand.size() - 1));
			}
			//else//提示
		}
	}
	//删除手牌指定卡片
	public void delOneHand(int id, Card card) {
		if(id == 0 || id == 1) {
			//注：如果card不存在不会提示
			player[id].hand.remove(card);
			this.mainV.delHandCard(id, 1);
		}
	}
	//抽牌
	public void drawCard(int id, int count) {
		for(int i = 0; i < count; i++) {
			//抽一张牌
			Card card = drawOneLib(id);
			if(card == null) {
				//牌库为空则疲劳
				//System.out.println(player[id].tiredCount);
				changeStandCardHp(id, 0, 0 - ++player[id].tiredCount);
			}
			else {
				//如果没满则添加
				//if(player[id].hand.size() < Config.handMaxCount) {
				addOneHand(id, card);
				//}
			}
		}
	}
	//两个随从互相攻击(随从1攻击随从2)
	public void attendFight(int id_1, int idx_1, int id_2, int idx_2) {
		//获得双方卡片
		StandCard card = mainV.stands[id_1][idx_1];
		StandCard oppoCard = mainV.stands[id_2][idx_2];
		//相互攻击
		//获得双方攻击力，防止随从死亡后随从为空
		int oppoAtk = oppoCard.attend.atk;
		int nowAtk = card.attend.atk;
		//如果特性是剧毒且对方不为英雄，攻击力为对方血量
		if(card.attend.features.contains(Feature.Poison) && 
				oppoCard.attend.nature != Nature.Player) {
			nowAtk = oppoCard.attend.hp;
		}
		if(oppoCard.attend.features.contains(Feature.Poison) && 
				card.attend.nature != Nature.Player) {
			oppoAtk = card.attend.hp;
		}
		//敌方
		changeStandCardHp(id_2, idx_2, 0 - nowAtk);
		//友方
		if(idx_2 != 0)//若攻击英雄，该卡片不会受到伤害
			changeStandCardHp(id_1, idx_1, 0 - oppoAtk);
		//更新攻击次数并刷新可攻击边框
		if(--card.attend.atkTime <= 0) {
			card.borderOff();
		}
	}
	//改变卡片(随从)血量值, +回血-扣血
	public void changeStandCardHp(int id, int idx, int value) {
		//获得卡片的随从对象
		Attend attend = this.mainV.stands[id][idx].attend;
		//回血
		if(value >= 0) 
			attend.hp = Math.min(attend.hpMax, attend.hp + value);
		//扣血
		else {
			//如果是英雄需判断护甲
			if(attend instanceof Player) {
				value = changePlayerArmor(id, value);
			}
			//如果有圣盾特性，抵挡伤害并取消圣盾特性
			if(attend.features.contains(Feature.Shield)) {
				attend.features.remove(Feature.Shield);
				//如果该随从只有圣盾特性，补个普通属性
				if(attend.features.size() == 0)
					attend.features.add(Feature.NORMAL);
				//伤害为0
				value = 0;
			}
			attend.hp += value;//value为负值
		}
		/**更新卡片显示*/
		boolean isDead = this.mainV.setStandCard(attend, idx, id);
		//触发亡语
		if(isDead)
			attend.deadWords(this, id);
//		//找到该卡片
//		for(int i = 0; i < 2; i++) {
//			StandCard[] stand = this.mainV.stands[i];
//			for(int j = 0; j < Config.standMaxCount + 1; j++) {
//				StandCard card = stand[j];
//				if(card != null && card.attend == attend) {
//					//card.hpLabel.setText("" + attend.hp);
//					//card.hpLabel.repaint();
//					this.mainV.setStandCard(attend, j, i);
//				}
//			}
//		}
		//检测玩家血量
		checkGameOver();
	}
	
	public void changeStandCardAtk(int id, int idx, int value) {
		//获得卡片的随从对象
		Attend attend = this.mainV.stands[id][idx].attend;
		//减攻 
		if (value < 0)
		attend.atk = Math.max(0, attend.atk + value);
		//加攻
		if (value >= 0)
			attend.atk+=value;
		//刷新视图
		this.mainV.setStandCard(attend, idx, id);
	}
	
	//改变玩家护甲值，当护甲减少时且护甲不够减后返回溢出伤害，否则返回0
	//需手动刷新视图
	public int changePlayerArmor(int id, int value) {
		Player nowPlayer = player[id];
		if(value >= 0) {
			nowPlayer.armor += value;
			return 0;
		}
		else {
			int armor = nowPlayer.armor;
			nowPlayer.armor = Math.max(nowPlayer.armor + value, 0);
			value = Math.min(value + armor, 0);
			return value;
		}
	}
	
	//检测游戏是否结束
	public void checkGameOver() {
		int dieId = -1;
		if(player[0].hp <= 0 && player[1].hp <= 0)//同归于尽
			dieId = 2;
		else if(player[0].hp <= 0)//玩家二获胜
			dieId = 1;
		else if(player[1].hp <= 0) //玩家一获胜
			dieId = 0;
		if(dieId != -1)
			gameOver(dieId);
	}
	//游戏结束,dieId:0-玩家一 1-玩家二 2-同归于尽
	public void gameOver(int dieId) {
		if(dieId >= 0 && dieId < 3) {
			String msg = "玩家" + (1 + dieId) + "获胜！";
			if(dieId == 2)
				msg = "精彩绝伦！同归于尽！";
			JOptionPane.showMessageDialog(mainV, msg, Config.title, 
					0, Config.winIcon);
			int choice = JOptionPane.showConfirmDialog(mainV, "是否再来一局？", Config.title, 
					JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
			
			if(choice == JOptionPane.YES_OPTION) 
				this.regame();
			else {
				//关闭窗口
				this.mainV.dispose();
				this.mainV.setVisible(false);
			}
		}
	}
	//重开游戏
	public void regame() {
		//初始化角色
		player = new Player[2];
		player[0] = new Player(Deck.decks[Deck.now[0]], Deck.skills[Deck.now[0]]);
		player[1] = new Player(Deck.decks[Deck.now[1]], Deck.skills[Deck.now[1]]);
		//初始化主界面
		this.mainV.regame();
		//更新玩家模板
		mainV.setStandCard(player[0], 0, 0);
		mainV.setStandCard(player[1], 0, 1);
		/**设置监听器*/
		for(int i = 0; i < 2; i++)
			Listen.setSkillListener(this.mainV.skillBtns[i]);//技能
		//重置回合数且主线程开启
		this.turn = -1;
		(new mainThread()).start();
	}
	//回合改变
	public void turnChange() {
		//先触发上回合的回合结束效果(暂只触发本回合随从效果)
		int lastNowId = getNowId();
		for(int i = 1; i < Config.standMaxCount + 1; i++)
			if(mainV.stands[lastNowId][i] != null && mainV.stands[lastNowId][i].attend.hasEndTurnEffect)
				mainV.stands[lastNowId][i].attend.endTurnEffect(this, lastNowId);
		
		//mainThread.interrupt();
		(new mainThread()).start();
	}
	
	/**随机获得目标
	 * type:1_全体可选目标
	 * 	   :2_敌方可选目标
	 *     :3_我方可选目标
	 *     :4_敌方可进攻目标
	 *     :5_我方可选随从
	 *     :6_敌方可选随从
	 * nowId:使用方Id
	 * return:int[0]:随机Id,-1则没有目标
	 *       :int[1]:随机Idx
	 * */
	public int[] randomTarget(int type, int nowId) {
		//敌方id
		int oppoId = 1 - nowId;
		//返回数组
		int[] res = new int[2];
		
		//站场下标数组，存储内容：个位数:id为0的玩家，十位数:id为1的玩家
		int idxs[] = new int[2 * Config.standMaxCount + 2];
		//按照类型获得可选单位个数,并存储下标
		int count = 0;
		
		switch(type) {
		//type - 1 全体可选目标
		case 1:
			//(未考虑潜行等特性)
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < Config.standMaxCount + 1; j++) {
					if(mainV.stands[i][j] != null) {
						idxs[count] = j + i * 10;
						count++;
					}
				}
			}
			break;
		//type - 2 敌方可选目标
		case 2:
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[oppoId][i] != null) {
					idxs[count] = i + oppoId * 10;
					count++;
				}
			}
			break;
		//type - 3 我方可选目标
		case 3:
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[nowId][i] != null) {
					idxs[count] = i + nowId * 10;
					count++;
				}
			}
			break;
		//type - 4 敌方可进攻目标
		case 4:
			//(未考虑潜行等特性)
			boolean hasTaunt = false;//是否有嘲讽
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				StandCard stand = mainV.stands[oppoId][i];
				if(stand != null) {
					//如果没有嘲讽就检查嘲讽属性
					if(hasTaunt == false && stand.attend.features.contains(Feature.Taunt) == true) {
						hasTaunt = true;
						count = 0;
					}
					
					idxs[count] = i + oppoId * 10;
					
					//如果暂无嘲讽属性，直接加入数组
					if(hasTaunt == false) 
						count++;
					//如果存在嘲讽，需要判断该卡片随从是否有嘲讽属性
					else if(stand.attend.features.contains(Feature.Taunt) == true) {
						count++;
					}
				}
			}
			break;
		//type - 5 我方可选随从
		case 5:
			for(int i = 1; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[nowId][i] != null) {
					idxs[count] = i + nowId * 10;
					count++;
				}
			}
			break;
		//type - 6 我方可选随从
		case 6:
			for(int i = 1; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[oppoId][i] != null) {
					idxs[count] = i + oppoId * 10;
					count++;
				}
			}
			break;
		}
		
		//如果有单位
		if(count > 0) {
			//获得随机数
			int ran = (int)Math.floor(Math.random() * count);
			//获得随机的随从所属id
			res[0] = idxs[ran] / 10;
			//获得随机的随从下标
			res[1] = idxs[ran] % 10;
		}
		else
			res[0] = -1;
		
		return res;
	}
	
	/**询问目标
	 * type:1_全体可选目标
	 * 	   :2_敌方可选目标
	 *     :3_我方可选目标
	 *     :4_敌方可进攻目标
	 *     :5_我方可选随从
	 *     :6_敌方可选随从
	 *     :7_全部可选随从
	 * nowId:使用方Id
	 * return:int[0]:选择Id,若选择失败Id为-1
	 *       :int[1]:选择Idx
	 * */
	public int[] inquiryTarget(int type, int nowId) {
		//敌方id
		int oppoId = 1 - nowId;
		//返回数组
		int[] res = new int[2];
		//选项数组
		LinkedList<String> list = new LinkedList<>();
		switch(type) {
		//type - 1 全体可选目标
		case 1:
			//(未考虑潜行等特性)
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < Config.standMaxCount + 1; j++) {
					if(mainV.stands[i][j] != null) {
						String str;
						if(i == nowId)
							str = "我方";
						else
							str = "敌方";
						if(j == 0)
							str += "英雄";
						else
							str += j + "号位";
						//例：我方英雄、敌方3号位
						list.add(str);
					}
				}
			}
			break;
		//type - 2 敌方可选目标
		case 2:
			for(int j = 0; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[oppoId][j] != null) {
					String str = "敌方";
					if(j == 0)
						str += "英雄";
					else
						str += j + "号位";
					//例：敌方3号位
					list.add(str);
				}
			}
			break;
		//type - 3 我方可选目标
		case 3:
			for(int j = 0; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[nowId][j] != null) {
					String str = "我方";
					if(j == 0)
						str += "英雄";
					else
						str += j + "号位";
					//例：敌方3号位
					list.add(str);
				}
			}
			break;
		//type - 4 敌方可进攻目标
		case 4:
			//(未考虑潜行等特性)
			boolean hasTaunt = false;//是否有嘲讽
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				StandCard stand = mainV.stands[oppoId][i];
				if(stand != null) {
					//如果没有嘲讽就检查嘲讽属性
					if(hasTaunt == false && stand.attend.features.contains(Feature.Taunt) == true) {
						hasTaunt = true;
						list.clear();//清空之前的随从(非嘲讽)
					}
					String str = "敌方";
					if(i == 0)
						str += "英雄";
					else
						str += i + "号位";
					//如果暂无嘲讽属性，直接加入数组
					if(hasTaunt == false)
						list.add(str);
					//如果存在嘲讽，需要判断该卡片随从是否有嘲讽属性
					else if(stand.attend.features.contains(Feature.Taunt) == true) {
						list.add(str);
					}
				}
			}
			break;
		//type - 5 我方可选随从
		case 5:
			for(int j = 1; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[nowId][j] != null) {
					String str = "我方" + j + "号位";
					//例：我方3号位
					list.add(str);
				}
			}
			break;
		//type - 6 我方可选随从
		case 6:
			for(int j = 1; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[oppoId][j] != null) {
					String str = "敌方" + j + "号位";
					//例：敌方3号位
					list.add(str);
				}
			}
			break;
		//type - 7 全部可选随从
		case 7:
			for(int i = 0; i < 2; i++) {
				for(int j = 1; j < Config.standMaxCount + 1; j++) {
					if(mainV.stands[i][j] != null) {
						String str;
						if(i == nowId)
							str = "我方";
						else
							str = "敌方";
						
						str += j + "号位";
						//例：敌方3号位
						list.add(str);
					}
				}
			}
			break;
		}
		//如果没有可选目标
		if(list.size() == 0) {
			JOptionPane.showMessageDialog(mainV, "无可选目标", Config.title, 0, Config.scaredIcon);
			res[0] = -1;
		}
		//有可选目标
		else {
			//获得玩家选项
			String choiceStr = (String)JOptionPane.showInputDialog(mainV, "请选择目标", 
					Config.title, 0, Config.inqueryIcon, list.toArray(), list.getFirst());
			//选择成功
			if(choiceStr != null) {
				//获得选择的目标位置
				if(choiceStr.substring(0, 2).equals("我方"))
					res[0] = nowId;//我方
				else
					res[0] = 1 - nowId;//敌方
				if(choiceStr.substring(2, 4).equals("英雄"))
					res[1] = 0;
				else
					res[1] = Integer.parseInt(choiceStr.substring(2, 3));
			}
			//选择失败
			else
				res[0] = -1;
		}
		
		return res;
	}
	
	//设置随从冰冻
	public void setAttendFroozen(int id, int idx, boolean isFroozen) {
		this.mainV.stands[id][idx].attend.isFroozen = isFroozen;
		this.mainV.stands[id][idx].attend.froozenNow = isFroozen;
		this.mainV.stands[id][idx].borderNow();
	}
	
	//获得当前站场随从数量
	public int getStandCardCount(int id) {
		int count = 0;
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(this.mainV.stands[id][i] != null)
				count++;
		}
		
		return count;
	}
	
	//直接增加站场随从,参数三为禁用下标，若为-1则无视
	public boolean addStandCard(Attend attend, int id, int noIdx) {
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(noIdx == i)
				continue;
			//找到位置即插入
			if(this.mainV.stands[id][i] == null) {
				this.mainV.setStandCard(attend, i, id);
				return true;
			}
		}
		//无法插入(没有位置)
		return false;
	}
	
	/**
	 * 修改水晶
	 * type:1_修改当前水晶
	 * 	   :2_修改回合水晶，增加时不增加当前水晶
	 * 	   :3_修改回合水晶，增加时增加当前水晶
	 * */
	public void changeCosts(int type, int id, int value) {
		switch(type) {
		//type:1_修改当前水晶
		case 1:
			//增加
			if(value >= 0) {
				this.player[id].cost = Math.min
						(this.player[id].cost + value, Config.costMax);
			}
			//减少
			if(value < 0) {
				this.player[id].cost = Math.max
						(this.player[id].cost + value, 0);
			}
			break;
		//2_修改回合水晶，增加时不增加当前水晶
		case 2:
			//增加
			if(value >= 0) {
				this.player[id].turnCost = Math.min
						(this.player[id].turnCost + value, Config.costMax);
			}
			//减少
			if(value < 0) {
				this.player[id].turnCost = Math.max
						(this.player[id].turnCost + value, 0);
				//同步减少当前水晶
				this.player[id].cost = Math.min(this.player[id].turnCost, this.player[id].cost);
			}
			break;
		//3_修改回合水晶，增加时增加当前水晶
		case 3:
			//增加
			if(value >= 0) {
				this.player[id].turnCost = Math.min
						(this.player[id].turnCost + value, Config.costMax);
				this.player[id].cost = Math.min
						(this.player[id].cost + value, Config.costMax);
			}
			//减少
			if(value < 0) {
				this.player[id].turnCost = Math.max
						(this.player[id].turnCost + value, 0);
				//同步减少当前水晶
				this.player[id].cost = Math.min(this.player[id].turnCost, this.player[id].cost);
			}
			break;
		}
		
		//刷新视图
		this.mainV.setCostPanes(id, this.player[id].cost, this.player[id].turnCost);
	}
	
	public static void main(String[] args) {
		new Controller();
	}
	
	//游戏主线程(内部类)
	class mainThread extends Thread {
		@Override
		public void run() {
			super.run();
			
			//如果回合更新前当前玩家有站场被冰冻，则判断是否为当前回合被冰冻
			//如果是，则不解冻，如果不是，则解冻
			int nowId = getNowId();
			//先判断是否有前一局
			if(nowId != -1) {
				//我方
				for(int i = 0; i < Config.standMaxCount + 1; i++) {
					if(mainV.stands[nowId][i] != null) {
						Attend attend = mainV.stands[nowId][i].attend;
						if(attend.froozenNow)
							attend.froozenNow = false;
						else if(attend.isFroozen)
							setAttendFroozen(nowId, i, false);
					}
				}
				//敌方
				int oppoId = nowId == 0 ? 1 : 0;
				for(int i = 0; i < Config.standMaxCount + 1; i++) {
					if(mainV.stands[oppoId][i] != null) {
						Attend attend = mainV.stands[oppoId][i].attend;
						if(attend.froozenNow)
							attend.froozenNow = false;
					}
				}
			}
			
			//更新回合数并判断上限
			if(++turn > Config.turnMax) {
				//到达回合上限，同归于尽
				gameOver(2);
			}
			
			//当前回合玩家id和对手id
			nowId = getNowId();
			int oppoId = nowId == 0 ? 1 : 0;
			//玩家抽牌，初始回合双方都抽4张牌
			if(turn == 0) {
				drawCard(nowId, 3);
				drawCard(oppoId, 3);
				//敌方获得一个“幸运硬币”
				addOneHand(oppoId, new LuckyCoin());
			}
			drawCard(nowId, 1);
			/**重置玩家的当前回合攻击力暂不考虑武器并刷新玩家技能使用次数*/
			for(int i = 0; i < 2; i++) {
				player[i].atk = 0;
				player[i].skillTime = 1;
				mainV.setStandCard(player[i], 0, i);
			}
			/**刷新水晶*/
			//更新本回合水晶上限
			if(player[nowId].turnCost < Config.costMax)
				++player[nowId].turnCost;
			//更新本回合可用水晶
			player[nowId].cost = Math.max(player[nowId].turnCost - player[nowId].overloadCost, 0);
			//重置过载水晶
			player[nowId].overloadCost = 0;
			//水晶显示
			mainV.setCostPanes(nowId, player[nowId].cost, player[nowId].turnCost);
			/**刷新攻击次数(暂未考虑风怒等特性)*/
			//刷新当前回合玩家的次数
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				//刷新当前玩家
				if(mainV.stands[nowId][i] != null) {
					mainV.stands[nowId][i].attend.atkTime = 1;
					//如果特性为风怒则攻击次数为2
					if(mainV.stands[nowId][i].attend.features.contains(Feature.Wind))
						mainV.stands[nowId][i].attend.atkTime = 2;
					//如果能够攻击且能够攻击或者被冰冻了，显示边框
					if(mainV.stands[nowId][i].attend.atk > 0 && mainV.stands[nowId][i].attend.canAtk || 
							mainV.stands[nowId][i].attend.isFroozen)
						mainV.stands[nowId][i].borderNow();
				}
				//刷新敌方玩家
				//关闭攻击边框
				if(mainV.stands[oppoId][i] != null) 
					mainV.stands[oppoId][i].borderOppo();
			}
			/**刷新主界面边框*/
			mainV.backPanels[nowId].setBorder(Config.nowTurnBorder);
			mainV.backPanels[oppoId].setBorder(null);
		}
	}

}

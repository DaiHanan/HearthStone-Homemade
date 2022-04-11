package model;

import java.util.Collections;
import java.util.LinkedList;

import model.card.Card;
import model.card.attend.Attend;
import model.card.magic.Magic;
import model.card.magic.deluyi.Distortion;
import model.card.magic.fashi.Flame;
import model.card.magic.shengqi.Report;

public class Player extends Attend {
	//每回合水晶数
	public int turnCost = 0;
	//水晶过载数
	public int overloadCost = 0;
	//当前疲劳
	public int tiredCount = 0;
	//护甲值
	public int armor = 0;
	//技能
	public Magic skill;
	//技能次数
	public int skillTime = 1;
	//手牌和牌库
	public LinkedList<Card> hand;
	public LinkedList<Card> lib;
	
	public Player(Card[] cards, Magic skill) {
		//初始化血量、攻击力、水晶、属性和特性、描述、技能
		super(30, 0, 0, "玩家", Nature.Player, "一位普通的玩家");
		
		//初始化名称
		if(skill instanceof Flame)
			this.name = "法师";
		else if(skill instanceof Distortion)
			this.name = "德鲁伊";
		else if(skill instanceof Report)
			this.name = "圣骑士";
		
		this.skill = skill;
		LinkedList<Feature> features = new LinkedList<>();
		features.add(Feature.NORMAL);
		this.features = features;
		//初始化手牌和牌库
		hand = new LinkedList<>();
		lib = new LinkedList<>();
		InitLib(cards);
		//初始回合攻击次数
		super.atkTime = 1;
	}
	//初始化牌库，测试全为1 1白板
	protected void InitLib(Card[] cards) {
		for(int i = 0; i < cards.length; i++) {
			lib.add(cards[i]);
		}
		//打乱卡牌
		Collections.shuffle(lib);
	}
//	//重置玩家
//	public void reset() {
//		//初始化血量、攻击力、水晶、属性和特性、描述、技能
//		this.name = "玩家";
//		this.desc = "一位普通的玩家";
//		this.nature = Nature.Player;
//		this.hp = this.hpMax = 30;
//		this.cost = this.turnCost = this.overloadCost = 0;
//		this.armor = 0;
//		this.tiredCount = 0;
//		this.skillTime = 1;
//		this.skill = skill;
//		features.clear();
//		features.add(Feature.NORMAL);
//		//初始回合攻击次数
//		super.atkTime = 1;
//		//手牌和牌库
//		hand.clear();
//		lib = new LinkedList<>();
//		InitLib(cards);
//	}
}

package model.card.attend;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.Card;

public class Attend extends Card {//随从根类
	public int hp = 5;//血量
	public int hpMax = 5;//血量上限
	public int atk = 3;//攻击力
	public int atkMin = 0;//攻击力下限
	public int atkMax = atk;//攻击力上限
	public boolean canAtk = true;//是否能够攻击
	
	public boolean isFroozen = false;//是否被冰冻
	public boolean froozenNow = false;//当前回合被冰冻
	
	public int atkTime = 0;//攻击次数(初下场时无法攻击)
	//public CardType type = CardType.Attend;
	public Nature nature = Nature.None;//属性，初始为无
	//public Feature features = Feature.NORMAL;//特性，初始为普通
	public LinkedList<Feature> features = new LinkedList<>();//特性数组，需手动添加
	
	public Attend() {
		//默认数据
	}
	public Attend(int hp, int atk, int cost) {
		//初始化数据
		this.hp = this.hpMax = hp;
		this.atk = this.atkMax = atk;
		this.cost = cost;
	}
	public Attend(int hp, int atk, int cost, String name, Nature nature, String desc) {
		//初始化数据
		this.hp = this.hpMax = hp;
		this.atk = this.atkMax = atk;
		this.cost = cost;
		this.name = name;
		//this.features = features;//特性
		this.nature = nature;//属性
		this.desc = desc;
		//this.hasEffect = hasEffect;
	}
	public Attend(Attend attend) {
		//初始化数据
		this.hp = attend.hp;
		this.hpMax = attend.hpMax;
		this.atkMax = attend.atkMax;
		this.atk = attend.atk;
		this.cost = attend.cost;
		this.name = attend.name;
		this.features.addAll(attend.features);//特性
		this.nature = attend.nature;//属性
		this.desc = attend.desc;
		this.atkTime = attend.atkTime;
		//this.hasEffect = attend.hasEffect;
	}
	
	//是否有战吼
	public boolean hasWarCry = false;
	//战吼
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		//提示触发战吼
		if(hasWarCry)
			JOptionPane.showMessageDialog(con.mainV, "触发 " + this.name + " 的战吼", Config.title, 0, Config.scaredIcon);
		return true;
	}
	
	//是否有亡语
	public boolean hasDeadWords = false;
	//亡语
	public void deadWords(Controller con, int nowId) {
		//提示触发亡语
		if(hasDeadWords)
			JOptionPane.showMessageDialog(con.mainV,"触发 " + this.name + " 的亡语", Config.title, 0, Config.scaredIcon);
	}
	
	//是否有回合结束效果
	public boolean hasEndTurnEffect = false;
	//回合结束效果
	public void endTurnEffect(Controller con, int nowId) {
		if(hasEndTurnEffect)
			JOptionPane.showMessageDialog(con.mainV, "触发" + this.name + "的回合结束效果", Config.title, 0, Config.scaredIcon);
	}

	
//	public void setHp(int hp) {this.hp = hp;};
//	public int getHp() {return hp;};
//	
//	public void setAtk(int atk) {this.atk = atk;};
//	public int getAtk() {return atk;};
//	
//	public void setFeature(Feature feature) {this.feature = feature;};
//	public Feature getFeature() {return feature;};
//	
//	public void setNature(Nature nature) {this.nature = nature;};
//	public Nature getNature() {return nature;};
}



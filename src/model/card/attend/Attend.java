package model.card.attend;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.Card;

public class Attend extends Card {//��Ӹ���
	public int hp = 5;//Ѫ��
	public int hpMax = 5;//Ѫ������
	public int atk = 3;//������
	public int atkMin = 0;//����������
	public int atkMax = atk;//����������
	public boolean canAtk = true;//�Ƿ��ܹ�����
	
	public boolean isFroozen = false;//�Ƿ񱻱���
	public boolean froozenNow = false;//��ǰ�غϱ�����
	
	public int atkTime = 0;//��������(���³�ʱ�޷�����)
	//public CardType type = CardType.Attend;
	public Nature nature = Nature.None;//���ԣ���ʼΪ��
	//public Feature features = Feature.NORMAL;//���ԣ���ʼΪ��ͨ
	public LinkedList<Feature> features = new LinkedList<>();//�������飬���ֶ����
	
	public Attend() {
		//Ĭ������
	}
	public Attend(int hp, int atk, int cost) {
		//��ʼ������
		this.hp = this.hpMax = hp;
		this.atk = this.atkMax = atk;
		this.cost = cost;
	}
	public Attend(int hp, int atk, int cost, String name, Nature nature, String desc) {
		//��ʼ������
		this.hp = this.hpMax = hp;
		this.atk = this.atkMax = atk;
		this.cost = cost;
		this.name = name;
		//this.features = features;//����
		this.nature = nature;//����
		this.desc = desc;
		//this.hasEffect = hasEffect;
	}
	public Attend(Attend attend) {
		//��ʼ������
		this.hp = attend.hp;
		this.hpMax = attend.hpMax;
		this.atkMax = attend.atkMax;
		this.atk = attend.atk;
		this.cost = attend.cost;
		this.name = attend.name;
		this.features.addAll(attend.features);//����
		this.nature = attend.nature;//����
		this.desc = attend.desc;
		this.atkTime = attend.atkTime;
		//this.hasEffect = attend.hasEffect;
	}
	
	//�Ƿ���ս��
	public boolean hasWarCry = false;
	//ս��
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		//��ʾ����ս��
		if(hasWarCry)
			JOptionPane.showMessageDialog(con.mainV, "���� " + this.name + " ��ս��", Config.title, 0, Config.scaredIcon);
		return true;
	}
	
	//�Ƿ�������
	public boolean hasDeadWords = false;
	//����
	public void deadWords(Controller con, int nowId) {
		//��ʾ��������
		if(hasDeadWords)
			JOptionPane.showMessageDialog(con.mainV,"���� " + this.name + " ������", Config.title, 0, Config.scaredIcon);
	}
	
	//�Ƿ��лغϽ���Ч��
	public boolean hasEndTurnEffect = false;
	//�غϽ���Ч��
	public void endTurnEffect(Controller con, int nowId) {
		if(hasEndTurnEffect)
			JOptionPane.showMessageDialog(con.mainV, "����" + this.name + "�ĻغϽ���Ч��", Config.title, 0, Config.scaredIcon);
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



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
	//ÿ�غ�ˮ����
	public int turnCost = 0;
	//ˮ��������
	public int overloadCost = 0;
	//��ǰƣ��
	public int tiredCount = 0;
	//����ֵ
	public int armor = 0;
	//����
	public Magic skill;
	//���ܴ���
	public int skillTime = 1;
	//���ƺ��ƿ�
	public LinkedList<Card> hand;
	public LinkedList<Card> lib;
	
	public Player(Card[] cards, Magic skill) {
		//��ʼ��Ѫ������������ˮ�������Ժ����ԡ�����������
		super(30, 0, 0, "���", Nature.Player, "һλ��ͨ�����");
		
		//��ʼ������
		if(skill instanceof Flame)
			this.name = "��ʦ";
		else if(skill instanceof Distortion)
			this.name = "��³��";
		else if(skill instanceof Report)
			this.name = "ʥ��ʿ";
		
		this.skill = skill;
		LinkedList<Feature> features = new LinkedList<>();
		features.add(Feature.NORMAL);
		this.features = features;
		//��ʼ�����ƺ��ƿ�
		hand = new LinkedList<>();
		lib = new LinkedList<>();
		InitLib(cards);
		//��ʼ�غϹ�������
		super.atkTime = 1;
	}
	//��ʼ���ƿ⣬����ȫΪ1 1�װ�
	protected void InitLib(Card[] cards) {
		for(int i = 0; i < cards.length; i++) {
			lib.add(cards[i]);
		}
		//���ҿ���
		Collections.shuffle(lib);
	}
//	//�������
//	public void reset() {
//		//��ʼ��Ѫ������������ˮ�������Ժ����ԡ�����������
//		this.name = "���";
//		this.desc = "һλ��ͨ�����";
//		this.nature = Nature.Player;
//		this.hp = this.hpMax = 30;
//		this.cost = this.turnCost = this.overloadCost = 0;
//		this.armor = 0;
//		this.tiredCount = 0;
//		this.skillTime = 1;
//		this.skill = skill;
//		features.clear();
//		features.add(Feature.NORMAL);
//		//��ʼ�غϹ�������
//		super.atkTime = 1;
//		//���ƺ��ƿ�
//		hand.clear();
//		lib = new LinkedList<>();
//		InitLib(cards);
//	}
}

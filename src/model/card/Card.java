package model.card;

public class Card {
	public String name = "��ʼ��Ƭ";//����
	public int cost = 1;//���ã�������ҵ��ڿ���ˮ����
	public String desc = "";//����
	//public boolean hasEffect = false;
	
	public Card() {};
	public Card(String name, int cost, String desc) {
		this.name = name;
		this.cost = cost;
		this.desc = desc;
		//this.hasEffect = hasEffect;
	}
	public Card(Card card) {
		this.name = card.name;
		this.cost = card.cost;
		this.desc = card.desc;
		//this.hasEffect = card.hasEffect;
	}
	
	//public void effect() {};
}

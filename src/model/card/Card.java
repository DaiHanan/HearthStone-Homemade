package model.card;

public class Card {
	public String name = "初始卡片";//名称
	public int cost = 1;//费用，对于玩家等于可用水晶数
	public String desc = "";//描述
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

package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * 法师技能
 * */
public class Flame extends Magic {
	public int damage = 1;
	
	public Flame() {
		super(2, "火冲");
		//暂未考虑法强
		this.desc = "对目标造成 " + damage + "点法术伤害";
	}
	//给子类使用
	protected Flame(int cost, String name, int damage) {
		this.name = name;
		this.cost = cost;
		this.damage = damage;
		//暂未考虑法强
		this.desc = "对目标造成 " + damage + "点法术伤害";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		int[] target = con.inquiryTarget(1, nowId);
		//使用成功
		if(target[0] != -1) {
			//修改数据
			con.changeStandCardHp(target[0], target[1], 0 - damage);
			return true;
		}
		//使用失败
		return false;
	}
}

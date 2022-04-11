package model.card.magic.deluyi;

import model.card.attend.neutral.Elf;
import model.card.magic.Magic;
import config.*;
import controller.Controller;

import java.lang.Math;
/**
 * 精灵之森
 * */
public class ElvenForest extends Magic{
	public ElvenForest() {
		super(4,"精灵之森");
		this.desc="你每有一张手牌，便召唤一个1/1的小精灵";
		// TODO 自动生成的构造函数存根
	}
	public boolean effect(Controller con, int nowId) {
		//获取能添加最大随从数量
		int number=Math.min(con.player[nowId].hand.size(),Config.standMaxCount-con.getStandCardCount(nowId));
		// 对小精灵上场
		for (int i=0;i<number;i++)
		{	Elf elf=new Elf();
		    System.out.println(con.addStandCard(elf, nowId, -1));
		}
		System.out.println(number);
		return true;
	}
}

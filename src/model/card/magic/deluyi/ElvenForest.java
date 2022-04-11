package model.card.magic.deluyi;

import model.card.attend.neutral.Elf;
import model.card.magic.Magic;
import config.*;
import controller.Controller;

import java.lang.Math;
/**
 * ����֮ɭ
 * */
public class ElvenForest extends Magic{
	public ElvenForest() {
		super(4,"����֮ɭ");
		this.desc="��ÿ��һ�����ƣ����ٻ�һ��1/1��С����";
		// TODO �Զ����ɵĹ��캯�����
	}
	public boolean effect(Controller con, int nowId) {
		//��ȡ���������������
		int number=Math.min(con.player[nowId].hand.size(),Config.standMaxCount-con.getStandCardCount(nowId));
		// ��С�����ϳ�
		for (int i=0;i<number;i++)
		{	Elf elf=new Elf();
		    System.out.println(con.addStandCard(elf, nowId, -1));
		}
		System.out.println(number);
		return true;
	}
}

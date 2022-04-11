package model.card.attend.deluyi;

import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 禁忌古树
 * */
public class AncientTaboo extends Attend {
	public AncientTaboo() {
		super(1, 1, 1, "禁忌古树", Nature.None, "战吼：消耗所有你的法力值，每消耗一点则使其获得+1/+1");
		this.features.add(Feature.NORMAL);
		
		this.hasWarCry = true;
	}
	
	@Override
	public boolean warCry(Controller con, int nowId, int nowIdx) {
		super.warCry(con, nowId, nowIdx);
		
		int number= con.player[nowId].cost - 1;
		
		this.atk += number;
		this.hp = this.hpMax += number;
//		int idxs[] = new int[Config.standMaxCount + 1];
//		int size = 0;
//		for(int j = 1; j < Config.standMaxCount + 1; j++)
//			if(con.mainV.stands[nowId][j] != null) {
//				idxs[size] = j;
//				size++;
//			}
//		for(int i=0;i<number;i++)
//		{
//			//获取随机友方随从
//			int[] IdIdx = con.randomTarget(5, nowId);
//			if(IdIdx[0] != -1) {
//				con.mainV.stands[IdIdx[0]][IdIdx[1]].attend.hp += 1;
//				con.mainV.stands[IdIdx[0]][IdIdx[1]].attend.hpMax += 1;
//				con.changeStandCardAtk(IdIdx[0], IdIdx[1], 1);
//			}
//		}
		
		con.changeCosts(1, nowId, 0 - number);
		
		return true;
	}
}

package model.card.attend.fashi;

import config.Config;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.attend.Attend;

/**
 * 大螺丝
 * */
public class RagnarosFirelord extends Attend {
	public RagnarosFirelord() {
		super(8, 8, 8, "炎魔之王", Nature.None, "无法攻击 在回合结束时随机对敌人 造成8点伤害");
		this.features.add(Feature.NORMAL);
		
		this.canAtk=false;
		this.hasEndTurnEffect = true;
	}
	
	@Override
	public void endTurnEffect(Controller con, int nowId) {
		super.endTurnEffect(con, nowId);
		//站场下标数组
		int oppoId = 1 - nowId;
		int idxs[] = new int[Config.standMaxCount + 1];
		//获得敌方场上单位个数,并存储下标
		int size = 0;
		for(int j = 0; j < Config.standMaxCount + 1; j++)
			if(con.mainV.stands[oppoId][j] != null) {
				idxs[size] = j;
				size++;
			}
		//获得随机数
		int ran = (int)Math.floor(Math.random() * size);
		//修改该随从血量
		con.changeStandCardHp(oppoId, idxs[ran], -8);
	
		
	}
}

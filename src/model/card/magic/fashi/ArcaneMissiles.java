package model.card.magic.fashi;

import controller.Controller;
import model.card.magic.Magic;

/**
 * 奥术飞弹
 * */
public class ArcaneMissiles extends Magic {
	public ArcaneMissiles() {
		super(1, "奥术飞弹");
		this.desc = "造成3点伤害，随机分配给敌方角色";
	}
	
	@Override
	public boolean effect(Controller con, int nowId) {
		//获得随机目标
		int IdIdx[] = con.randomTarget(2, nowId);
		if(IdIdx[0] == -1)
			return false;
		for(int i = 0; i < 3; i++) {
			//修改该随从血量
			if(IdIdx[0] != -1)
				con.changeStandCardHp(IdIdx[0], IdIdx[1], -1);
			//获得随机目标
			IdIdx = con.randomTarget(2, nowId);
//			//站场下标数组
//			int idxs[] = new int[Config.standMaxCount + 1];
//			//获得敌方场上单位个数,并存储下标
//			int size = 0;
//			for(int j = 0; j < Config.standMaxCount + 1; j++)
//				if(con.mainV.stands[oppoId][j] != null) {
//					idxs[size] = j;
//					size++;
//				}
//			//获得随机数
//			int ran = (int)Math.floor(Math.random() * size);
		}
		
		return true;
	}
}

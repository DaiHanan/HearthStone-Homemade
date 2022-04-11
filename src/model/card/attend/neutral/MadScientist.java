package model.card.attend.neutral;

import java.util.ListIterator;
import controller.Controller;
import model.Feature;
import model.Nature;
import model.card.Card;
import model.card.attend.Attend;

/**
 * ���Ŀ�ѧ��
 * */
public class MadScientist extends Attend {
	public MadScientist() {
		super( 2, 2, 3, "���Ŀ�ѧ��", Nature.None, "����:��һ����Ӵ��ƿ������ϳ�");
		this.features.add(Feature.NORMAL);
		
		hasDeadWords = true;
	}
	
	@Override
		public void deadWords(Controller con, int nowId) {
			super.deadWords(con, nowId);
			
			for(ListIterator<Card> iter = con.player[nowId].lib.listIterator(); iter.hasNext();) {
				Card card = iter.next();
				if(card instanceof Attend) {
					Attend attend = (Attend)card;
					con.addStandCard(attend, nowId, -1);
					con.delOneLib(nowId, attend);
					break;
				}
			}
		}
}
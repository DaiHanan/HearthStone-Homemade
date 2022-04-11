package controller;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import config.Config;
import config.Deck;
import model.Feature;
import model.Nature;
import model.Player;
import model.card.Card;
import model.card.attend.Attend;
import model.card.magic.neutral.LuckyCoin;
import view.AboutView;
import view.MainView;
import view.StandCard;

public class Controller {
	public Player[] player;//��ɫ����
	public MainView mainV;
	
	public int turn = -1;//��ǰ�غ���
	
	//Thread mainT = new mainThread();//��Ϸ���߳�
	Random random = new Random();
	
	public Controller() {
		//��ʼ������
		new Deck();
		//��ʼ����ɫ
		player = new Player[2];
		player[0] = new Player(Deck.decks[Deck.now[0]], Deck.skills[Deck.now[0]]);
		player[1] = new Player(Deck.decks[Deck.now[1]], Deck.skills[Deck.now[1]]);
		//��ʼ��������
		mainV = new MainView();
		//�������ģ��
		mainV.setStandCard(player[0], 0, 0);
		mainV.setStandCard(player[1], 0, 1);
		/**���ü�����*/
		new Listen(this);
		Listen.setTurnChangeListener(this.mainV.turnChange);//�غϽ���
		for(int i = 0; i < 2; i++)
			Listen.setSkillListener(this.mainV.skillBtns[i]);//����
		Listen.setAboutListener(this.mainV.aboutMI);//˵������
		Listen.setChooseListener(this.mainV.chooseMI);//ѡ��Ƭ����
		//���߳̿���
		(new mainThread()).start();
		//��������ҳ��
		new AboutView(this);
	}
	
	//��õ�ǰ�غ����id
	public int getNowId() {
		return turn % 2;
	}
	
	//���ƿ��һ�ſ�Ƭ(��ɾ��)
	public Card getOneLib(int id) {
		if((id == 0 || id == 1) && player[id].lib.size() > 0)
			return player[id].lib.get(random.nextInt(player[id].lib.size()));
		return null;
	}
	//���ƿ�ɾ��ָ����Ƭ,�������ƿ�ʣ�࿨�Ʊ�ǩ
	public void delOneLib(int id, Card card) {
		if(id == 0 || id == 1) {
			//ע�����card�����ڲ�����ʾ
			if(card != null) {
				player[id].lib.remove(card);
				mainV.setLibLable(id, player[id].lib.size());
			}
		}
	}
	//���ƿ��һ����(ɾ��)
	public Card drawOneLib(int id) {
		Card card = getOneLib(id);
		//System.out.println(card.desc + "3");
		delOneLib(id, card);
		return card;
	}
	//�������ָ����Ƭ
	public void addOneHand(int id, Card card) {
		if(id == 0 || id == 1) {
			if(player[id].hand.size() < Config.handMaxCount) {
				//����
				//System.out.println(card.desc + "3");
				player[id].hand.add(card);
				//������ư�ť
				this.mainV.addHandCard(id);
				//Ϊ��ǰ���������ĩβ�İ�ť���ü�����
				Listen.setHandListener(this.mainV.hands.get(id).get(this.player[id].hand.size() - 1));
			}
			//else//��ʾ
		}
	}
	//ɾ������ָ����Ƭ
	public void delOneHand(int id, Card card) {
		if(id == 0 || id == 1) {
			//ע�����card�����ڲ�����ʾ
			player[id].hand.remove(card);
			this.mainV.delHandCard(id, 1);
		}
	}
	//����
	public void drawCard(int id, int count) {
		for(int i = 0; i < count; i++) {
			//��һ����
			Card card = drawOneLib(id);
			if(card == null) {
				//�ƿ�Ϊ����ƣ��
				//System.out.println(player[id].tiredCount);
				changeStandCardHp(id, 0, 0 - ++player[id].tiredCount);
			}
			else {
				//���û�������
				//if(player[id].hand.size() < Config.handMaxCount) {
				addOneHand(id, card);
				//}
			}
		}
	}
	//������ӻ��๥��(���1�������2)
	public void attendFight(int id_1, int idx_1, int id_2, int idx_2) {
		//���˫����Ƭ
		StandCard card = mainV.stands[id_1][idx_1];
		StandCard oppoCard = mainV.stands[id_2][idx_2];
		//�໥����
		//���˫������������ֹ������������Ϊ��
		int oppoAtk = oppoCard.attend.atk;
		int nowAtk = card.attend.atk;
		//��������Ǿ綾�ҶԷ���ΪӢ�ۣ�������Ϊ�Է�Ѫ��
		if(card.attend.features.contains(Feature.Poison) && 
				oppoCard.attend.nature != Nature.Player) {
			nowAtk = oppoCard.attend.hp;
		}
		if(oppoCard.attend.features.contains(Feature.Poison) && 
				card.attend.nature != Nature.Player) {
			oppoAtk = card.attend.hp;
		}
		//�з�
		changeStandCardHp(id_2, idx_2, 0 - nowAtk);
		//�ѷ�
		if(idx_2 != 0)//������Ӣ�ۣ��ÿ�Ƭ�����ܵ��˺�
			changeStandCardHp(id_1, idx_1, 0 - oppoAtk);
		//���¹���������ˢ�¿ɹ����߿�
		if(--card.attend.atkTime <= 0) {
			card.borderOff();
		}
	}
	//�ı俨Ƭ(���)Ѫ��ֵ, +��Ѫ-��Ѫ
	public void changeStandCardHp(int id, int idx, int value) {
		//��ÿ�Ƭ����Ӷ���
		Attend attend = this.mainV.stands[id][idx].attend;
		//��Ѫ
		if(value >= 0) 
			attend.hp = Math.min(attend.hpMax, attend.hp + value);
		//��Ѫ
		else {
			//�����Ӣ�����жϻ���
			if(attend instanceof Player) {
				value = changePlayerArmor(id, value);
			}
			//�����ʥ�����ԣ��ֵ��˺���ȡ��ʥ������
			if(attend.features.contains(Feature.Shield)) {
				attend.features.remove(Feature.Shield);
				//��������ֻ��ʥ�����ԣ�������ͨ����
				if(attend.features.size() == 0)
					attend.features.add(Feature.NORMAL);
				//�˺�Ϊ0
				value = 0;
			}
			attend.hp += value;//valueΪ��ֵ
		}
		/**���¿�Ƭ��ʾ*/
		boolean isDead = this.mainV.setStandCard(attend, idx, id);
		//��������
		if(isDead)
			attend.deadWords(this, id);
//		//�ҵ��ÿ�Ƭ
//		for(int i = 0; i < 2; i++) {
//			StandCard[] stand = this.mainV.stands[i];
//			for(int j = 0; j < Config.standMaxCount + 1; j++) {
//				StandCard card = stand[j];
//				if(card != null && card.attend == attend) {
//					//card.hpLabel.setText("" + attend.hp);
//					//card.hpLabel.repaint();
//					this.mainV.setStandCard(attend, j, i);
//				}
//			}
//		}
		//������Ѫ��
		checkGameOver();
	}
	
	public void changeStandCardAtk(int id, int idx, int value) {
		//��ÿ�Ƭ����Ӷ���
		Attend attend = this.mainV.stands[id][idx].attend;
		//���� 
		if (value < 0)
		attend.atk = Math.max(0, attend.atk + value);
		//�ӹ�
		if (value >= 0)
			attend.atk+=value;
		//ˢ����ͼ
		this.mainV.setStandCard(attend, idx, id);
	}
	
	//�ı���һ���ֵ�������׼���ʱ�һ��ײ������󷵻�����˺������򷵻�0
	//���ֶ�ˢ����ͼ
	public int changePlayerArmor(int id, int value) {
		Player nowPlayer = player[id];
		if(value >= 0) {
			nowPlayer.armor += value;
			return 0;
		}
		else {
			int armor = nowPlayer.armor;
			nowPlayer.armor = Math.max(nowPlayer.armor + value, 0);
			value = Math.min(value + armor, 0);
			return value;
		}
	}
	
	//�����Ϸ�Ƿ����
	public void checkGameOver() {
		int dieId = -1;
		if(player[0].hp <= 0 && player[1].hp <= 0)//ͬ���ھ�
			dieId = 2;
		else if(player[0].hp <= 0)//��Ҷ���ʤ
			dieId = 1;
		else if(player[1].hp <= 0) //���һ��ʤ
			dieId = 0;
		if(dieId != -1)
			gameOver(dieId);
	}
	//��Ϸ����,dieId:0-���һ 1-��Ҷ� 2-ͬ���ھ�
	public void gameOver(int dieId) {
		if(dieId >= 0 && dieId < 3) {
			String msg = "���" + (1 + dieId) + "��ʤ��";
			if(dieId == 2)
				msg = "���ʾ��ף�ͬ���ھ���";
			JOptionPane.showMessageDialog(mainV, msg, Config.title, 
					0, Config.winIcon);
			int choice = JOptionPane.showConfirmDialog(mainV, "�Ƿ�����һ�֣�", Config.title, 
					JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
			
			if(choice == JOptionPane.YES_OPTION) 
				this.regame();
			else {
				//�رմ���
				this.mainV.dispose();
				this.mainV.setVisible(false);
			}
		}
	}
	//�ؿ���Ϸ
	public void regame() {
		//��ʼ����ɫ
		player = new Player[2];
		player[0] = new Player(Deck.decks[Deck.now[0]], Deck.skills[Deck.now[0]]);
		player[1] = new Player(Deck.decks[Deck.now[1]], Deck.skills[Deck.now[1]]);
		//��ʼ��������
		this.mainV.regame();
		//�������ģ��
		mainV.setStandCard(player[0], 0, 0);
		mainV.setStandCard(player[1], 0, 1);
		/**���ü�����*/
		for(int i = 0; i < 2; i++)
			Listen.setSkillListener(this.mainV.skillBtns[i]);//����
		//���ûغ��������߳̿���
		this.turn = -1;
		(new mainThread()).start();
	}
	//�غϸı�
	public void turnChange() {
		//�ȴ����ϻغϵĻغϽ���Ч��(��ֻ�������غ����Ч��)
		int lastNowId = getNowId();
		for(int i = 1; i < Config.standMaxCount + 1; i++)
			if(mainV.stands[lastNowId][i] != null && mainV.stands[lastNowId][i].attend.hasEndTurnEffect)
				mainV.stands[lastNowId][i].attend.endTurnEffect(this, lastNowId);
		
		//mainThread.interrupt();
		(new mainThread()).start();
	}
	
	/**������Ŀ��
	 * type:1_ȫ���ѡĿ��
	 * 	   :2_�з���ѡĿ��
	 *     :3_�ҷ���ѡĿ��
	 *     :4_�з��ɽ���Ŀ��
	 *     :5_�ҷ���ѡ���
	 *     :6_�з���ѡ���
	 * nowId:ʹ�÷�Id
	 * return:int[0]:���Id,-1��û��Ŀ��
	 *       :int[1]:���Idx
	 * */
	public int[] randomTarget(int type, int nowId) {
		//�з�id
		int oppoId = 1 - nowId;
		//��������
		int[] res = new int[2];
		
		//վ���±����飬�洢���ݣ���λ��:idΪ0����ң�ʮλ��:idΪ1�����
		int idxs[] = new int[2 * Config.standMaxCount + 2];
		//�������ͻ�ÿ�ѡ��λ����,���洢�±�
		int count = 0;
		
		switch(type) {
		//type - 1 ȫ���ѡĿ��
		case 1:
			//(δ����Ǳ�е�����)
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < Config.standMaxCount + 1; j++) {
					if(mainV.stands[i][j] != null) {
						idxs[count] = j + i * 10;
						count++;
					}
				}
			}
			break;
		//type - 2 �з���ѡĿ��
		case 2:
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[oppoId][i] != null) {
					idxs[count] = i + oppoId * 10;
					count++;
				}
			}
			break;
		//type - 3 �ҷ���ѡĿ��
		case 3:
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[nowId][i] != null) {
					idxs[count] = i + nowId * 10;
					count++;
				}
			}
			break;
		//type - 4 �з��ɽ���Ŀ��
		case 4:
			//(δ����Ǳ�е�����)
			boolean hasTaunt = false;//�Ƿ��г���
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				StandCard stand = mainV.stands[oppoId][i];
				if(stand != null) {
					//���û�г���ͼ�鳰������
					if(hasTaunt == false && stand.attend.features.contains(Feature.Taunt) == true) {
						hasTaunt = true;
						count = 0;
					}
					
					idxs[count] = i + oppoId * 10;
					
					//������޳������ԣ�ֱ�Ӽ�������
					if(hasTaunt == false) 
						count++;
					//������ڳ�����Ҫ�жϸÿ�Ƭ����Ƿ��г�������
					else if(stand.attend.features.contains(Feature.Taunt) == true) {
						count++;
					}
				}
			}
			break;
		//type - 5 �ҷ���ѡ���
		case 5:
			for(int i = 1; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[nowId][i] != null) {
					idxs[count] = i + nowId * 10;
					count++;
				}
			}
			break;
		//type - 6 �ҷ���ѡ���
		case 6:
			for(int i = 1; i < Config.standMaxCount + 1; i++) {
				if(mainV.stands[oppoId][i] != null) {
					idxs[count] = i + oppoId * 10;
					count++;
				}
			}
			break;
		}
		
		//����е�λ
		if(count > 0) {
			//��������
			int ran = (int)Math.floor(Math.random() * count);
			//���������������id
			res[0] = idxs[ran] / 10;
			//������������±�
			res[1] = idxs[ran] % 10;
		}
		else
			res[0] = -1;
		
		return res;
	}
	
	/**ѯ��Ŀ��
	 * type:1_ȫ���ѡĿ��
	 * 	   :2_�з���ѡĿ��
	 *     :3_�ҷ���ѡĿ��
	 *     :4_�з��ɽ���Ŀ��
	 *     :5_�ҷ���ѡ���
	 *     :6_�з���ѡ���
	 *     :7_ȫ����ѡ���
	 * nowId:ʹ�÷�Id
	 * return:int[0]:ѡ��Id,��ѡ��ʧ��IdΪ-1
	 *       :int[1]:ѡ��Idx
	 * */
	public int[] inquiryTarget(int type, int nowId) {
		//�з�id
		int oppoId = 1 - nowId;
		//��������
		int[] res = new int[2];
		//ѡ������
		LinkedList<String> list = new LinkedList<>();
		switch(type) {
		//type - 1 ȫ���ѡĿ��
		case 1:
			//(δ����Ǳ�е�����)
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < Config.standMaxCount + 1; j++) {
					if(mainV.stands[i][j] != null) {
						String str;
						if(i == nowId)
							str = "�ҷ�";
						else
							str = "�з�";
						if(j == 0)
							str += "Ӣ��";
						else
							str += j + "��λ";
						//�����ҷ�Ӣ�ۡ��з�3��λ
						list.add(str);
					}
				}
			}
			break;
		//type - 2 �з���ѡĿ��
		case 2:
			for(int j = 0; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[oppoId][j] != null) {
					String str = "�з�";
					if(j == 0)
						str += "Ӣ��";
					else
						str += j + "��λ";
					//�����з�3��λ
					list.add(str);
				}
			}
			break;
		//type - 3 �ҷ���ѡĿ��
		case 3:
			for(int j = 0; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[nowId][j] != null) {
					String str = "�ҷ�";
					if(j == 0)
						str += "Ӣ��";
					else
						str += j + "��λ";
					//�����з�3��λ
					list.add(str);
				}
			}
			break;
		//type - 4 �з��ɽ���Ŀ��
		case 4:
			//(δ����Ǳ�е�����)
			boolean hasTaunt = false;//�Ƿ��г���
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				StandCard stand = mainV.stands[oppoId][i];
				if(stand != null) {
					//���û�г���ͼ�鳰������
					if(hasTaunt == false && stand.attend.features.contains(Feature.Taunt) == true) {
						hasTaunt = true;
						list.clear();//���֮ǰ�����(�ǳ���)
					}
					String str = "�з�";
					if(i == 0)
						str += "Ӣ��";
					else
						str += i + "��λ";
					//������޳������ԣ�ֱ�Ӽ�������
					if(hasTaunt == false)
						list.add(str);
					//������ڳ�����Ҫ�жϸÿ�Ƭ����Ƿ��г�������
					else if(stand.attend.features.contains(Feature.Taunt) == true) {
						list.add(str);
					}
				}
			}
			break;
		//type - 5 �ҷ���ѡ���
		case 5:
			for(int j = 1; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[nowId][j] != null) {
					String str = "�ҷ�" + j + "��λ";
					//�����ҷ�3��λ
					list.add(str);
				}
			}
			break;
		//type - 6 �ҷ���ѡ���
		case 6:
			for(int j = 1; j < Config.standMaxCount + 1; j++) {
				if(mainV.stands[oppoId][j] != null) {
					String str = "�з�" + j + "��λ";
					//�����з�3��λ
					list.add(str);
				}
			}
			break;
		//type - 7 ȫ����ѡ���
		case 7:
			for(int i = 0; i < 2; i++) {
				for(int j = 1; j < Config.standMaxCount + 1; j++) {
					if(mainV.stands[i][j] != null) {
						String str;
						if(i == nowId)
							str = "�ҷ�";
						else
							str = "�з�";
						
						str += j + "��λ";
						//�����з�3��λ
						list.add(str);
					}
				}
			}
			break;
		}
		//���û�п�ѡĿ��
		if(list.size() == 0) {
			JOptionPane.showMessageDialog(mainV, "�޿�ѡĿ��", Config.title, 0, Config.scaredIcon);
			res[0] = -1;
		}
		//�п�ѡĿ��
		else {
			//������ѡ��
			String choiceStr = (String)JOptionPane.showInputDialog(mainV, "��ѡ��Ŀ��", 
					Config.title, 0, Config.inqueryIcon, list.toArray(), list.getFirst());
			//ѡ��ɹ�
			if(choiceStr != null) {
				//���ѡ���Ŀ��λ��
				if(choiceStr.substring(0, 2).equals("�ҷ�"))
					res[0] = nowId;//�ҷ�
				else
					res[0] = 1 - nowId;//�з�
				if(choiceStr.substring(2, 4).equals("Ӣ��"))
					res[1] = 0;
				else
					res[1] = Integer.parseInt(choiceStr.substring(2, 3));
			}
			//ѡ��ʧ��
			else
				res[0] = -1;
		}
		
		return res;
	}
	
	//������ӱ���
	public void setAttendFroozen(int id, int idx, boolean isFroozen) {
		this.mainV.stands[id][idx].attend.isFroozen = isFroozen;
		this.mainV.stands[id][idx].attend.froozenNow = isFroozen;
		this.mainV.stands[id][idx].borderNow();
	}
	
	//��õ�ǰվ���������
	public int getStandCardCount(int id) {
		int count = 0;
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(this.mainV.stands[id][i] != null)
				count++;
		}
		
		return count;
	}
	
	//ֱ������վ�����,������Ϊ�����±꣬��Ϊ-1������
	public boolean addStandCard(Attend attend, int id, int noIdx) {
		for(int i = 1; i < Config.standMaxCount + 1; i++) {
			if(noIdx == i)
				continue;
			//�ҵ�λ�ü�����
			if(this.mainV.stands[id][i] == null) {
				this.mainV.setStandCard(attend, i, id);
				return true;
			}
		}
		//�޷�����(û��λ��)
		return false;
	}
	
	/**
	 * �޸�ˮ��
	 * type:1_�޸ĵ�ǰˮ��
	 * 	   :2_�޸Ļغ�ˮ��������ʱ�����ӵ�ǰˮ��
	 * 	   :3_�޸Ļغ�ˮ��������ʱ���ӵ�ǰˮ��
	 * */
	public void changeCosts(int type, int id, int value) {
		switch(type) {
		//type:1_�޸ĵ�ǰˮ��
		case 1:
			//����
			if(value >= 0) {
				this.player[id].cost = Math.min
						(this.player[id].cost + value, Config.costMax);
			}
			//����
			if(value < 0) {
				this.player[id].cost = Math.max
						(this.player[id].cost + value, 0);
			}
			break;
		//2_�޸Ļغ�ˮ��������ʱ�����ӵ�ǰˮ��
		case 2:
			//����
			if(value >= 0) {
				this.player[id].turnCost = Math.min
						(this.player[id].turnCost + value, Config.costMax);
			}
			//����
			if(value < 0) {
				this.player[id].turnCost = Math.max
						(this.player[id].turnCost + value, 0);
				//ͬ�����ٵ�ǰˮ��
				this.player[id].cost = Math.min(this.player[id].turnCost, this.player[id].cost);
			}
			break;
		//3_�޸Ļغ�ˮ��������ʱ���ӵ�ǰˮ��
		case 3:
			//����
			if(value >= 0) {
				this.player[id].turnCost = Math.min
						(this.player[id].turnCost + value, Config.costMax);
				this.player[id].cost = Math.min
						(this.player[id].cost + value, Config.costMax);
			}
			//����
			if(value < 0) {
				this.player[id].turnCost = Math.max
						(this.player[id].turnCost + value, 0);
				//ͬ�����ٵ�ǰˮ��
				this.player[id].cost = Math.min(this.player[id].turnCost, this.player[id].cost);
			}
			break;
		}
		
		//ˢ����ͼ
		this.mainV.setCostPanes(id, this.player[id].cost, this.player[id].turnCost);
	}
	
	public static void main(String[] args) {
		new Controller();
	}
	
	//��Ϸ���߳�(�ڲ���)
	class mainThread extends Thread {
		@Override
		public void run() {
			super.run();
			
			//����غϸ���ǰ��ǰ�����վ�������������ж��Ƿ�Ϊ��ǰ�غϱ�����
			//����ǣ��򲻽ⶳ��������ǣ���ⶳ
			int nowId = getNowId();
			//���ж��Ƿ���ǰһ��
			if(nowId != -1) {
				//�ҷ�
				for(int i = 0; i < Config.standMaxCount + 1; i++) {
					if(mainV.stands[nowId][i] != null) {
						Attend attend = mainV.stands[nowId][i].attend;
						if(attend.froozenNow)
							attend.froozenNow = false;
						else if(attend.isFroozen)
							setAttendFroozen(nowId, i, false);
					}
				}
				//�з�
				int oppoId = nowId == 0 ? 1 : 0;
				for(int i = 0; i < Config.standMaxCount + 1; i++) {
					if(mainV.stands[oppoId][i] != null) {
						Attend attend = mainV.stands[oppoId][i].attend;
						if(attend.froozenNow)
							attend.froozenNow = false;
					}
				}
			}
			
			//���»غ������ж�����
			if(++turn > Config.turnMax) {
				//����غ����ޣ�ͬ���ھ�
				gameOver(2);
			}
			
			//��ǰ�غ����id�Ͷ���id
			nowId = getNowId();
			int oppoId = nowId == 0 ? 1 : 0;
			//��ҳ��ƣ���ʼ�غ�˫������4����
			if(turn == 0) {
				drawCard(nowId, 3);
				drawCard(oppoId, 3);
				//�з����һ��������Ӳ�ҡ�
				addOneHand(oppoId, new LuckyCoin());
			}
			drawCard(nowId, 1);
			/**������ҵĵ�ǰ�غϹ������ݲ�����������ˢ����Ҽ���ʹ�ô���*/
			for(int i = 0; i < 2; i++) {
				player[i].atk = 0;
				player[i].skillTime = 1;
				mainV.setStandCard(player[i], 0, i);
			}
			/**ˢ��ˮ��*/
			//���±��غ�ˮ������
			if(player[nowId].turnCost < Config.costMax)
				++player[nowId].turnCost;
			//���±��غϿ���ˮ��
			player[nowId].cost = Math.max(player[nowId].turnCost - player[nowId].overloadCost, 0);
			//���ù���ˮ��
			player[nowId].overloadCost = 0;
			//ˮ����ʾ
			mainV.setCostPanes(nowId, player[nowId].cost, player[nowId].turnCost);
			/**ˢ�¹�������(��δ���Ƿ�ŭ������)*/
			//ˢ�µ�ǰ�غ���ҵĴ���
			for(int i = 0; i < Config.standMaxCount + 1; i++) {
				//ˢ�µ�ǰ���
				if(mainV.stands[nowId][i] != null) {
					mainV.stands[nowId][i].attend.atkTime = 1;
					//�������Ϊ��ŭ�򹥻�����Ϊ2
					if(mainV.stands[nowId][i].attend.features.contains(Feature.Wind))
						mainV.stands[nowId][i].attend.atkTime = 2;
					//����ܹ��������ܹ��������߱������ˣ���ʾ�߿�
					if(mainV.stands[nowId][i].attend.atk > 0 && mainV.stands[nowId][i].attend.canAtk || 
							mainV.stands[nowId][i].attend.isFroozen)
						mainV.stands[nowId][i].borderNow();
				}
				//ˢ�µз����
				//�رչ����߿�
				if(mainV.stands[oppoId][i] != null) 
					mainV.stands[oppoId][i].borderOppo();
			}
			/**ˢ��������߿�*/
			mainV.backPanels[nowId].setBorder(Config.nowTurnBorder);
			mainV.backPanels[oppoId].setBorder(null);
		}
	}

}

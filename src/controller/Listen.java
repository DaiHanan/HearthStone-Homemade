package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import config.Config;
import model.Feature;
import model.IdBtn;
import model.Player;
import model.card.Card;
import model.card.attend.Attend;
import model.card.magic.Magic;
import view.AboutView;
import view.ChooseView;
import view.StandCard;

//��Ӽ�����
public class Listen {
	static Controller con;//��������
	
	public Listen(Controller con) {
		Listen.con = con;
	}
	//���ûغϽ�����ť������
	public static void setTurnChangeListener(JButton btn) {
		btn.addActionListener(new turnChangeListener());
	}
	//�غϽ���������
	private static class turnChangeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			con.turnChange();
		}
	}
	
	//���ò˵���_˵���ļ�����
	public static void setAboutListener(JMenuItem item) {
		item.addActionListener(new aboutListener());
	}
	//�غϽ���������
	private static class aboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AboutView(con);
		}
	}
	
	//���ò˵���_ѡ����ļ�����
	public static void setChooseListener(JMenuItem item) {
		item.addActionListener(new ChooseListener());
	}
	//�غϽ���������
	private static class ChooseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new ChooseView(con);
		}
	}
	
	
	//�������ư�ť������
	public static void setHandListener(IdBtn btn) {
		btn.addMouseListener(new HandListener());
	}
	//���ư�ť������
	private static class HandListener extends MouseAdapter {
		//int descFlag = 0;//��descFlagΪ1ʱ��ʾ����
		Thread descShow;
		
		@Override
		public void mouseEntered(MouseEvent e) {//�������ʱ
			super.mouseEntered(e);
			
			//��ð�ť
			IdBtn btn = (IdBtn)e.getSource();
			//��õ�ǰ��ť�������id
			int id = btn.id;
			//��ǰ�غϵ����ֻ�ܿ��Լ�������
			if(id == con.getNowId()) {
				descShow = new Thread() {
					@Override
					public void run() {
						super.run();
						try {
							Thread.currentThread();
							//�ӳٺ���ʾ
							Thread.sleep(Config.descTimeInterval);
						} catch (Exception e2) {
						}
						//��ÿ�Ƭ�����������е��±�
						int idx = 0;
						try {
							idx = Integer.parseInt(btn.getText()) - 1;
						} catch (Exception e2) {
							System.out.println("��������±�ʧ�ܣ�");
						}
						//�����ָ��Ŀ�Ƭ
						Card card = con.player[id].hand.get(idx);
						//���ò���ʾ�������(�ڶԷ���������ʾ)
						int oppoId = 1 - id;
						//System.out.println(attend.desc);
						con.mainV.descPanes[oppoId].setCard(card);
						con.mainV.descPanes[oppoId].setVisible(true);
					}
				};
				descShow.start();
			}
		};
		
		@SuppressWarnings("deprecation")
		@Override
		public void mouseExited(MouseEvent e) {//����Ƴ�ʱ
			super.mouseExited(e);
			
			//��ð�ť
			IdBtn btn = (IdBtn)e.getSource();
			//��õ�ǰ���id
			int id = btn.id;
			//ֻ���Լ��Ŀ�Ƭ�ŻῪ��descShow�߳�
			if(id == con.getNowId()) {
				//��ֹ������ʾ�߳�
				descShow.stop();
				
				//�����������(�ڶԷ���������ʾ�����)
				con.mainV.descPanes[1 - id].setVisible(false);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			//��ð�ť
			IdBtn btn = (IdBtn)e.getSource();
			//��õ�ǰ��ť�������id
			int id = btn.id;
			//ֻ�ܲ�����ǰ����Լ�������
			if(id == con.getNowId()) {
				int choice = JOptionPane.showConfirmDialog(con.mainV, "�Ƿ�ʹ�ã�", 
						Config.title, JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
				if(choice == JOptionPane.YES_OPTION) {//ȷ��ʹ��
					//��ÿ�Ƭ�����������е��±�
					int idx = 0;
					try {
						idx = Integer.parseInt(btn.getText()) - 1;
					} catch (Exception e2) {
						System.out.println("��������±�ʧ�ܣ�");
					}
					//�����ָ��Ŀ�Ƭ
					Card card = con.player[id].hand.get(idx);
					//��ǰ���
					Player player = con.player[id];
					//���ˮ������
					if(player.cost >= card.cost) {
						boolean use = false;//�ж��Ƿ�����ʹ�øÿ�Ƭ
						//����������
						if(card instanceof Attend) {
							Attend attend = (Attend)card;
							//Ѱ�ҿ�λ
							LinkedList<Integer> idxs = new LinkedList<>();
							StandCard[] stands = con.mainV.stands[id];
							for(int i = 0; i < Config.standMaxCount + 1; i++) {
								if(stands[i] == null) 
									idxs.add(i);
							}
							if(idxs.size() == 0) {//�޿���λ��
								JOptionPane.showMessageDialog(con.mainV, "ս���������޷�����", Config.title, 
										0, Config.scaredIcon);
								return;
							}else {
								//����û�ѡ���λ��
								//ע������ʲô���ͷ���ʲô����
								Integer pos = (Integer)JOptionPane.showInputDialog(con.mainV, "��ѡ������ս��λ��", Config.title, 
										0, Config.inqueryIcon, idxs.toArray(), idxs.getFirst());
								//���ʹ�óɹ��Ҵ���ս��ɹ�
								if(pos != null && attend.warCry(con, id, pos) == true) {
									use = true;
									//����г������
									if(attend.features.contains(Feature.Dash)) {
										int value = 1;
										//����з�ŭ����
										if(attend.features.contains(Feature.Wind))
											value = 2;
										//���³�ʼ��������
										attend.atkTime = value;
									}
									//��ʾ��Ƭ���,���ÿ�Ƭ������
									con.mainV.setStandCard(attend, pos, id);
								}
							}
						}
						//����Ƿ�����
						else if(card instanceof Magic) {
							Magic magic = (Magic)card;
							use = magic.effect(con, id);
							//ʹ��ʧ����ʾ
							if(!use)
								JOptionPane.showMessageDialog(con.mainV, 
										"����ʹ��ʧ��", Config.title, 0, Config.scaredIcon);
								
						}
						//���ʹ�óɹ���ɾ�����Ʋ��޸�ˮ��
						if(use == true) {
							//������ɾ��
							con.delOneHand(id, card);
							/**�޸�ˮ��,��δ���ǹ���*/
							con.changeCosts(1, id, 0 - card.cost);
//							//���ݸ���
//							player.cost = Math.max(player.cost - card.cost, 0);
//							//��ͼ����
//							con.mainV.setCostPanes(id, player.cost, player.turnCost);
						}
					}
					//ˮ��������
					else {
						JOptionPane.showMessageDialog(con.mainV, "û���㹻�ķ���ֵ��", Config.title, 
								0, Config.scaredIcon);
						return;
					}
					
				}
			}
		}
	}
	
	
	//���ü��ܰ�ť������
	public static void setSkillListener(IdBtn btn) {
		btn.addMouseListener(new SkillListener());
	}
	//���ܰ�ť������
	private static class SkillListener extends MouseAdapter {
		//int descFlag = 0;//��descFlagΪ1ʱ��ʾ����
		Thread descShow;
		
		@Override
		public void mouseEntered(MouseEvent e) {//�������ʱ
			super.mouseEntered(e);
			
			//��ð�ť
			IdBtn btn = (IdBtn)e.getSource();
			//��õ�ǰ��ť�������id
			int id = btn.id;
			descShow = new Thread() {
				@Override
				public void run() {
					super.run();
					try {
						//�ӳٺ���ʾ
						Thread.sleep(Config.descTimeInterval);
					} catch (Exception e2) {
					}
					//�����ָ��Ŀ�Ƭ
					Card card = con.player[id].skill;
					//���ò���ʾ�������(�ڶԷ���������ʾ)
					int oppoId = 1 - id;
					//System.out.println(attend.desc);
					con.mainV.descPanes[oppoId].setCard(card);
					con.mainV.descPanes[oppoId].setVisible(true);
				}
			};
			descShow.start();
		};
		
		@SuppressWarnings("deprecation")
		@Override
		public void mouseExited(MouseEvent e) {//����Ƴ�ʱ
			super.mouseExited(e);
			
			//��ð�ť
			IdBtn btn = (IdBtn)e.getSource();
			//��õ�ǰ���id
			int id = btn.id;
			//��ֹ������ʾ�߳�
			descShow.stop();
			
			//�����������(�ڶԷ���������ʾ�����)
			con.mainV.descPanes[1 - id].setVisible(false);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			//��ð�ť
			IdBtn btn = (IdBtn)e.getSource();
			//��õ�ǰ��ť�������id
			int id = btn.id;
			//ֻ�ܲ�����ǰ����Լ��ļ���
			if(id == con.getNowId()) {
				//��ǰ���
				Player player = con.player[id];
				//����ʹ�ô�������
				if(player.skillTime <= 0) {
					JOptionPane.showMessageDialog(con.mainV, 
							"���ܴ���������", Config.title, 0, Config.scaredIcon);
				}
				else {
					int choice = JOptionPane.showConfirmDialog(con.mainV, "�Ƿ�ʹ�ã�", 
							Config.title, JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
					if(choice == JOptionPane.YES_OPTION) {//ȷ��ʹ��
						
						//�����ָ��Ŀ�Ƭ
						Card card = player.skill;
						
						//���ˮ������
						if(player.cost >= card.cost) {
							boolean use = false;//�ж��Ƿ�����ʹ�øÿ�Ƭ
							Magic magic = (Magic)card;
							use = magic.effect(con, id);
							//���ʹ�óɹ����޸�ˮ�����ü��ܲ�����(һ�غ�ֻ����һ��)
							if(use == true) {
								/**�޸�ˮ��,��δ���ǹ���*/
								//���ݸ���
								player.cost = Math.max(player.cost - card.cost, 0);
								//��ͼ����
								con.mainV.setCostPanes(id, player.cost, player.turnCost);
								/**�޸ļ��ܴ���*/
								player.skillTime -= 1;
							}
							else
								JOptionPane.showMessageDialog(con.mainV, "ʹ��ʧ�ܣ�", Config.title, 
										0, Config.scaredIcon);
						}
						//ˮ��������
						else {
							JOptionPane.showMessageDialog(con.mainV, "û���㹻�ķ���ֵ��", Config.title, 
									0, Config.scaredIcon);
							return;
						}
						
					}
				}
			}
		}
	}
	
	
	//���ÿ�Ƭ������
	public static void setCardListener(StandCard card) {
		card.addMouseListener(new CardListener());
	}
	//��Ƭ������
	private static class CardListener extends MouseAdapter {
		Thread descShow;
		
		@Override
		public void mouseEntered(MouseEvent e) {//�������ʱ��ʾ�������
			super.mouseEntered(e);
			
			//��ÿ�Ƭ
			StandCard card = (StandCard)e.getSource();
			//��õ�ǰ��Ƭ�������id
			int id = card.id;
			//��Ƭ˫�����ɿ�
			descShow = new Thread() {
				@Override
				public void run() {
					super.run();
					try {
						//�ӳٺ���ʾ
						Thread.sleep(Config.descTimeInterval);
					} catch (Exception e2) {
					}
					//�����ָ��Ŀ�Ƭ
					Attend attend = card.attend;
					//���ò���ʾ�������(�ڶԷ���������ʾ)
					int oppoId = 1 - id;
					//System.out.println(attend.desc);
					con.mainV.descPanes[oppoId].setCard(attend);
					con.mainV.descPanes[oppoId].setVisible(true);
				}
			};
			descShow.start();
		};
		
		@SuppressWarnings("deprecation")
		@Override
		public void mouseExited(MouseEvent e) {//����Ƴ�ʱ
			super.mouseExited(e);
			
			//��ÿ�Ƭ
			StandCard card = (StandCard)e.getSource();
			//��õ�ǰ���id
			int id = card.id;
			//��ֹ������ʾ�߳�
			descShow.stop();
			//�����������(�ڶԷ���������ʾ�����)
			con.mainV.descPanes[1 - id].setVisible(false);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {//���й���
			super.mouseClicked(e);
			
			//��õ�ǰ��Ƭ
			StandCard card = (StandCard)e.getSource();
			//��õ�ǰ���id�͵з�id
			int nowId = con.getNowId();
			//ֻ�ܲ����ѷ��Ŀ�Ƭ
			if(nowId == card.id) {
				//ֻ�е�ǰ���û�б��������й����������й�����ʱ��Ч
				if(!card.attend.isFroozen && card.attend.canAtk && card.attend.atk > 0 && card.attend.atkTime != 0) {
					//��ù���Ŀ��
					int[] target = con.inquiryTarget(4, nowId);
					//���ʹ�óɹ�
					if(target[0] != -1) {
						//�ѷ�(��Ҫ���ҵ���card�������е��±�)
						int nowIdx = 0;
						for(; nowIdx < Config.standMaxCount + 1; nowIdx++)
							if(con.mainV.stands[nowId][nowIdx] == card)
								break;
						//˫������
						con.attendFight(nowId, nowIdx, target[0], target[1]);
//						//��öԷ���Ƭ
//						StandCard oppoCard = con.mainV.stands[oppoId][target[1]];
//						//�໥����
//						//���˫������������ֹ������������Ϊ��
//						int oppoAtk = oppoCard.attend.atk;
//						int nowAtk = card.attend.atk;
//						//��������Ǿ綾�ҶԷ���ΪӢ�ۣ�������Ϊ�Է�Ѫ��
//						if(card.attend.features.contains(Feature.Poison) && 
//								oppoCard.attend.nature != Nature.Player) {
//							nowAtk = oppoCard.attend.hp;
//						}
//						if(oppoCard.attend.features.contains(Feature.Poison) && 
//								card.attend.nature != Nature.Player) {
//							oppoAtk = card.attend.hp;
//						}
//						//�з�
//						con.changeStandCardHp(oppoId, target[1], 0 - nowAtk);
//						//�ѷ�(��Ҫ���ҵ���card�������е��±�)
//						int nowIdx = 0;
//						for(; nowIdx < Config.standMaxCount + 1; nowIdx++)
//							if(con.mainV.stands[nowId][nowIdx] == card)
//								break;
//						if(target[1] != 0)//������Ӣ�ۣ��ÿ�Ƭ�����ܵ��˺�
//							con.changeStandCardHp(nowId, nowIdx, 0 - oppoAtk);
//						//���¹���������ˢ�¿ɹ����߿�
//						if(--card.attend.atkTime <= 0) {
//							card.borderOff();
//						}
					}
				}
			}
		}
		
	}
	
}
	


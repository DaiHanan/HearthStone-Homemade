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

//添加监听器
public class Listen {
	static Controller con;//主控制器
	
	public Listen(Controller con) {
		Listen.con = con;
	}
	//设置回合交换按钮监听器
	public static void setTurnChangeListener(JButton btn) {
		btn.addActionListener(new turnChangeListener());
	}
	//回合交换监听器
	private static class turnChangeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			con.turnChange();
		}
	}
	
	//设置菜单栏_说明的监听器
	public static void setAboutListener(JMenuItem item) {
		item.addActionListener(new aboutListener());
	}
	//回合交换监听器
	private static class aboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AboutView(con);
		}
	}
	
	//设置菜单栏_选择卡组的监听器
	public static void setChooseListener(JMenuItem item) {
		item.addActionListener(new ChooseListener());
	}
	//回合交换监听器
	private static class ChooseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new ChooseView(con);
		}
	}
	
	
	//设置手牌按钮监听器
	public static void setHandListener(IdBtn btn) {
		btn.addMouseListener(new HandListener());
	}
	//手牌按钮监听器
	private static class HandListener extends MouseAdapter {
		//int descFlag = 0;//当descFlag为1时显示描述
		Thread descShow;
		
		@Override
		public void mouseEntered(MouseEvent e) {//鼠标移入时
			super.mouseEntered(e);
			
			//获得按钮
			IdBtn btn = (IdBtn)e.getSource();
			//获得当前按钮所属玩家id
			int id = btn.id;
			//当前回合的玩家只能看自己的手牌
			if(id == con.getNowId()) {
				descShow = new Thread() {
					@Override
					public void run() {
						super.run();
						try {
							Thread.currentThread();
							//延迟后显示
							Thread.sleep(Config.descTimeInterval);
						} catch (Exception e2) {
						}
						//获得卡片在手牌数组中的下标
						int idx = 0;
						try {
							idx = Integer.parseInt(btn.getText()) - 1;
						} catch (Exception e2) {
							System.out.println("获得手牌下标失败！");
						}
						//获得所指向的卡片
						Card card = con.player[id].hand.get(idx);
						//设置并显示描述面板(在对方手牌区显示)
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
		public void mouseExited(MouseEvent e) {//鼠标移出时
			super.mouseExited(e);
			
			//获得按钮
			IdBtn btn = (IdBtn)e.getSource();
			//获得当前玩家id
			int id = btn.id;
			//只有自己的卡片才会开启descShow线程
			if(id == con.getNowId()) {
				//终止描述显示线程
				descShow.stop();
				
				//隐藏描述面板(在对方手牌区显示的面板)
				con.mainV.descPanes[1 - id].setVisible(false);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			//获得按钮
			IdBtn btn = (IdBtn)e.getSource();
			//获得当前按钮所属玩家id
			int id = btn.id;
			//只能操作当前玩家自己的手牌
			if(id == con.getNowId()) {
				int choice = JOptionPane.showConfirmDialog(con.mainV, "是否使用？", 
						Config.title, JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
				if(choice == JOptionPane.YES_OPTION) {//确认使用
					//获得卡片在手牌数组中的下标
					int idx = 0;
					try {
						idx = Integer.parseInt(btn.getText()) - 1;
					} catch (Exception e2) {
						System.out.println("获得手牌下标失败！");
					}
					//获得所指向的卡片
					Card card = con.player[id].hand.get(idx);
					//当前玩家
					Player player = con.player[id];
					//如果水晶够用
					if(player.cost >= card.cost) {
						boolean use = false;//判断是否真正使用该卡片
						//如果是随从类
						if(card instanceof Attend) {
							Attend attend = (Attend)card;
							//寻找空位
							LinkedList<Integer> idxs = new LinkedList<>();
							StandCard[] stands = con.mainV.stands[id];
							for(int i = 0; i < Config.standMaxCount + 1; i++) {
								if(stands[i] == null) 
									idxs.add(i);
							}
							if(idxs.size() == 0) {//无可用位置
								JOptionPane.showMessageDialog(con.mainV, "战场已满，无法进入", Config.title, 
										0, Config.scaredIcon);
								return;
							}else {
								//获得用户选择的位置
								//注：传入什么类型返回什么类型
								Integer pos = (Integer)JOptionPane.showInputDialog(con.mainV, "请选择放入的战场位置", Config.title, 
										0, Config.inqueryIcon, idxs.toArray(), idxs.getFirst());
								//如果使用成功且触发战吼成功
								if(pos != null && attend.warCry(con, id, pos) == true) {
									use = true;
									//如果有冲锋特性
									if(attend.features.contains(Feature.Dash)) {
										int value = 1;
										//如果有风怒特性
										if(attend.features.contains(Feature.Wind))
											value = 2;
										//更新初始攻击次数
										attend.atkTime = value;
									}
									//显示卡片面板,设置卡片监听器
									con.mainV.setStandCard(attend, pos, id);
								}
							}
						}
						//如果是法术牌
						else if(card instanceof Magic) {
							Magic magic = (Magic)card;
							use = magic.effect(con, id);
							//使用失败提示
							if(!use)
								JOptionPane.showMessageDialog(con.mainV, 
										"法术使用失败", Config.title, 0, Config.scaredIcon);
								
						}
						//如果使用成功，删除手牌并修改水晶
						if(use == true) {
							//从手牌删除
							con.delOneHand(id, card);
							/**修改水晶,暂未考虑过载*/
							con.changeCosts(1, id, 0 - card.cost);
//							//数据更新
//							player.cost = Math.max(player.cost - card.cost, 0);
//							//视图更新
//							con.mainV.setCostPanes(id, player.cost, player.turnCost);
						}
					}
					//水晶不够用
					else {
						JOptionPane.showMessageDialog(con.mainV, "没有足够的法力值！", Config.title, 
								0, Config.scaredIcon);
						return;
					}
					
				}
			}
		}
	}
	
	
	//设置技能按钮监听器
	public static void setSkillListener(IdBtn btn) {
		btn.addMouseListener(new SkillListener());
	}
	//技能按钮监听器
	private static class SkillListener extends MouseAdapter {
		//int descFlag = 0;//当descFlag为1时显示描述
		Thread descShow;
		
		@Override
		public void mouseEntered(MouseEvent e) {//鼠标移入时
			super.mouseEntered(e);
			
			//获得按钮
			IdBtn btn = (IdBtn)e.getSource();
			//获得当前按钮所属玩家id
			int id = btn.id;
			descShow = new Thread() {
				@Override
				public void run() {
					super.run();
					try {
						//延迟后显示
						Thread.sleep(Config.descTimeInterval);
					} catch (Exception e2) {
					}
					//获得所指向的卡片
					Card card = con.player[id].skill;
					//设置并显示描述面板(在对方手牌区显示)
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
		public void mouseExited(MouseEvent e) {//鼠标移出时
			super.mouseExited(e);
			
			//获得按钮
			IdBtn btn = (IdBtn)e.getSource();
			//获得当前玩家id
			int id = btn.id;
			//终止描述显示线程
			descShow.stop();
			
			//隐藏描述面板(在对方手牌区显示的面板)
			con.mainV.descPanes[1 - id].setVisible(false);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			//获得按钮
			IdBtn btn = (IdBtn)e.getSource();
			//获得当前按钮所属玩家id
			int id = btn.id;
			//只能操作当前玩家自己的技能
			if(id == con.getNowId()) {
				//当前玩家
				Player player = con.player[id];
				//技能使用次数不够
				if(player.skillTime <= 0) {
					JOptionPane.showMessageDialog(con.mainV, 
							"技能次数已用完", Config.title, 0, Config.scaredIcon);
				}
				else {
					int choice = JOptionPane.showConfirmDialog(con.mainV, "是否使用？", 
							Config.title, JOptionPane.YES_NO_OPTION, 0, Config.inqueryIcon);
					if(choice == JOptionPane.YES_OPTION) {//确认使用
						
						//获得所指向的卡片
						Card card = player.skill;
						
						//如果水晶够用
						if(player.cost >= card.cost) {
							boolean use = false;//判断是否真正使用该卡片
							Magic magic = (Magic)card;
							use = magic.effect(con, id);
							//如果使用成功，修改水晶并置技能不可用(一回合只能用一次)
							if(use == true) {
								/**修改水晶,暂未考虑过载*/
								//数据更新
								player.cost = Math.max(player.cost - card.cost, 0);
								//视图更新
								con.mainV.setCostPanes(id, player.cost, player.turnCost);
								/**修改技能次数*/
								player.skillTime -= 1;
							}
							else
								JOptionPane.showMessageDialog(con.mainV, "使用失败！", Config.title, 
										0, Config.scaredIcon);
						}
						//水晶不够用
						else {
							JOptionPane.showMessageDialog(con.mainV, "没有足够的法力值！", Config.title, 
									0, Config.scaredIcon);
							return;
						}
						
					}
				}
			}
		}
	}
	
	
	//设置卡片监听器
	public static void setCardListener(StandCard card) {
		card.addMouseListener(new CardListener());
	}
	//卡片监听器
	private static class CardListener extends MouseAdapter {
		Thread descShow;
		
		@Override
		public void mouseEntered(MouseEvent e) {//鼠标移入时显示描述面板
			super.mouseEntered(e);
			
			//获得卡片
			StandCard card = (StandCard)e.getSource();
			//获得当前卡片所属玩家id
			int id = card.id;
			//卡片双方均可看
			descShow = new Thread() {
				@Override
				public void run() {
					super.run();
					try {
						//延迟后显示
						Thread.sleep(Config.descTimeInterval);
					} catch (Exception e2) {
					}
					//获得所指向的卡片
					Attend attend = card.attend;
					//设置并显示描述面板(在对方手牌区显示)
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
		public void mouseExited(MouseEvent e) {//鼠标移出时
			super.mouseExited(e);
			
			//获得卡片
			StandCard card = (StandCard)e.getSource();
			//获得当前玩家id
			int id = card.id;
			//终止描述显示线程
			descShow.stop();
			//隐藏描述面板(在对方手牌区显示的面板)
			con.mainV.descPanes[1 - id].setVisible(false);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {//进行攻击
			super.mouseClicked(e);
			
			//获得当前卡片
			StandCard card = (StandCard)e.getSource();
			//获得当前玩家id和敌方id
			int nowId = con.getNowId();
			//只能操纵友方的卡片
			if(nowId == card.id) {
				//只有当前随从没有被冰冻且有攻击次数且有攻击力时生效
				if(!card.attend.isFroozen && card.attend.canAtk && card.attend.atk > 0 && card.attend.atkTime != 0) {
					//获得攻击目标
					int[] target = con.inquiryTarget(4, nowId);
					//如果使用成功
					if(target[0] != -1) {
						//友方(需要先找到该card在数组中的下标)
						int nowIdx = 0;
						for(; nowIdx < Config.standMaxCount + 1; nowIdx++)
							if(con.mainV.stands[nowId][nowIdx] == card)
								break;
						//双方攻击
						con.attendFight(nowId, nowIdx, target[0], target[1]);
//						//获得对方卡片
//						StandCard oppoCard = con.mainV.stands[oppoId][target[1]];
//						//相互攻击
//						//获得双方攻击力，防止随从死亡后随从为空
//						int oppoAtk = oppoCard.attend.atk;
//						int nowAtk = card.attend.atk;
//						//如果特性是剧毒且对方不为英雄，攻击力为对方血量
//						if(card.attend.features.contains(Feature.Poison) && 
//								oppoCard.attend.nature != Nature.Player) {
//							nowAtk = oppoCard.attend.hp;
//						}
//						if(oppoCard.attend.features.contains(Feature.Poison) && 
//								card.attend.nature != Nature.Player) {
//							oppoAtk = card.attend.hp;
//						}
//						//敌方
//						con.changeStandCardHp(oppoId, target[1], 0 - nowAtk);
//						//友方(需要先找到该card在数组中的下标)
//						int nowIdx = 0;
//						for(; nowIdx < Config.standMaxCount + 1; nowIdx++)
//							if(con.mainV.stands[nowId][nowIdx] == card)
//								break;
//						if(target[1] != 0)//若攻击英雄，该卡片不会受到伤害
//							con.changeStandCardHp(nowId, nowIdx, 0 - oppoAtk);
//						//更新攻击次数并刷新可攻击边框
//						if(--card.attend.atkTime <= 0) {
//							card.borderOff();
//						}
					}
				}
			}
		}
		
	}
	
}
	


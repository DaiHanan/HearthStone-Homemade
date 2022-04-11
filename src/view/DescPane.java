package view;

import java.awt.BorderLayout;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Config;
import config.Tool;
import model.Feature;
import model.Player;
import model.card.Card;
import model.card.attend.Attend;
import model.card.magic.Magic;

public class DescPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Card card;//所显示的随从
	public int x, y;//模板坐标
	
	JPanel topPanel;//属性和名称的自定义Panel
	JPanel bottonPanel;//值和特性的自定义Panel
	JPanel featurePanel;//特性自定义Panel
	JPanel hpArmorPanel;//血量和护甲自定义Panel
	//标签
	public JLabel natureLabel;//属性
	//public JLabel featureLabel;//特性
	public JLabel nameLabel;//名称
	public JLabel costLabel;//费用
	public JLabel hpLabel;//生命值
	public JLabel armorLabel;//护甲
	public JLabel atkLabel;//攻击力
	public JLabel descLable;//卡片描述

	public DescPane(Card card, int x, int y) {
		//设置位置大小
		this.setBounds(x, y, Config.descPaneWeight, Config.descPaneHeight);
		//设置排版
		this.setLayout(new BorderLayout());
		//属性和名称的自定义Panel
		topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false);
		this.add(topPanel, BorderLayout.NORTH);
		/**添加属性标签*/
		natureLabel = new JLabel("", JLabel.CENTER);
		natureLabel.setFont(Config.natureFont);
		//natureLable.setSize(Config.attendWeight, 1);
		topPanel.add(natureLabel, BorderLayout.WEST);
		/**添加名称标签*/
		nameLabel = new JLabel("", JLabel.CENTER);
		nameLabel.setForeground(Config.nameColor);
		nameLabel.setFont(Config.nameFont);
		//nameLable.setSize(Config.attendWeight, 1);
		topPanel.add(nameLabel, BorderLayout.CENTER);
		/**添加费用标签*/
		costLabel = new JLabel("", JLabel.CENTER);
		costLabel.setForeground(Config.costColor);
		costLabel.setFont(Config.costFont);
		topPanel.add(costLabel, BorderLayout.EAST);
		/**添加描述标签*/
		descLable = new JLabel("", JLabel.CENTER);
		descLable.setForeground(Config.descColor);
		descLable.setFont(Config.descFont);
		this.add(descLable, BorderLayout.CENTER);
		//设置值和特性的自定义Panel
		bottonPanel = new JPanel(new BorderLayout());
		bottonPanel.setOpaque(false);
		this.add(bottonPanel, BorderLayout.SOUTH);
		/**添加攻击力标签*/
		atkLabel = new JLabel("", JLabel.LEFT);
		atkLabel.setFont(Config.atkFont);
		atkLabel.setForeground(Config.atkColor);
		bottonPanel.add(atkLabel, BorderLayout.WEST);
		//设置特性自定义Panel
		featurePanel = new JPanel();
		featurePanel.setOpaque(false);
		bottonPanel.add(featurePanel, BorderLayout.CENTER);
//		/**添加特性标签*/
//		featureLabel = new JLabel("", JLabel.CENTER);
//		featureLabel.setFont(Config.featureFont);
//		bottonPanel.add(featureLabel);
		/**添加生命值标签*/
		hpLabel = new JLabel("", JLabel.RIGHT);
		hpLabel.setFont(Config.hpFont);
		bottonPanel.add(hpLabel, BorderLayout.EAST);
		/**添加护甲标签*/
		armorLabel = new JLabel("", JLabel.CENTER);
		armorLabel.setFont(Config.armorFont);
		armorLabel.setForeground(Config.armorColor);
		
		setCard(card);
		this.setVisible(false);
	}
	
	public void setCard(Card card) {
		this.card = card;
		/**设置费用标签*/
		costLabel.setText("" + card.cost);
		/**设置描述标签*/
		Tool.setJLabelText(descLable, card.desc);
		//descLable.setText(card.desc);
		/**设置名称标签*/
		nameLabel.setText(card.name);
		//如果该卡片是随从
		if(card instanceof Attend) {
			Attend attend = (Attend)card;
			//根据特性设置背景颜色
			this.setBackground(Config.standBackColor[attend.nature.ordinal()]);
			//设置值和特性的自定义Panel
			//topPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
			/**设置属性标签*/
			natureLabel.setText(Config.natureName[attend.nature.ordinal()]);
			natureLabel.setForeground(Config.natureColor[attend.nature.ordinal()]);
			//设置值和特性的自定义Panel
			//bottonPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
			/**设置攻击力标签*/
			atkLabel.setText("" + attend.atk);
			/**设置特性标签*/
			//先把之前所有的特性标签删除
			featurePanel.removeAll();
			//再循环添加
			for(ListIterator<Feature> iter = attend.features.listIterator(); iter.hasNext();) {
				Feature feature = iter.next();
				JLabel featureLabel = new JLabel(Config.featureName[feature.ordinal()]);
				featureLabel.setFont(Config.featureFont);
				featureLabel.setForeground(Config.featureColor[feature.ordinal()]);
				featurePanel.add(featureLabel);
			}
			/**设置生命值标签*/
			if(attend.hp >= attend.hpMax)//满血颜色
				hpLabel.setForeground(Config.hpMAXColor);
			else//残血颜色
				hpLabel.setForeground(Config.hpColor);
			hpLabel.setText("" + attend.hp);
			/**设置护甲标签*/
			//如果是英雄且有护甲时，显示护甲而不显示特性
			if(attend instanceof Player && ((Player) attend).armor > 0) {
				armorLabel.setText("" + ((Player) attend).armor);
				bottonPanel.add(armorLabel, BorderLayout.CENTER);
				bottonPanel.remove(featurePanel);
			}
			else {
				bottonPanel.add(featurePanel, BorderLayout.CENTER);
				bottonPanel.remove(armorLabel);
				//armorLabel.setText("");
			}
		}
		//如果是法术，取消显示攻击力，生命值，属性和特性
		else if(card instanceof Magic) {
			this.setBackground(Config.magicDescColor);
			atkLabel.setText("");
			hpLabel.setText("");
			natureLabel.setText("");
			featurePanel.removeAll();
			//featureLabel.setText("");
		}
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Config;
import controller.Listen;
import model.Feature;
import model.Player;
import model.card.attend.Attend;

public class StandCard extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Attend attend;//所显示随从
	public int id;//所属玩家id
	public int x, y;//模板坐标
	
	JPanel topPanel;//属性和位置的自定义Panel
	JPanel midPanel;//名称和护甲的自定义Panel
	JPanel bottonPanel;//值和特性的自定义Panel
	//JPanel featurePanel;//特性自定义Panel
	//标签
	public JLabel numberLabel;//位置
	public JLabel natureLabel;//属性
	//public LinkedList<JLabel> featureLabel;//特性
	public JLabel featureLabel;//特性
	public JLabel nameLabel;//名称
	public JLabel armorLabel;//护甲
	public JLabel hpLabel;//生命值
	public JLabel atkLabel;//攻击力

	public StandCard(int id, Attend attend, int x, int y, int idx) {
		this.id = id;
		this.attend = attend;
//		this.setBounds(x, y, Config.attendWeight, Config.attendHeight);
//		//根据特性设置背景颜色
//		this.setBackground(Config.standBackColor[attend.feature.ordinal()]);
//		//设置排版
//		this.setLayout(new BorderLayout());
//		/**添加属性标签*/
//		natureLabel = new JLabel(Config.natureName[attend.nature.ordinal()], JLabel.CENTER);
//		natureLabel.setForeground(Config.natureColor[attend.nature.ordinal()]);
//		natureLabel.setFont(Config.natureFont);
//		//natureLable.setSize(Config.attendWeight, 1);
//		this.add(natureLabel, BorderLayout.NORTH);
//		/**添加名称标签*/
//		nameLabel = new JLabel(attend.name, JLabel.CENTER);
//		nameLabel.setForeground(Config.nameColor);
//		nameLabel.setFont(Config.nameFont);
//		//nameLable.setSize(Config.attendWeight, 1);
//		this.add(nameLabel, BorderLayout.CENTER);
//		//设置值和特性的自定义Panel
//		JPanel bottonPanel = new JPanel(new GridLayout(1, 3));
//		bottonPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
//		this.add(bottonPanel, BorderLayout.SOUTH);
//		/**添加攻击力标签*/
//		atkLabel = new JLabel("" + attend.atk, JLabel.LEFT);
//		atkLabel.setFont(Config.atkFont);
//		atkLabel.setForeground(Config.atkColor);
//		bottonPanel.add(atkLabel);
//		/**添加特性标签*/
//		featureLabel = new JLabel(Config.featureName[attend.feature.ordinal()], JLabel.CENTER);
//		featureLabel.setFont(Config.featureFont);
//		bottonPanel.add(featureLabel);
//		/**添加生命值标签*/
//		hpLabel = new JLabel("" + attend.hp, JLabel.RIGHT);
//		hpLabel.setFont(Config.hpFont);
//		hpLabel.setForeground(Config.hpColor);
//		bottonPanel.add(hpLabel);
		
		//设置位置大小
		this.setBounds(x, y, Config.attendWeight, Config.attendHeight);
		//设置排版
		this.setLayout(new BorderLayout());
		//设置属性和位置的自定义Panel
		topPanel = new JPanel(new GridLayout(1, 3));
		topPanel.setOpaque(false);
		this.add(topPanel, BorderLayout.NORTH);
		/**添加位置标签*/
		numberLabel = new JLabel("", JLabel.CENTER);
		if(idx != 0) 
			numberLabel.setText(idx + "号位");
		numberLabel.setFont(Config.numberFont);
		numberLabel.setForeground(Config.numberColor);
		topPanel.add(numberLabel);
		/**添加属性标签*/
		natureLabel = new JLabel("", JLabel.CENTER);
		natureLabel.setFont(Config.natureFont);
		//natureLable.setSize(Config.attendWeight, 1);
		topPanel.add(natureLabel);
		/**给topPanel添加透明标签*/
		topPanel.add(new JLabel());
		//设置名称和护甲的自定义Panel
		midPanel = new JPanel(new BorderLayout());
		midPanel.setOpaque(false);
		this.add(midPanel, BorderLayout.CENTER);
//		/**给midPanel添加透明标签*/
//		midPanel.add(new JLabel(), BorderLayout.WEST);
		/**添加名称标签*/
		nameLabel = new JLabel("", JLabel.CENTER);
		nameLabel.setForeground(Config.nameColor);
		//nameLabel.setFont(Config.nameFont);
		//nameLable.setSize(Config.attendWeight, 1);
		//this.add(nameLabel, BorderLayout.CENTER);
		midPanel.add(nameLabel, BorderLayout.CENTER);
		/**添加护甲标签，默认不显示*/
		armorLabel = new JLabel("", JLabel.CENTER);
		armorLabel.setForeground(Config.armorColor);
		armorLabel.setFont(Config.armorFont);
		//armorLabel.setOpaque(false);
		midPanel.add(armorLabel, BorderLayout.EAST);
		//设置值和特性的自定义Panel
		bottonPanel = new JPanel(new GridLayout(1, 3));
		bottonPanel.setOpaque(false);
		this.add(bottonPanel, BorderLayout.SOUTH);
		/**添加攻击力标签*/
		atkLabel = new JLabel("", JLabel.LEFT);
		atkLabel.setFont(Config.atkFont);
		atkLabel.setForeground(Config.atkColor);
		bottonPanel.add(atkLabel);
		//设置特性自定义Panel
//		featurePanel = new JPanel();
//		featurePanel.setOpaque(false);
//		bottonPanel.add(featurePanel);
		/**添加特性标签*/
		featureLabel = new JLabel("", JLabel.CENTER);
		featureLabel.setFont(Config.featureFont);
		bottonPanel.add(featureLabel);
		/**添加生命值标签*/
		hpLabel = new JLabel("", JLabel.RIGHT);
		hpLabel.setFont(Config.hpFont);
		bottonPanel.add(hpLabel);
		//设置各标签的属性
		setAttend(attend);
		//如果有冲锋特性，初始化时显示边框
		if(attend.atk > 0 && (attend.features.contains(Feature.Dash) || attend.atkTime > 0))
			borderNow();
		//设置监听器
		Listen.setCardListener(this);
	}
	
	public void setAttend(Attend attend) {
		this.attend = attend;
		//根据属性设置背景颜色
		this.setBackground(Config.standBackColor[attend.nature.ordinal()]);
		/**设置属性标签*/
		natureLabel.setText(Config.natureName[attend.nature.ordinal()]);
		natureLabel.setForeground(Config.natureColor[attend.nature.ordinal()]);
		//natureLable.setSize(Config.attendWeight, 1);
		/**设置名称标签*/
		//当字数过长时缩小字号
		if(attend.name.length() < 5) 
			nameLabel.setFont(Config.nameFont);
		else 
			nameLabel.setFont(Config.nameSmallFont);
		nameLabel.setText(attend.name);
		//nameLable.setSize(Config.attendWeight, 1);
		//设置值和特性的自定义Panel
		//bottonPanel.setBackground(Config.standBackColor[attend.feature.ordinal()]);
		/**设置护甲标签*/
		//如果有护甲才显示
		if(attend instanceof Player && ((Player) attend).armor > 0) 
			armorLabel.setText("" + ((Player) attend).armor);
		else
			armorLabel.setText("");
		/**设置攻击力标签*/
		atkLabel.setText("" + attend.atk);
		/**设置特性标签*/
		//如果只有一个特性则显示该特性
		if(attend.features.size() <= 1) {
			featureLabel.setText(Config.featureName[attend.features.getFirst().ordinal()]);
			featureLabel.setForeground(Config.featureColor[attend.features.getFirst().ordinal()]);
		}
		//如果有多个特性
		else {
			featureLabel.setText(Config.moreFeatureStr);
			featureLabel.setForeground(Config.moreFeatureColor);
		}
//		//先把之前所有的特性标签删除
//		featurePanel.removeAll();
//		//再循环添加
//		for(ListIterator<Feature> iter = attend.features.listIterator(); iter.hasNext();) {
//			Feature feature = iter.next();
//			JLabel featureLabel = new JLabel(Config.featureName[feature.ordinal()]);
//			featureLabel.setFont(Config.featureFont);
//			featureLabel.setForeground(Config.featureColor[feature.ordinal()]);
//			featurePanel.add(featureLabel);
//		}
		/**设置生命值标签*/
		if(attend.hp >= attend.hpMax)//满血颜色
			hpLabel.setForeground(Config.hpMAXColor);
		else//残血颜色
			hpLabel.setForeground(Config.hpColor);
		hpLabel.setText("" + attend.hp);
		
		if(attend.atk > 0 && attend.atkTime > 0)
			borderNow();
	}
	
	//我方时的边框
	public void borderNow() {
		//如果被冰冻
		if(this.attend.isFroozen)
			this.setBorder(Config.froozenBorder);
		//如果有攻击次数
		else if(this.attend.atkTime > 0)
			this.setBorder(Config.canAtkBorder);
		else
			this.setBorder(null);
	}
	//敌方时的边框
	public void borderOppo() {
		//如果有嘲讽
		if(this.attend.features.contains(Feature.Taunt))
			this.setBorder(Config.tauntBorder);
		//如果被冰冻
		else if(this.attend.isFroozen)
			this.setBorder(Config.froozenBorder);
		else
			this.setBorder(null);
	}
	//关闭边框
	public void borderOff() {
		this.setBorder(null);
	}
	
//	@Override
//	public void paintComponent(Graphics g1) {
//		super.paintComponent(g1);
//		Graphics2D g = (Graphics2D)g1;
//		//设置抗锯齿开
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		/**绘制属性Nature*/
//		//设置颜色
//		g.setColor(Config.natureColor[attend.nature.ordinal()]);
//		//获得属性名，以此设置打印坐标
//		String natureName = Config.natureName[attend.nature.ordinal()];
//		//计算打印X轴下标
//		int natureX = Config.attendWeight / 2 - 4 * natureName.length();
//		//绘制
//		g.drawString(natureName, natureX, Config.attendNatureY);
//		/**绘制特性Feature*/
//		
//	}
	

}

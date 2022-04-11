package config;

import model.card.Card;
import model.card.attend.deluyi.AncientTaboo;
import model.card.attend.deluyi.KingKrush;
import model.card.attend.deluyi.LeopardKnight;
import model.card.attend.fashi.RagnarosFirelord;
import model.card.attend.neutral.Abomination;
import model.card.attend.neutral.BigGameHunter;
import model.card.attend.neutral.BowMan;
import model.card.attend.neutral.Crocodile;
import model.card.attend.neutral.DarkscaleHealer;
import model.card.attend.neutral.DrPengPeng;
import model.card.attend.neutral.DragonSwamp;
import model.card.attend.neutral.FoeReaper4000;
import model.card.attend.neutral.HuntingDog;
import model.card.attend.neutral.LootHoarder;
import model.card.attend.neutral.MadScientist;
import model.card.attend.neutral.PhantomHorsemen;
import model.card.attend.neutral.SacrificeToBrokenCanyon;
import model.card.attend.neutral.Snowman;
import model.card.attend.shengqi.Peacekeeper;
import model.card.attend.shengqi.ProtectorOfJustice;
import model.card.attend.shengqi.Quartermaster;
import model.card.attend.shengqi.ShieldRobot;
import model.card.attend.shengqi.Tallim;
import model.card.attend.shengqi.ThePure;
import model.card.attend.shengqi.WindLord;
import model.card.magic.Magic;
import model.card.magic.deluyi.Activation;
import model.card.magic.deluyi.Distortion;
import model.card.magic.deluyi.ElvenForest;
import model.card.magic.deluyi.Sweep;
import model.card.magic.deluyi.WildGrowth;
import model.card.magic.fashi.ArcaneExplosio;
import model.card.magic.fashi.ArcaneMissiles;
import model.card.magic.fashi.Fire;
import model.card.magic.fashi.Flame;
import model.card.magic.fashi.FlameCannon;
import model.card.magic.fashi.FrostBolt;
import model.card.magic.fashi.LavaImage;
import model.card.magic.fashi.Pyroblast;
import model.card.magic.neutral.PossessedDark;
import model.card.magic.shengqi.BornEqual;
import model.card.magic.shengqi.Dedication;
import model.card.magic.shengqi.Mistress;
import model.card.magic.shengqi.Report;
import model.card.magic.shengqi.SealOfChampions;


public class Deck {
	public static int count = 3;
	//当前玩家的卡组编号
	public static int[] now = new int[2];
	//所有卡组及对应技能
	public static Card[][] decks;
	public static Magic[] skills;
	
	public static Card[] fashi;
	public static Card[] deluyi;
	public static Card[] shengqi;
	
	public Deck() {
		//卡组一：法师
		fashi = new Card[Config.deckCount];
		for(int i = 0; i < Config.deckCount; i++) {
			if(i < 2)
				fashi[i] = new BowMan();
			else if(i < 3)
				fashi[i] = new ArcaneExplosio();
			else if(i < 5)
				fashi[i] = new ArcaneMissiles();
			else if(i < 7)
				fashi[i] = new Fire();
			else if(i < 9)
				fashi[i] = new FlameCannon();
			else if(i < 11)
				fashi[i] = new FrostBolt();
			else if(i < 13)
				fashi[i] = new LavaImage();
			else if(i < 14)
				fashi[i] = new Pyroblast();
			else if(i < 15)
				fashi[i] = new PossessedDark();
			else if(i < 16)
				fashi[i] = new Crocodile();
			else if(i < 18)
				fashi[i] = new DarkscaleHealer();
			else if(i < 20)
				fashi[i] = new DragonSwamp();
			else if(i < 21)
				fashi[i] = new DrPengPeng();
			else if(i < 22)
				fashi[i] = new HuntingDog();
			else if(i < 24)
				fashi[i] = new LootHoarder();
			else if(i < 26)
				fashi[i] = new PhantomHorsemen();
			else if(i < 27)
				fashi[i] = new RagnarosFirelord();
			else if(i < 28)
				fashi[i] = new SacrificeToBrokenCanyon();
			else if(i < 29)
				fashi[i] = new FoeReaper4000();
			else
				fashi[i] = new Abomination();
		}
		
		//卡组二：德鲁伊
		deluyi = new Card[Config.deckCount];
		for(int i = 0; i < Config.deckCount; i++) {
			if(i < 2)
				deluyi[i] = new AncientTaboo();
			else if(i < 4)
				deluyi[i] = new LeopardKnight();
			else if(i < 6)
				deluyi[i] = new LavaImage();
			else if(i < 8)
				deluyi[i] = new ElvenForest();
			else if(i < 10)
				deluyi[i] = new Sweep();
			else if(i < 11)
				deluyi[i] = new PossessedDark();
			else if(i < 12)
				deluyi[i] = new BowMan();
			else if(i < 14)
				deluyi[i] = new Activation();
			else if(i < 15)
				deluyi[i] = new DarkscaleHealer();
			else if(i < 16)
				deluyi[i] = new DragonSwamp();
			else if(i < 17)
				deluyi[i] = new DrPengPeng();
			else if(i < 18)
				deluyi[i] = new KingKrush();
			else if(i < 20)
				deluyi[i] = new LootHoarder();
			else if(i < 21)
				deluyi[i] = new Abomination();
			else if(i < 22)
				deluyi[i] = new PhantomHorsemen();
			else if(i < 23)
				deluyi[i] = new SacrificeToBrokenCanyon();
			else if(i < 25)
				deluyi[i] = new Snowman();
			else if(i < 26)
				deluyi[i] = new FoeReaper4000();
			else if(i < 27)
				deluyi[i] = new MadScientist();
			else if(i < 29)
				deluyi[i] = new WildGrowth();
			else
				deluyi[i] = new Abomination();
		}
		
		//卡组三：圣骑
		shengqi = new Card[Config.deckCount];
		for(int i = 0; i < Config.deckCount; i++) {
			if(i < 2)
				shengqi[i] = new Dedication();
			else if(i < 4)
				shengqi[i] = new ShieldRobot();
			else if(i < 6)
				shengqi[i] = new Tallim();
			else if(i < 7)
				shengqi[i] = new PossessedDark();
			else if(i < 8)
				shengqi[i] = new DrPengPeng();
			else if(i < 9)
				shengqi[i] = new DarkscaleHealer();
			else if(i < 11)
				shengqi[i] = new LootHoarder();
			else if(i < 12)
				shengqi[i] = new PhantomHorsemen();
			else if(i < 13)
				shengqi[i] = new WindLord();
			else if(i < 14)
				shengqi[i] = new SacrificeToBrokenCanyon();
			else if(i < 16)
				shengqi[i] = new Snowman();
			else if(i < 17)
				shengqi[i] = new ProtectorOfJustice();
			else if(i < 19)
				shengqi[i] = new Mistress();
			else if(i < 21)
				shengqi[i] = new BornEqual();
			else if(i < 23)
				shengqi[i] = new Quartermaster();
			else if(i < 25)
				shengqi[i] = new Peacekeeper();
			else if(i < 26)
				shengqi[i] = new ThePure();
			else if(i < 28)
				shengqi[i] = new SealOfChampions();
			else
				shengqi[i] = new BigGameHunter();
		}
		
		decks = new Card[3][];
		skills = new Magic[3];
		//法师
		decks[0] = fashi;
		skills[0] = new Flame();
		//德鲁伊
		decks[1] = deluyi;
		skills[1] = new Distortion();
		//圣骑士
		decks[2] = shengqi;
		skills[2] = new Report();
		
		//初始卡组:玩家一：法师  玩家二：德鲁伊
		now[0] = 0;
		now[1] = 1;
		
	}
}

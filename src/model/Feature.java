package model;

//特性枚举，例如：剧毒
public enum Feature {
	NORMAL,//无
	Wind,//风怒_一回合能够攻击两次
	Taunt,//嘲讽_对方必须先攻击该特性随从
	Poison,//剧毒_受到该特性随从伤害的非英雄随从死亡
	Dash,//冲锋_下场即可攻击
	Shield,//圣盾_抵挡一次伤害
}


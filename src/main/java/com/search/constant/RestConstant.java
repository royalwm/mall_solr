package com.search.constant;

public interface RestConstant {
	int SUCCESS = 1;
	int FAILED = 2;

	public interface Items {
		Byte SOLDOUT = 2; // 下架
		Byte PUTAWAY = 1;// 上架
		Byte UNKNOWN = 3;
	}

	public interface CONTENT_CATEGORY {
		Long BIGAD = 89l;// 大广告
		Long SEARCHHOT = 99l;
		Long RECOMMEND = 101l;// 推荐
		Long NEWSFLASH = 91l;//快报
		Long INTELLIGENTPIONEER = 102l;//智能先锋
		Long LIFESTYLE = 104l;//生活百货
		Long FAMILYSTYLE = 106l;//居家优品
		Long FASHIONSTYLE = 107l;//时尚达人
	}

	public interface Permission {
		Integer ADMIN = 1;// 管理员
		Integer NORMAL = 2;// 普通用户
	}
}

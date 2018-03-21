package com.search.constant;

public interface RestConstant {
    int SUCCESS = 1;

    public interface Items {
        Byte SOLDOUT = 2; // 下架
        Byte PUTAWAY = 1;// 上架
        Byte UNKNOWN = 3;
    }
    public interface CONTENT_CATEGORY {
        Long BIGAD = 89l;// 大广告
        Long SEARCHHOT=99l;
        Long RECOMMEND = 101l;//推荐
    }
}

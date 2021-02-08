package com.example.helloworldapp.net;

public class HttpManager {
    //本机IP地址
    public static final String IP = "http://xxx.xxx.x.x:8080";

    //登录
    public static String loginUrl = IP + "/login";
    //轮播图
    public static String RationUrl = IP + "/images";
    //全部新闻列表
    public static String ArticleListUrl = IP + "/GET/lists";
    //获取头像
    public static String ImageUrl = IP + "/GET/images";
    //修改密码
    public static String UpdatePass = IP + "/PUT/passwords";
    //根据价格组装
    public static String PriceAssembleUrl = IP + "/GET/assemble";
}

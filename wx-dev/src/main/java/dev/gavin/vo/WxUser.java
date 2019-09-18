package dev.gavin.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class WxUser {

    /**
     * subscribe : 1
     * openid : o3W4lwzktl_YkngKqTzJfbGl__MA
     * nickname : carrot
     * sex : 1
     * language : zh_CN
     * city : 广州
     * province : 广东
     * country : 中国
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/9z1dFgeNNb9ib8ElFkJLXhVASrF1wFYv9VaGSYrNr7jibXmIXpeXxMpOZ5OqsicibSdR85SiaYiaZNibhibn1O24mqxCO4Lic4uXUc0VP/132
     * subscribe_time : 1558418030
     * remark :
     * groupid : 0
     * tagid_list : []
     * subscribe_scene : ADD_SCENE_QR_CODE
     * qr_scene : 0
     * qr_scene_str :
     */

    @JSONField(name = "subscribe")
    private int subscribe;
    @JSONField(name = "openid")
    private String openid;
    @JSONField(name = "nickname")
    private String nickname;
    @JSONField(name = "sex")
    private int sex;
    @JSONField(name = "language")
    private String language;
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "province")
    private String province;
    @JSONField(name = "country")
    private String country;
    @JSONField(name = "headimgurl")
    private String headimgurl;
    @JSONField(name = "subscribe_time")
    private long subscribeTime;
    @JSONField(name = "remark")
    private String remark;
    @JSONField(name = "groupid")
    private int groupid;
    @JSONField(name = "subscribe_scene")
    private String subscribeScene;
    @JSONField(name = "qr_scene")
    private int qrScene;
    @JSONField(name = "qr_scene_str")
    private String qrSceneStr;

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public int getQrScene() {
        return qrScene;
    }

    public void setQrScene(int qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }
}

package com.hateck.tjxx.model;

import com.jfinal.plugin.activerecord.Model;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;

/**
 * Created by apple on 15/5/19.
 */

public class Tousu extends Model<Tousu> implements AfterExtractor {
    private String dengJiBianHao;
    private String dengJiRen;
    private String xinXiLaiYuan;
    private String jieShouFangShi;
    private String dengJiRiQi;
    private String xingMing;
    private String shenFenZhengHao;
    private String xingBie;
    private String nianLing;
    private String lianXiDianHua_xfz;
    private String youBian;
    private String diZhi_xfz;

    private String jingYingZheXingMing;
    private String jingYingZheLeiBie;
    private String lianXiRen;
    private String lianXiDianHua_jyz;
    private String diZhi_jyz;

    private String sheAnJinE;
    private String pinPaiMingCheng;
    private String shangPinLeiXing;
    private String touSuXingZhi;
    private String touSuWenTi;
    private String touSuSuQiu;
    private String xiaoFeiRiQi;
    private String shouSunRiQi;

    public String getDengJiBianHao() {
        return dengJiBianHao;
    }

    public void setDengJiBianHao(String dengJiBianHao) {
        this.dengJiBianHao = dengJiBianHao;
    }

    public String getDengJiRen() {
        return dengJiRen;
    }

    public void setDengJiRen(String dengJiRen) {
        this.dengJiRen = dengJiRen;
    }

    public String getXinXiLaiYuan() {
        return xinXiLaiYuan;
    }

    public void setXinXiLaiYuan(String xinXiLaiYuan) {
        this.xinXiLaiYuan = xinXiLaiYuan;
    }

    public String getJieShouFangShi() {
        return jieShouFangShi;
    }

    public void setJieShouFangShi(String jieShouFangShi) {
        this.jieShouFangShi = jieShouFangShi;
    }

    public String getDengJiRiQi() {
        return dengJiRiQi;
    }

    public void setDengJiRiQi(String dengJiRiQi) {
        this.dengJiRiQi = dengJiRiQi;
    }

    public String getXingMing() {
        return xingMing;
    }

    public void setXingMing(String xingMing) {
        this.xingMing = xingMing;
    }

    public String getShenFenZhengHao() {
        return shenFenZhengHao;
    }

    public void setShenFenZhengHao(String shenFenZhengHao) {
        this.shenFenZhengHao = shenFenZhengHao;
    }

    public String getXingBie() {
        return xingBie;
    }

    public void setXingBie(String xingBie) {
        this.xingBie = xingBie;
    }

    public String getNianLing() {
        return nianLing;
    }

    public void setNianLing(String nianLing) {
        this.nianLing = nianLing;
    }

    public String getLianXiDianHua_xfz() {
        return lianXiDianHua_xfz;
    }

    public void setLianXiDianHua_xfz(String lianXiDianHua_xfz) {
        this.lianXiDianHua_xfz = lianXiDianHua_xfz;
    }

    public String getYouBian() {
        return youBian;
    }

    public void setYouBian(String youBian) {
        this.youBian = youBian;
    }

    public String getDiZhi_xfz() {
        return diZhi_xfz;
    }

    public void setDiZhi_xfz(String diZhi_xfz) {
        this.diZhi_xfz = diZhi_xfz;
    }

    public String getJingYingZheXingMing() {
        return jingYingZheXingMing;
    }

    public void setJingYingZheXingMing(String jingYingZheXingMing) {
        this.jingYingZheXingMing = jingYingZheXingMing;
    }

    public String getJingYingZheLeiBie() {
        return jingYingZheLeiBie;
    }

    public void setJingYingZheLeiBie(String jingYingZheLeiBie) {
        this.jingYingZheLeiBie = jingYingZheLeiBie;
    }

    public String getLianXiRen() {
        return lianXiRen;
    }

    public void setLianXiRen(String lianXiRen) {
        this.lianXiRen = lianXiRen;
    }

    public String getLianXiDianHua_jyz() {
        return lianXiDianHua_jyz;
    }

    public void setLianXiDianHua_jyz(String lianXiDianHua_jyz) {
        this.lianXiDianHua_jyz = lianXiDianHua_jyz;
    }

    public String getDiZhi_jyz() {
        return diZhi_jyz;
    }

    public void setDiZhi_jyz(String diZhi_jyz) {
        this.diZhi_jyz = diZhi_jyz;
    }

    public String getSheAnJinE() {
        return sheAnJinE;
    }

    public void setSheAnJinE(String sheAnJinE) {
        this.sheAnJinE = sheAnJinE;
    }

    public String getPinPaiMingCheng() {
        return pinPaiMingCheng;
    }

    public void setPinPaiMingCheng(String pinPaiMingCheng) {
        this.pinPaiMingCheng = pinPaiMingCheng;
    }

    public String getShangPinLeiXing() {
        return shangPinLeiXing;
    }

    public void setShangPinLeiXing(String shangPinLeiXing) {
        this.shangPinLeiXing = shangPinLeiXing;
    }

    public String getTouSuXingZhi() {
        return touSuXingZhi;
    }

    public void setTouSuXingZhi(String touSuXingZhi) {
        this.touSuXingZhi = touSuXingZhi;
    }

    public String getTouSuWenTi() {
        return touSuWenTi;
    }

    public void setTouSuWenTi(String touSuWenTi) {
        this.touSuWenTi = touSuWenTi;
    }

    public String getTouSuSuQiu() {
        return touSuSuQiu;
    }

    public void setTouSuSuQiu(String touSuSuQiu) {
        this.touSuSuQiu = touSuSuQiu;
    }

    public String getXiaoFeiRiQi() {
        return xiaoFeiRiQi;
    }

    public void setXiaoFeiRiQi(String xiaoFeiRiQi) {
        this.xiaoFeiRiQi = xiaoFeiRiQi;
    }

    public String getShouSunRiQi() {
        return shouSunRiQi;
    }

    public void setShouSunRiQi(String shouSunRiQi) {
        this.shouSunRiQi = shouSunRiQi;
    }

    public void afterProcess(Page page) {

    }

    @Override
    public String toString() {
        return "Tousu{" +
                "登记人='" + dengJiRen + '\'' +
                "信息来源='" + xinXiLaiYuan + '\'' +
                ", 接收方式='" + jieShouFangShi + '\'' +
                ", 登记时间='" + dengJiRiQi + '\'' +
                '}';
    }
}

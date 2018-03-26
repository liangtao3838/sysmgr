package com.sys.mgr.model;

import java.io.Serializable;

/**
 * Created by liangtao on 2018/3/25.
 */
public class TradeDetail implements Serializable {

    private Long id;
    private String uuid;
    private String jkmc;
    private String qqxt;
    private String mbxt;
    private String mbjk;
    private String qqxml;
    private String fhxml;
    private String xyxml;
    private String ycxx;
    private String jdwz;
    private String zt;
    private String lrrq;
    private String jyhs;
    private String ts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJkmc() {
        return jkmc;
    }

    public void setJkmc(String jkmc) {
        this.jkmc = jkmc;
    }

    public String getQqxt() {
        return qqxt;
    }

    public void setQqxt(String qqxt) {
        this.qqxt = qqxt;
    }

    public String getMbxt() {
        return mbxt;
    }

    public void setMbxt(String mbxt) {
        this.mbxt = mbxt;
    }

    public String getMbjk() {
        return mbjk;
    }

    public void setMbjk(String mbjk) {
        this.mbjk = mbjk;
    }

    public String getQqxml() {
        return qqxml;
    }

    public void setQqxml(String qqxml) {
        this.qqxml = qqxml;
    }

    public String getFhxml() {
        return fhxml;
    }

    public void setFhxml(String fhxml) {
        this.fhxml = fhxml;
    }

    public String getXyxml() {
        return xyxml;
    }

    public void setXyxml(String xyxml) {
        this.xyxml = xyxml;
    }

    public String getYcxx() {
        return ycxx;
    }

    public void setYcxx(String ycxx) {
        this.ycxx = ycxx;
    }

    public String getJdwz() {
        return jdwz;
    }

    public void setJdwz(String jdwz) {
        this.jdwz = jdwz;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getLrrq() {
        return lrrq;
    }

    public void setLrrq(String lrrq) {
        this.lrrq = lrrq;
    }

    public String getJyhs() {
        return jyhs;
    }

    public void setJyhs(String jyhs) {
        this.jyhs = jyhs;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "TradeDetail{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", jkmc='" + jkmc + '\'' +
                ", qqxt='" + qqxt + '\'' +
                ", mbxt='" + mbxt + '\'' +
                ", mbjk='" + mbjk + '\'' +
                ", qqxml='" + qqxml + '\'' +
                ", fhxml='" + fhxml + '\'' +
                ", xyxml='" + xyxml + '\'' +
                ", ycxx='" + ycxx + '\'' +
                ", jdwz='" + jdwz + '\'' +
                ", zt='" + zt + '\'' +
                ", lrrq='" + lrrq + '\'' +
                ", jyhs='" + jyhs + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}

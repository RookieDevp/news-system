package com.guet.newsproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;


@TableName("news")
public class News implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "nid", type = IdType.AUTO)
    private Integer nid;

    private Integer ntid;

    private String ntitle;

    private String nauthor;

    @TableField("ncreateDate")
    private Date ncreateDate;

    @TableField("npicPath")
    private String npicPath;

    private String ncontent;

    @TableField("nmodifyDate")
    private Date nmodifyDate;

    private String nsummary;


    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getNtid() {
        return ntid;
    }

    public void setNtid(Integer ntid) {
        this.ntid = ntid;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNauthor() {
        return nauthor;
    }

    public void setNauthor(String nauthor) {
        this.nauthor = nauthor;
    }

    public Date getNcreateDate() {
        return ncreateDate;
    }

    public void setNcreateDate(Date ncreateDate) {
        this.ncreateDate = ncreateDate;
    }

    public String getNpicPath() {
        return npicPath;
    }

    public void setNpicPath(String npicPath) {
        this.npicPath = npicPath;
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public Date getNmodifyDate() {
        return nmodifyDate;
    }

    public void setNmodifyDate(Date nmodifyDate) {
        this.nmodifyDate = nmodifyDate;
    }

    public String getNsummary() {
        return nsummary;
    }

    public void setNsummary(String nsummary) {
        this.nsummary = nsummary;
    }

    @Override
    public String toString() {
        return "News{" +
        "nid=" + nid +
        ", ntid=" + ntid +
        ", ntitle=" + ntitle +
        ", nauthor=" + nauthor +
        ", ncreateDate=" + ncreateDate +
        ", npicPath=" + npicPath +
        ", ncontent=" + ncontent +
        ", nmodifyDate=" + nmodifyDate +
        ", nsummary=" + nsummary +
        "}";
    }
}

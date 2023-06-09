package com.guet.newsproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-05
 */
@TableName("topic")
public class Topic implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    private String tname;


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "Topic{" +
        "tid=" + tid +
        ", tname=" + tname +
        "}";
    }
}

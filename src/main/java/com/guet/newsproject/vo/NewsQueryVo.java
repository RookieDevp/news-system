package com.guet.newsproject.vo;

import lombok.Data;

@Data
public class NewsQueryVo {
    private Integer ntid;
    private Integer pageNo=1;
    private Integer pageSize=10;
}

package com.demo.api.response;

import lombok.Data;

import java.util.List;

@Data
public class PageFoodTrucks {

    /**
     * 分页
     */
    int page;

    /**
     * 每页显示多少条
     */
    int perPage;

    /**
     * 总共多少条
     */
    long totalCount;

    /**
     * 总页数
     */
    int totalPage;

    /**
     * 列表字段
     */
    List<FoodTrucks> contents;
}

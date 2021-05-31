package com.yugo.huashan.dto;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String name;
    private List<Item> list;
}

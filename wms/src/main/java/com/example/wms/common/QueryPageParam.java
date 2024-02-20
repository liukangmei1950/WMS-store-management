package com.example.wms.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam{
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;

    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    /*为了接收未知的参数*/
    private HashMap param=new HashMap();
}
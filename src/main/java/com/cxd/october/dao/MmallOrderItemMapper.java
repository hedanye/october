package com.cxd.october.dao;


import com.cxd.october.pojo.MmallOrderItem;

public interface MmallOrderItemMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(MmallOrderItem record);

    int insertSelective(MmallOrderItem record);


    MmallOrderItem selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(MmallOrderItem record);

    int updateByPrimaryKey(MmallOrderItem record);
}
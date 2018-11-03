package com.cxd.october.dao;

import com.cxd.october.pojo.MmallOrder;
import java.util.List;

public interface MmallOrderMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(MmallOrder record);

    int insertSelective(MmallOrder record);


    MmallOrder selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(MmallOrder record);

    int updateByPrimaryKey(MmallOrder record);
}
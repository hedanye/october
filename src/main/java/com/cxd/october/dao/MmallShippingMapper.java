package com.cxd.october.dao;

import com.cxd.october.pojo.MmallShipping;
import java.util.List;


public interface MmallShippingMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(MmallShipping record);

    int insertSelective(MmallShipping record);


    MmallShipping selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(MmallShipping record);

    int updateByPrimaryKey(MmallShipping record);
}
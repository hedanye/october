package com.cxd.october.dao;

import com.cxd.october.pojo.MmallCart;
import java.util.List;

public interface MmallCartMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MmallCart record);

    int insertSelective(MmallCart record);


    MmallCart selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(MmallCart record);

    int updateByPrimaryKey(MmallCart record);
}
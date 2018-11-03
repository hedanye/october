package com.cxd.october.dao;

import com.cxd.october.pojo.MmallCategory;
import java.util.List;

public interface MmallCategoryMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(MmallCategory record);

    int insertSelective(MmallCategory record);


    MmallCategory selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(MmallCategory record);

    int updateByPrimaryKey(MmallCategory record);
}
package com.cxd.october.dao;

import com.cxd.october.pojo.MmallPayInfo;
import java.util.List;

public interface MmallPayInfoMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(MmallPayInfo record);

    int insertSelective(MmallPayInfo record);


    MmallPayInfo selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(MmallPayInfo record);

    int updateByPrimaryKey(MmallPayInfo record);
}
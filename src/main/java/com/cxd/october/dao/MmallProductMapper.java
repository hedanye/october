package com.cxd.october.dao;

import com.cxd.october.pojo.MmallProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MmallProductMapper {


    int deleteByPrimaryKey(Integer id);


    int insertSelective(MmallProduct record);

    MmallProduct selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(MmallProduct record);


    List<MmallProduct> selectList();


    List<MmallProduct> selectByNameAndId(@Param(value = "productName") String productName, @Param(value = "productId") Integer productId);



    List<MmallProduct> selectByNameAndcatrgoryIds(@Param(value = "productName")String productName,
                                                  @Param(value = "categoryIdList")List<Integer> categoryIdList);

}
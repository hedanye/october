package com.cxd.october.service.impl;

import com.cxd.october.common.ResponseCode;
import com.cxd.october.common.ServerResponse;
import com.cxd.october.dao.MmallCartMapper;
import com.cxd.october.dao.MmallCategoryMapper;
import com.cxd.october.pojo.MmallCategory;
import com.cxd.october.service.ICategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.java.Log;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


@Service("iCategoryService")
@Log
public class CategoryServiceImpl implements ICategoryService {



    @Autowired
    private MmallCategoryMapper categoryMapper;


    public ServerResponse add(String categoryname,Integer parentId){
        if (parentId==null || StringUtils.isBlank(categoryname)){
            return ServerResponse.createByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getMsg());
        }

        MmallCategory category=new MmallCategory();
        category.setParentId(parentId);
        category.setName(categoryname);
        category.setStatus(true);

        int count=categoryMapper.insert(category);
        if (count>0){
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createByError("添加失败");


    }


    public ServerResponse update(String categoryname,Integer categoryId){
        if (categoryId==null || StringUtils.isBlank(categoryname)){
            return ServerResponse.createByErrorCode(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getMsg());
        }

        MmallCategory category=new MmallCategory();
        category.setId(categoryId);
        category.setName(categoryname);

        int count=categoryMapper.updateByPrimaryKeySelective(category);
        if (count>0){
            return ServerResponse.createBySuccess("更新成功");
        }
        return ServerResponse.createByError("更新失败");

    }

    @Override
    public ServerResponse<List<MmallCategory>> getChildrenParallel(Integer categoryId) {

        List<MmallCategory> categoryList= categoryMapper.selectChildren(categoryId);
        if (CollectionUtils.isEmpty(categoryList)){
            log.info("未找到分类");
        }

        return ServerResponse.createBySuccess(categoryList);

    }





    public ServerResponse<List<Integer>> getChildren(Integer categoryId){
        Set<MmallCategory> categorySet= Sets.newHashSet();
        digui(categorySet,categoryId);

        List<Integer> categoryIdList= Lists.newArrayList();

        if (categoryId!=null){
            for (MmallCategory c :
                    categorySet) {
                categoryIdList.add(c.getId());
            }
        }

        return ServerResponse.createBySuccess(categoryIdList);


    }






    private Set<MmallCategory> digui(Set<MmallCategory> categorySet,Integer categoryId){
       MmallCategory category= categoryMapper.selectByPrimaryKey(categoryId);
       if (category!=null){
           categorySet.add(category);
       }

        List<MmallCategory> categoryList = categoryMapper.selectChildren(categoryId);
        for (MmallCategory c :
                categoryList) {
            digui(categorySet,c.getId());
        }
        return categorySet;


    }






}

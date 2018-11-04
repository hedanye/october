package com.cxd.october.service;

import com.cxd.october.common.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ICategoryService {

    ServerResponse add(String categoryname, Integer categoryId);

    ServerResponse update(String categoryname,Integer categoryId);

    ServerResponse getChildrenParallel(Integer categoryId);

    ServerResponse<List<Integer>> getChildren(Integer categoryId);








}

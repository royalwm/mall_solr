package com.search.dao;
import com.search.entity.Items;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SolrMapper {
    List<Items> getItemList();
}

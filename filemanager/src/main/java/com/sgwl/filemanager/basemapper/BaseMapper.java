package com.sgwl.filemanager.basemapper;


import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


/**
 * 之后的mapper只需继承此mapper即可
 * @param <T>
 */
@RegisterMapper//自定义Mapper需注册
public interface BaseMapper<T> extends Mapper<T>, IdListMapper<T,Long>, InsertListMapper<T> {
}

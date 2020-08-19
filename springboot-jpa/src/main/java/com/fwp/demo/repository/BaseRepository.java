package com.fwp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * 通用Repository
 * 需要使用  @NoRepositoryBean  告诉JPA不要创建对应接口的bean对象
 * 不然会报  Caused by: java.lang.IllegalArgumentException: Not a managed type: class java.lang.Object
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {


}

package com.winterchen.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;


/**
 * 针对mybatis-plus做一些工具类封装
 * @author winterchen
 */
public class MybatisPlusUtils {


    public static final String LIMIT_1 = "AND ROWNUM<2";


    /**
     * 返回lambda格式的queryWrapper
     * @param baseMapper mapper类
     * @param <T> 泛型
     * @return
     */
    public static <T> LambdaQueryChainWrapper<T> lambdaQueryChainWrapper(BaseMapper<T> baseMapper) {
        return new LambdaQueryChainWrapper(baseMapper);
    }


    /**
     * 返回lambda格式的updateWrapper
     * @param baseMapper mapper类
     * @param <T> 泛型
     * @return
     */
    public static <T> LambdaUpdateChainWrapper<T> lambdaUpdateChainWrapper(BaseMapper<T> baseMapper) {
        return new LambdaUpdateChainWrapper(baseMapper);
    }


    /**
     * 获取对于的查询条件wrapper
     * @param tClass 对应实体类的class
     * @param <T> 泛型
     * @return
     */
    public static <T> LambdaQueryWrapper<T> lambdaQueryWrapper(Class<T> tClass) {
        return new QueryWrapper<T>().lambda();
    }


    /**
     * 获取对应的更新wrapper的lambda
     * @param tClass 对应实体类的class
     * @param <T> 泛型
     * @return
     */
    public static <T> LambdaUpdateWrapper<T> lambdaUpdateWrapper(Class<T> tClass) {
        return new UpdateWrapper<T>().lambda();
    }


    
}
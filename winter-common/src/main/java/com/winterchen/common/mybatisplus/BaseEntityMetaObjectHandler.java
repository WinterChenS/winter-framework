package com.winterchen.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.winterchen.common.utils.UserInfoUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //只有属性没有值的时候才进行填充
        if (this.getFieldValByName("createTime",metaObject) == null) {
            this.setInsertFieldValByName("createTime", new Date(), metaObject);
        }
        if (this.getFieldValByName("updateTime",metaObject) == null) {
            this.setInsertFieldValByName("updateTime", new Date(), metaObject);
        }
        if (this.getFieldValByName("delFlag",metaObject) == null) {
            this.setInsertFieldValByName("delFlag", 0, metaObject);
        }
        if (this.getFieldValByName("createUserId", metaObject) == null) {
            this.setInsertFieldValByName("createUserId", UserInfoUtils.getLongUserIdByCurrent(), metaObject);
        }
        if (this.getFieldValByName("createUserType", metaObject) == null) {
            this.setInsertFieldValByName("createUserType", UserInfoUtils.getUserTypeByCurrent(), metaObject);
        }

        if (this.getFieldValByName("updateVersion", metaObject) == null) {
            this.setInsertFieldValByName("updateVersion", 0, metaObject);
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setInsertFieldValByName("updateTime", new Date(), metaObject);
        this.setUpdateFieldValByName("updateUserId", UserInfoUtils.getLongUserIdByCurrent(), metaObject);
        this.setUpdateFieldValByName("createUserType", UserInfoUtils.getUserTypeByCurrent(), metaObject);
    }



}

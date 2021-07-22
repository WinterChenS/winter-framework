package com.winterchen.common.mybatisplus;


import com.winterchen.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;


/**
 * @Description: DateTypeHandler
 * @Author: winterchen
 * @Date: 2021/6/2 18:50
 */
@MappedJdbcTypes(JdbcType.DATE)
public class StringDateTypeHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        if (StringUtils.isNotBlank(s)) {
            Date date = new Date(DateUtil.strToDate(s, DateUtil.DATETIME_FORMAT_PATTERN).getTime());
            preparedStatement.setDate(i, date);
        }

        preparedStatement.setDate(i, null);
    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        if (StringUtils.isNotBlank(s)) {
            return DateUtil.dateToString(resultSet.getDate(s),DateUtil.DATETIME_FORMAT_PATTERN);
        }
        return null;

    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        return DateUtil.dateToString(resultSet.getDate(i),DateUtil.DATETIME_FORMAT_PATTERN);
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return DateUtil.dateToString(callableStatement.getDate(i),DateUtil.DATETIME_FORMAT_PATTERN);
    }
}

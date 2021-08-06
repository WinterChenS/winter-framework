package ${cfg.basePackage}.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ewell.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
* ${table.comment!""}
*/
@Data
@TableName("${table.name}")
@Builder
public class ${entity}Entity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
    <#list table.fields as column>
    <#if column.keyFlag>
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value="${column.name}" ,type = IdType.ID_WORKER)
    </#if>
    <#if column.propertyType?index_of("LocalDateTime")!=-1 >
    <#if column.comment??>
    /** ${column.comment!""} */
    </#if>
    @TableField(value="${column.name}" )
    private Date ${column.propertyName};
    <#else >
    <#if column.comment??>
    /** ${column.comment!""} */
    </#if>
    @TableField("${column.name}")
    private ${column.propertyType} ${column.propertyName};
    </#if>

    </#list>

    @Tolerate
    public ${entity}Entity(){}
}

package ${cfg.basePackage}.rest.form;

import java.io.Serializable;
import java.math.BigDecimal;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.util.Date;




@ApiModel("${table.comment!""}")
@Data
@Builder
public class ${entity}DTO extends BaseRequest implements Serializable {

    <#list table.fields as column>

    <#if column.propertyType?index_of("LocalDateTime")!=-1 >
    @ApiModelProperty(value = "${column.comment!""}",example = "2020-01-01 00:00:00")
    @JsonFormat(pattern = DateUtil.DATETIME_FORMAT_PATTERN,timezone = "GMT+8")
    private Date ${column.propertyName};
    <#else >
    <#if column.comment??>
    @ApiModelProperty("${column.comment!""}")
    </#if>
    private ${column.propertyType} ${column.propertyName};
    </#if>
    </#list>

    @Tolerate
    public ${entity}DTO(){}

}

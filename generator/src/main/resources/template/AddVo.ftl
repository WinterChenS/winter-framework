package ${cfg.basePackage}.vo.addVo;

import java.math.BigDecimal;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@Data
public class ${entity}AddVo{

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


}

package ${cfg.basePackage}.vo.queryVo;

import java.math.BigDecimal;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;



@Data
public class ${entity}QueryVo {

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
    
    @ApiModelProperty(value = "当前页码" )
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量" )
    private Integer pageSize = 10;

	  //获取查询条件,注释部分要自己填写
	public <T> QueryWrapper<T> createQuery(Class<T> entityClass) {
		
			QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
			
			/*注释部分是举例*/	
	//		//各种and和or任意组合
	//		queryWrapper.eq("del_flag", 0);
	//		
	//		//like的前后默认会加上%%
	//		queryWrapper.and(wrapper -> wrapper.like("name", "小花").or().like("id", "1"));
	//		
	//		//默认两个条件之间就是and
	//		queryWrapper.or(wrapper -> wrapper.eq("del_flag", 0).eq("del_flag", 0));
	//		
	//		//按id倒叙
	//		queryWrapper.orderByDesc("id");
	//		
			 
		   return queryWrapper;
		}
		
		//获取分页
	   public <T> Page<T> createPage(){
		   return new Page<>(pageNum , pageSize);
	   }
}

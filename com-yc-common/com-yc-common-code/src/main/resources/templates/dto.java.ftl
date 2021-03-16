package ${cfg.packageDto};

<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
</#if>
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * @Description ${table.comment!} 网络传输实体
 * </p>
 * @author ${author}
 * @since ${date}
 * @version 1.0
 */
<#if entityLombokModel>
@Getter
@Setter
</#if>
public class ${entity} implements Serializable {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
 <#if field.comment!?length gt 0>
   /**
   * ${field.comment}
   */
 </#if>
 private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}

package ${cfg.dto};

import ${cfg.entity}.${entity};
import com.baomidou.mybatisplus.annotation.TableName;

<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
</#if>

/**
 * <p>
 * @Description ${table.comment!} 实体类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version 2.0
 */
<#if entityLombokModel>
@Getter
@Setter
</#if>
<#if table.convert>
@TableName("${table.name}")
</#if>
<#if superEntityClass??>
public class ${entity}${cfg.dtoSuffix} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity}${cfg.dtoSuffix} extends Model<${entity}> {
<#else>
public class ${entity}${cfg.dtoSuffix} extends ${entity} {
</#if>

}

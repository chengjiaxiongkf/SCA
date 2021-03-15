package ${cfg.Mapper};

import ${cfg.entity}.${entity};
import ${superMapperClassPackage};

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${entity}${cfg.mapperSuffix} : ${superMapperClass}<${entity}>
<#else>
public interface ${entity}${cfg.mapperSuffix} extends ${superMapperClass}<${entity}> {

}
</#if>

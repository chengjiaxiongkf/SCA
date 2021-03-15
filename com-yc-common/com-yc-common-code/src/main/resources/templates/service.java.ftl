package ${cfg.Service};

import ${superServiceClassPackage};
import ${cfg.entity}.${entity};
import ${cfg.model}.${entity}${cfg.modelSuffix};
/**
 * <p>
 * @Description ${table.comment!} 业务接口类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version 2.0
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}>{

    boolean insert(${entity}${cfg.modelSuffix} model);

    boolean update(${entity}${cfg.modelSuffix} model);
}
</#if>

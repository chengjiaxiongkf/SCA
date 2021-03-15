package ${cfg.ServiceImpl};

import ${cfg.entity}.${entity};
import ${cfg.model}.${entity}${cfg.modelSuffix};
import ${cfg.query}.${entity}${cfg.querySuffix};
import ${cfg.Mapper}.${entity}${cfg.mapperSuffix};
import ${cfg.Service}.${table.serviceName};
import ${superServiceImplClassPackage};

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * @Description ${table.comment!} 业务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version 2.0
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${entity}${cfg.mapperSuffix},${entity},${entity}${cfg.querySuffix}> implements ${table.serviceName} {

    @Override
    public boolean insert(${entity}${cfg.modelSuffix} model) {
        ${entity} t = new ${entity}();
        BeanUtils.copyProperties(model,t);
        return super.save(t);
    }

    @Override
    public boolean update(${entity}${cfg.modelSuffix} model) {
        ${entity} t = new ${entity}();
        BeanUtils.copyProperties(model,t);
        return super.updateById(t);
    }

}
</#if>

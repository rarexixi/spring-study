package org.xi.multidatasourse.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xi.multidatasourse.model.TransferApiModel;
import org.xi.multidatasourse.model.TransferApiParamModel;

import java.util.List;

@Mapper
public interface TransferApiMapper {
    TransferApiModel getApi(@Param("moduleName") String moduleName, @Param("apiName") String apiName);
    List<TransferApiParamModel> getApiParams(@Param("apiId") Integer apiId);
}

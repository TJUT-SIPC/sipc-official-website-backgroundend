package com.sipc115.catalina.VO.DynamicVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sipc115.catalina.dataobject.Dynamics;
import lombok.Data;

import java.util.List;

/**
 * 动态集合视图
 */
@Data
public class DynamicListVO {

    @JsonProperty("dynamic_list")
    private List<DynamicListInfoVO> dynamicListInfoVOList;

    @JsonProperty("total")
    private Integer totalPage;

}

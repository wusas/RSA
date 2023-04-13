package com.weroom.domain.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 服务提供者VO
 * </p>
 *
 * @author ws
 * @since 2023-03-07
 */
@Data
@Api("服务提供" +
        "者VO")
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProviderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("错误码")
    private long code;
    @ApiModelProperty("提示信息")
    private String message;
    @ApiModelProperty("加密数据")
    private String data;
    @ApiModelProperty("时间搓")
    private String now;
}

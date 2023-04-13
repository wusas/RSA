
package com.weroom.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 服务提供者DTO
 * </p>
 *
 * @author ws
 * @since 2023-03-07
 */
@Data
@Api("服务提供" +
        "者DTO")
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class ProviderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("错误码")
    private long code;
    @ApiModelProperty("提示信息")
    private String message;
    @ApiModelProperty("加密数据")
    private String data;
    @ApiModelProperty("时间搓")
    private String now;
    @ApiModelProperty("rsa加密之后的key")
    private String encryptKey;
}

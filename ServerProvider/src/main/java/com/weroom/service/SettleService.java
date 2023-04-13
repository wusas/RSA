package com.weroom.service;

import com.weroom.domain.CommonResult;
import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public interface SettleService {


    @ApiOperation(value = "支付")
    CommonResult<ProviderVO> pay(@RequestBody ProviderDTO providerDTO);

    @ApiOperation(value = "退款")
    CommonResult<ProviderVO> refund(@RequestBody ProviderDTO providerDTO);


    @ApiOperation(value = "分账")
    CommonResult<ProviderVO> subAccount(@RequestBody ProviderDTO providerDTO);
}

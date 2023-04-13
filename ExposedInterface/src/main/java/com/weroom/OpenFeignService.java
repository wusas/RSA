package com.weroom;

import com.weroom.domain.CommonResult;
import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import com.weroom.fallback.ProviderFallback;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "settle", url = "192.168.0.154:8890", fallback = ProviderFallback.class, path = "/provider/")
public interface OpenFeignService {


    @ApiOperation(value = "支付")
    @LoadBalanced
    @PostMapping("pay")
    CommonResult<ProviderVO> pay(@RequestBody ProviderDTO providerDTO);

    @LoadBalanced
    @PostMapping("refund")
    CommonResult<ProviderVO> refund(@RequestBody ProviderDTO providerDTO);


    @ApiOperation(value = "分账")
    @LoadBalanced
    @PostMapping("sub-account")
    CommonResult<ProviderVO> subAccount(@RequestBody ProviderDTO providerDTO);
}

package com.weroom.fallback;

import com.weroom.domain.CommonResult;
import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import com.weroom.OpenFeignService;
import org.springframework.stereotype.Component;

/**
 * 熔断降级
 */
@Component
public class ProviderFallback implements OpenFeignService {

    private CommonResult<ProviderVO> getMessage() {
        return CommonResult.failed("系统正在维护， 请联系管理员.......");
    }


    @Override
    public CommonResult<ProviderVO> pay(ProviderDTO providerDTO) {
        return this.getMessage();
    }

    @Override
    public CommonResult<ProviderVO> refund(ProviderDTO providerDTO) {
        return this.getMessage();
    }

    @Override
    public CommonResult<ProviderVO> subAccount(ProviderDTO providerDTO) {
        return this.getMessage();
    }
}

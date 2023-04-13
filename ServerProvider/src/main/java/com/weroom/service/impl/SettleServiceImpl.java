package com.weroom.service.impl;

import com.weroom.domain.CommonResult;
import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import com.weroom.service.SettleService;
import com.weroom.util.RSA;
import com.weroom.util.Req;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SettleServiceImpl implements SettleService {


    @Override
    public CommonResult<ProviderVO> pay(ProviderDTO providerDTO) {
        String server = null;
        try {
            Req.data = providerDTO.getData();
            Req.encryptkey =providerDTO.getEncryptKey();
            server = RSA.server();
        } catch (Exception e) {
        }
        return CommonResult.success(ProviderVO.builder().data(server).build());
    }

    @Override
    public CommonResult<ProviderVO> refund(ProviderDTO providerDTO) {
        return null;
    }

    @Override
    public CommonResult<ProviderVO> subAccount(ProviderDTO providerDTO) {
        return null;
    }
}

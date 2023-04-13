package com.weroom.controller;


import com.weroom.domain.CommonResult;
import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import com.weroom.service.SettleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "服务提供者对外提供层")
@RequiredArgsConstructor
@RequestMapping("/provider")
public class ProviderController {


    private final SettleService settleService;

    @ApiOperation(value = "支付")
    @PostMapping(value = {"/pay"})
    public CommonResult<ProviderVO> pay(@RequestBody ProviderDTO providerDTO){
        return this.settleService.pay(providerDTO);
    }

    @ApiOperation(value = "退款")
    @PostMapping(value = {"/refund"})
    public CommonResult<ProviderVO> addChat(@RequestBody ProviderDTO providerDTO) {
        return this.settleService.refund(providerDTO);
    }

    @ApiOperation(value = "分账")
    @PostMapping(value = {"/sub_account"})
    public CommonResult<ProviderVO> subAccount(@RequestBody ProviderDTO providerDTO) {
        return this.settleService.subAccount(providerDTO);
    }


}

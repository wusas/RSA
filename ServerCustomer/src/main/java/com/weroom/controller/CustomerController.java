package com.weroom.controller;


import com.weroom.domain.CommonResult;
import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import com.weroom.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "服务提消费者")
@RequiredArgsConstructor
@RequestMapping("/customer/")
public class CustomerController {


    private final CustomerService customerService;

    @ApiOperation(value = "支付")
    @PostMapping(value = {"/pay"})
    public CommonResult<ProviderVO> pay(@RequestBody ProviderDTO providerDTO){
        return CommonResult.success(this.customerService.pay(providerDTO));
    }

    @ApiOperation(value = "退款")
    @PostMapping(value = {"/refund"})
    public CommonResult<ProviderVO> refund(@RequestBody ProviderDTO providerDTO) {
        return CommonResult.success(this.customerService.refund(providerDTO));
    }

    @ApiOperation(value = "分账")
    @PostMapping(value = {"/sub_account"})
    public CommonResult<ProviderVO> subAccount(@RequestBody ProviderDTO providerDTO) {
        return CommonResult.success(this.customerService.subAccount(providerDTO));
    }


}

package com.weroom.service;

import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TODO
 *
 * @author 吴沙
 * @date 2023/4/12 16:03
 */
public interface CustomerService {
    /**
     * 支付实体
     * @param providerDTO 传输实体
     * @return 返回实体
     */
    ProviderVO pay(@RequestBody ProviderDTO providerDTO);

    /**
     * 退款
     * @param providerDTO 传输实体
     * @return 返回实体
     */
    ProviderVO refund(@RequestBody ProviderDTO providerDTO);

    /**
     *
     * @param providerDTO 传输实体
     * @return 返回实体
     */
    ProviderVO subAccount(@RequestBody ProviderDTO providerDTO);
}

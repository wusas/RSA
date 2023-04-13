package com.weroom.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.weroom.domain.dto.ProviderDTO;
import com.weroom.domain.vo.ProviderVO;
import com.weroom.service.CustomerService;
import com.weroom.util.RSA;
import com.weroom.util.Req;
import lombok.RequiredArgsConstructor;
import com.weroom.OpenFeignService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

/**
 * TODO
 *
 * @author 吴沙
 * @date 2023/4/12 16:03
 */
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final OpenFeignService openFeignService;
    private final static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlFkVu87Ah9T2E0Tls5ARi9kr6Ks0YmskyC2WrF1cS0GexXh2c7WGVxhD6C2MAGTwAxWH329tbjHuIw0YLPNlwvQsYwCaDrCdT6I4sLv0bqeHgWycix4tV0033qGNrsA0DXA5v86frgZ9tHyqwIA5OV7kjYN3/OVpQ7KnUOKQjRTMgkMrN3UZYZ7MVKmeIhO3AFjtzt2wCQcSHVXJhqV8x9zQC+VjT2fXGZ82zxFEfAkjGPWVWXFo6ocFIDweUvrL9ZgAuKU69Vlsa07623YTSXnUqSt8P6XM0yRu1INNzAghQTK6U/0ClRdIBS1455RVxdTYMGh0utOlpvc8gU4wAQIDAQAB";
    private final static String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCUWRW7zsCH1PYTROWzkBGL2SvoqzRiayTILZasXVxLQZ7FeHZztYZXGEPoLYwAZPADFYffb21uMe4jDRgs82XC9CxjAJoOsJ1Pojiwu/Rup4eBbJyLHi1XTTfeoY2uwDQNcDm/zp+uBn20fKrAgDk5XuSNg3f85WlDsqdQ4pCNFMyCQys3dRlhnsxUqZ4iE7cAWO3O3bAJBxIdVcmGpXzH3NAL5WNPZ9cZnzbPEUR8CSMY9ZVZcWjqhwUgPB5S+sv1mAC4pTr1WWxrTvrbdhNJedSpK3w/pczTJG7Ug03MCCFBMrpT/QKVF0gFLXjnlFXF1NgwaHS606Wm9zyBTjABAgMBAAECggEBAIyYYaC9MnuJui5I/epQgFCqYpMAr4UXRqTxj/uMAAzTBEbjBGBligzBUpqZkDZPc6l4nm2EjZICgE93+ZGgnLFH+UlbKKcDfBQdCt11RRQacrKW3UZ8MzyqUmkNc9pLDwotco32ph+oxzpnbO1EmPdml/YGw5FLW5htmWVvVNBfk9JtlxsLTbtBEIqCQu2K2SfOUZi94JNWt1va73fQveDBvpSK6K9FIJ4kNevFMdqau4soV4Vl+Vtqt3wsiQlje2J+veD0aky3qDiQvJhuXMcqXQztZ8I1cCnZ0rxVBVeS/E3ynEW2ybYZ8cz4qJFBRNoRo9DiRQh1wfmIeRqmHIECgYEAz5aJYuSssiL/5oiem0FPHXnG1n2YDHz5T/E2SisVn9E2iVATWeb0Bt3j1IHtti5YAPJpVfeM9YTcIdd9DrFiS3DX+lhL9NybyRjPwssUDeOIlMdmIkNS7TCSRmDYfoRP0CRna+NwTA8oHL5hXSrn43M1ZhQtCCImAitRyx0XzwsCgYEAtvHJcC9LVa0VjPyiGkoXiAoI862II6yI5n9nEUiRnsVS7I93Tqy3AyEzt7Nf1vnjsCw0AGnyszn+s";
    private final static String modulus = "AJRZFbvOwIfU9hNE5bOQEYvZK+irNGJrJMgtlqxdXEtBnsV4dnO1hlcYQ+gtjABk8AMVh99vbW4x7iMNGCzzZcL0LGMAmg6wnU+iOLC79G6nh4FsnIseLVdNN96hja7ANA1wOb/On64GfbR8qsCAOTle5I2Dd/zlaUOyp1DikI0UzIJDKzd1GWGezFSpniITtwBY7c7dsAkHEh1VyYalfMfc0AvlY09n1xmfNs8RRHwJIxj1lVlxaOqHBSA8HlL6y/WYALilOvVZbGtO+tt2E0l51KkrfD+lzNMkbtSDTcwIIUEyulP9ApUXSAUteOeUVcXU2DBodLrTpab3PIFOMAE=";

    @Override
    public ProviderVO pay(ProviderDTO providerDTO) {
        try {
            TreeMap<String,Object> treeMap = Maps.newTreeMap();
            treeMap.put("data",providerDTO.getData());
            Req client = RSA.client(treeMap);
            providerDTO.setData(Req.data);
            providerDTO.setEncryptKey(Req.encryptkey);
        } catch (Exception e) {
        }
        return this.openFeignService.pay(providerDTO).getData();
    }

    @Override
    public ProviderVO refund(ProviderDTO providerDTO) {
        return this.openFeignService.refund(providerDTO).getData();
    }

    @Override
    public ProviderVO subAccount(ProviderDTO providerDTO) {

        return this.openFeignService.subAccount(providerDTO).getData();
    }
}

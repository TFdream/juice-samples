package juice.samples.web.controller;

import juice.contracts.ResponseDTO;
import juice.core.util.JsonUtils;
import juice.samples.storage.entity.product.ProductSku;
import juice.samples.storage.entity.product.ProductSpecItem;
import juice.samples.storage.entity.product.ProductSpecOption;
import juice.samples.storage.manager.ProductManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ricky Fung
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ProductManager productManager;

    @GetMapping("/{id}/info")
    public ResponseDTO findById(@PathVariable("id") Long id) {
        LOG.info("商品服务-查询商品信息开始, id={}", id);
        ProductSku sku = productManager.findById(id);
        LOG.info("商品服务-查询会员信息结束, id={}, member={}", id, JsonUtils.toJson(sku));
        return ResponseDTO.ok(sku);
    }

    @GetMapping("/spec/{specId}")
    public ResponseDTO findSpecById(@PathVariable("specId") Integer specId) {
        LOG.info("商品服务-查询商品规格项信息开始, specId={}", specId);
        ProductSpecOption spec = productManager.findSpecById(specId);
        LOG.info("商品服务-查询商品规格项信息结束, specId={}， spec={}", specId, JsonUtils.toJson(spec));
        return ResponseDTO.ok(spec);
    }

    @GetMapping("/spec/{specId}/items")
    public ResponseDTO findSpecItemById(@PathVariable("specId") Integer specId) {
        LOG.info("商品服务-查询商品规格值列表开始, specId={}", specId);
        List<ProductSpecItem> list = productManager.findSpecValueById(specId);
        LOG.info("商品服务-查询商品规格值列表结束, specId={}， spec={}", specId, JsonUtils.toJson(list));
        return ResponseDTO.ok(list);
    }
}

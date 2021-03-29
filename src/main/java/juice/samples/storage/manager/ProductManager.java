package juice.samples.storage.manager;

import juice.datasource.annotation.DSRouting;
import juice.samples.constants.DataSourceKey;
import juice.samples.storage.entity.product.ProductSku;
import juice.samples.storage.entity.product.ProductSpecItem;
import juice.samples.storage.entity.product.ProductSpecOption;
import juice.samples.storage.mapper.product.ProductSkuMapper;
import juice.samples.storage.mapper.product.ProductSpecItemMapper;
import juice.samples.storage.mapper.product.ProductSpecOptionMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ricky Fung
 */
@DSRouting(DataSourceKey.MASTER_PRODUCT)
@Component
public class ProductManager {

    @Resource
    private ProductSkuMapper productSkuMapper;

    @Resource
    private ProductSpecOptionMapper productSpecOptionMapper;

    @Resource
    private ProductSpecItemMapper productSpecItemMapper;

    public ProductSku findById(Long id) {
        return productSkuMapper.selectByPrimaryKey(id);
    }

    public ProductSpecOption findSpecById(Integer specId) {
        return productSpecOptionMapper.selectByPrimaryKey(specId);
    }

    public List<ProductSpecItem> findSpecValueById(Integer specId) {
        return productSpecItemMapper.selectBySpecId(specId);
    }
}

package juice.samples.storage.mapper.product;

import juice.datasource.annotation.DSRouting;
import juice.samples.constants.DataSourceKey;
import juice.samples.storage.entity.product.ProductSpecItem;

import java.util.List;

@DSRouting(DataSourceKey.MASTER_PRODUCT)
public interface ProductSpecItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSpecItem record);

    int insertSelective(ProductSpecItem record);

    ProductSpecItem selectByPrimaryKey(Long id);

    List<ProductSpecItem> selectBySpecId(Integer specId);

    int updateByPrimaryKeySelective(ProductSpecItem record);

    int updateByPrimaryKey(ProductSpecItem record);
}
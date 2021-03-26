package juice.samples.storage.mapper.product;

import juice.datasource.annotation.DSRouting;
import juice.samples.constants.DataSourceKey;
import juice.samples.storage.entity.product.ProductSpecOption;

@DSRouting(DataSourceKey.MASTER_PRODUCT)
public interface ProductSpecOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpecOption record);

    int insertSelective(ProductSpecOption record);

    ProductSpecOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpecOption record);

    int updateByPrimaryKey(ProductSpecOption record);
}
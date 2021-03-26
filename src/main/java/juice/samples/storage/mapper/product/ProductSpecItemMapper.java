package juice.samples.storage.mapper.product;

import juice.samples.storage.entity.product.ProductSpecItem;

public interface ProductSpecItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSpecItem record);

    int insertSelective(ProductSpecItem record);

    ProductSpecItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductSpecItem record);

    int updateByPrimaryKey(ProductSpecItem record);
}
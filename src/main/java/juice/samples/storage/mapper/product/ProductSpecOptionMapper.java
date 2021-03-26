package juice.samples.storage.mapper.product;

import juice.samples.storage.entity.product.ProductSpecOption;

public interface ProductSpecOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpecOption record);

    int insertSelective(ProductSpecOption record);

    ProductSpecOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpecOption record);

    int updateByPrimaryKey(ProductSpecOption record);
}
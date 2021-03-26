package juice.samples.storage.mapper.member;

import juice.datasource.annotation.DSRouting;
import juice.samples.constants.DataSourceKey;
import juice.samples.storage.entity.member.MemberLevel;

@DSRouting(DataSourceKey.MASTER_MEMBER)
public interface MemberLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberLevel record);

    int insertSelective(MemberLevel record);

    MemberLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberLevel record);

    int updateByPrimaryKey(MemberLevel record);
}
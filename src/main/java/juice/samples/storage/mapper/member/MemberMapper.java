package juice.samples.storage.mapper.member;

import juice.config.springsupport.annotation.DSRouting;
import juice.samples.constants.DataSourceKey;
import juice.samples.storage.entity.member.Member;

@DSRouting(DataSourceKey.MASTER_MEMBER)
public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}
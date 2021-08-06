package juice.samples.storage.mapper.member;

import juice.config.springsupport.annotation.DSRouting;
import juice.samples.constants.DataSourceKey;
import juice.samples.storage.entity.member.MemberExt;

@DSRouting(DataSourceKey.MASTER_MEMBER)
public interface MemberExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberExt record);

    int insertSelective(MemberExt record);

    MemberExt selectByPrimaryKey(Long id);

    MemberExt selectByMemberId(Long memberId);

    int updateByPrimaryKeySelective(MemberExt record);

    int updateByPrimaryKey(MemberExt record);
}
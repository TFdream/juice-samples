package juice.samples.storage.mapper.member;

import juice.samples.storage.entity.member.MemberExt;

public interface MemberExtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberExt record);

    int insertSelective(MemberExt record);

    MemberExt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberExt record);

    int updateByPrimaryKey(MemberExt record);
}
package juice.samples.storage.mapper.member;

import juice.samples.storage.entity.member.MemberLevel;

public interface MemberLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberLevel record);

    int insertSelective(MemberLevel record);

    MemberLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberLevel record);

    int updateByPrimaryKey(MemberLevel record);
}
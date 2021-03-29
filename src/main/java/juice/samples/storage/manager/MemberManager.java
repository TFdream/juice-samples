package juice.samples.storage.manager;

import juice.datasource.annotation.DSRouting;
import juice.samples.constants.DataSourceKey;
import juice.samples.storage.entity.member.Member;
import juice.samples.storage.entity.member.MemberExt;
import juice.samples.storage.mapper.member.MemberExtMapper;
import juice.samples.storage.mapper.member.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Ricky Fung
 */
@Component
public class MemberManager {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberExtMapper memberExtMapper;

    @Transactional
    @DSRouting(DataSourceKey.MASTER_MEMBER)
    public Member add(String nickname) {
        Member member = new Member();
        member.setShopId(5);
        member.setMobile("18611509999");
        member.setNickname(nickname);

        memberMapper.insertSelective(member);
        return member;
    }


    @DSRouting(DataSourceKey.MASTER_MEMBER)
    public Member findById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    public MemberExt findExtById(Long memberId) {
        return memberExtMapper.selectByMemberId(memberId);
    }
}

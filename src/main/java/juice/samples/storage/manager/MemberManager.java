package juice.samples.storage.manager;

import juice.samples.storage.entity.member.Member;
import juice.samples.storage.entity.member.MemberExt;
import juice.samples.storage.mapper.member.MemberExtMapper;
import juice.samples.storage.mapper.member.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    public Member findById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    public MemberExt findExtById(Long memberId) {
        return memberExtMapper.selectByMemberId(memberId);
    }
}

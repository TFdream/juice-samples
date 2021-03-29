package juice.samples.web.controller;

import juice.contracts.ResponseDTO;
import juice.core.util.JsonUtils;
import juice.samples.storage.entity.member.Member;
import juice.samples.storage.entity.member.MemberExt;
import juice.samples.storage.manager.MemberManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Ricky Fung
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MemberManager memberManager;

    @GetMapping("/add")
    public ResponseDTO addMember(@RequestParam("nickname") String nickname) {
        LOG.info("会员服务-添加会员信息开始, nickname={}", nickname);
        Member member = memberManager.add(nickname);
        LOG.info("会员服务-添加会员信息结束, nickname={}, member={}", nickname, JsonUtils.toJson(member));
        return ResponseDTO.ok(member);
    }

    @GetMapping("/{id}/info")
    public ResponseDTO findById(@PathVariable("id") Long id) {
        LOG.info("会员服务-查询会员信息开始, id={}", id);
        Member member = memberManager.findById(id);
        LOG.info("会员服务-查询会员信息结束, id={}, member={}", id, JsonUtils.toJson(member));
        return ResponseDTO.ok(member);
    }

    @GetMapping("/{memberId}/ext")
    public ResponseDTO findExtById(@PathVariable("memberId") Long memberId) {
        LOG.info("会员服务-查询会员扩展信息开始, memberId={}", memberId);
        MemberExt memberExt = memberManager.findExtById(memberId);
        LOG.info("会员服务-查询会员扩展信息结束, memberId={}， ext={}", memberId, JsonUtils.toJson(memberExt));
        return ResponseDTO.ok(memberExt);
    }
}

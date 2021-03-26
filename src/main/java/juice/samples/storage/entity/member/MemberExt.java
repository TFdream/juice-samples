package juice.samples.storage.entity.member;

import java.math.BigDecimal;
import java.util.Date;

public class MemberExt {
    private Long id;

    private Integer shopId;

    private Long memberId;

    private Integer level;

    private Integer levelId;

    private Integer isTrial;

    private Integer creditTotal;

    private Integer creditAvailable;

    private BigDecimal balance;

    private Date birthday;

    private String email;

    private Integer enablePayPwd;

    private String payPassword;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getIsTrial() {
        return isTrial;
    }

    public void setIsTrial(Integer isTrial) {
        this.isTrial = isTrial;
    }

    public Integer getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(Integer creditTotal) {
        this.creditTotal = creditTotal;
    }

    public Integer getCreditAvailable() {
        return creditAvailable;
    }

    public void setCreditAvailable(Integer creditAvailable) {
        this.creditAvailable = creditAvailable;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getEnablePayPwd() {
        return enablePayPwd;
    }

    public void setEnablePayPwd(Integer enablePayPwd) {
        this.enablePayPwd = enablePayPwd;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
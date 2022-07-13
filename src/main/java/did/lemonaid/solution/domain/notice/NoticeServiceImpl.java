package did.lemonaid.solution.domain.notice;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.domain.tenant.TenantInfoMapper;
import did.lemonaid.solution.domain.tenant.TenantReader;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {
  private final NoticeReader noticeReader;
  private final TenantReader tenantReader;
  private final NoticeInfoMapper noticeInfoMapper;

  @Override
  public List<NoticeInfo.NoticeDetail> retrieveHolderNotices() {
    var notices = noticeReader.retrieveHolderNotices();
    return noticeInfoMapper.of(notices);
  }

  @Override
  public List<NoticeInfo.NoticeDetail> retrieveTenantsNotices(String tenantId, SystemManagementDto.NoticeSearchCondition condition) {
    Tenant tenant = tenantReader.getTenant(tenantId);
    List<Notice> notices;
    if (tenant.getTenantType().equals(Tenant.TenantType.ISSUER)){
      notices =  noticeReader.retrieveIssuerNotices(condition);
    }else if (tenant.getTenantType().equals(Tenant.TenantType.VERIFIER)) {
      notices = noticeReader.retrieveVerifierNotices(condition);
    } else{
      notices = noticeReader.retrieveTenantNotices(condition);
    }
    return noticeInfoMapper.of(notices);
  }
}

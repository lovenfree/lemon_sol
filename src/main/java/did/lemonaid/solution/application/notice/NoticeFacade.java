package did.lemonaid.solution.application.notice;

import did.lemonaid.solution.domain.notice.NoticeInfo;
import did.lemonaid.solution.domain.notice.NoticeService;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoticeFacade {
  private final NoticeService noticeService;
    public List<NoticeInfo.NoticeDetail> retrieveHolderNotices() {
      return noticeService.retrieveHolderNotices();
    }

  public List<NoticeInfo.NoticeDetail> retrieveTenantsNotices(String tenantId, SystemManagementDto.NoticeSearchCondition condition) {
    return noticeService.retrieveTenantsNotices(tenantId, condition);
    }
}

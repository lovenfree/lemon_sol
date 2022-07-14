package did.lemonaid.solution.infrastructure.notice;

import did.lemonaid.solution.domain.notice.Notice;
import did.lemonaid.solution.domain.notice.NoticeReader;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoticeReaderImpl implements NoticeReader {
  private final NoticeRepository noticeRepository;
  @Override
  public List<Notice> retrieveHolderNotices() {
    return noticeRepository.retrieveHolderNotices();
  }

  @Override
  public List<Notice> retrieveTenantNotices(SystemManagementDto.NoticeSearchCondition condition) {
    return noticeRepository.retrieveTenantNotices(condition);
  }

  @Override
  public List<Notice> retrieveIssuerNotices(SystemManagementDto.NoticeSearchCondition condition) {
    return noticeRepository.retrieveIssuerNotices(condition);
  }

  @Override
  public List<Notice> retrieveVerifierNotices(SystemManagementDto.NoticeSearchCondition condition) {
    return noticeRepository.retrieveVerifierNotices(condition);
  }

}

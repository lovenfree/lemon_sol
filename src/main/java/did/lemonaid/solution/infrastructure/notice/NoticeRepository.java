package did.lemonaid.solution.infrastructure.notice;

import did.lemonaid.solution.domain.notice.Notice;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
  List<Notice> retrieveHolderNotices();
  List<Notice> retrieveTenantNotices(SystemManagementDto.NoticeSearchCondition condition);
  List<Notice> retrieveIssuerNotices(SystemManagementDto.NoticeSearchCondition condition);
  List<Notice> retrieveVerifierNotices(SystemManagementDto.NoticeSearchCondition condition);
}

package did.lemonaid.solution.domain.notice;

import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;

import java.util.List;

public interface NoticeReader {
  List<Notice> retrieveHolderNotices();

  List<Notice> retrieveTenantNotices(SystemManagementDto.NoticeSearchCondition condition);
  List<Notice> retrieveIssuerNotices(SystemManagementDto.NoticeSearchCondition condition);
  List<Notice> retrieveVerifierNotices(SystemManagementDto.NoticeSearchCondition condition);
}

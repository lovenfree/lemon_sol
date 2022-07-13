package did.lemonaid.solution.domain.notice;

import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;

import java.util.List;

public interface NoticeService {
  List<NoticeInfo.NoticeDetail> retrieveHolderNotices();

  List<NoticeInfo.NoticeDetail> retrieveTenantsNotices(String tenantId, SystemManagementDto.NoticeSearchCondition condition);
}

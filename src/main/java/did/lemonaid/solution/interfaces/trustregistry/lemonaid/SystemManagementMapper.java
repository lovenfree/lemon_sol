package did.lemonaid.solution.interfaces.trustregistry.lemonaid;

import did.lemonaid.solution.domain.faq.FAQInfo;
import did.lemonaid.solution.domain.notice.NoticeInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface SystemManagementMapper {

  @Mappings({
    @Mapping(source = "NoticeInfo.NoticeDetail", target = "SystemManagementDto.NoticeInfo")
  })
  List<SystemManagementDto.NoticeInfo> of(List<NoticeInfo.NoticeDetail> notices);

  List<SystemManagementDto.FAQInfo> faqof(List<FAQInfo.FAQDetail> faQs);
}

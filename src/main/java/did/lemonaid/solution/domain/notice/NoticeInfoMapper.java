package did.lemonaid.solution.domain.notice;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface NoticeInfoMapper {
  List<NoticeInfo.NoticeDetail> of(List<Notice> notices);
}

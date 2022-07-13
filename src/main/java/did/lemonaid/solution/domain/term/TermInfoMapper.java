package did.lemonaid.solution.domain.term;


import did.lemonaid.solution.domain.faq.FAQ;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TermInfoMapper {
  List<TermInfo.TermDetail> of(List<Term> terms);
}

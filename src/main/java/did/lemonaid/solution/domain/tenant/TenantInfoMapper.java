package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.domain.MapStructConfig;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TenantInfoMapper {
  @Mappings({
    @Mapping(source = "Tenant", target = "TenantInfo")
  })
  List<TenantInfo> of(List<Tenant> tenantInfoList);
}

package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.credential.schema.Schemas;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CredentialInfoMapper {
//    @Mappings({
//    @Mapping(source = "schema.schemaAttributeList", target = "schemaAttributeList")
//  })
//  Schemas of(CredentialCommand.RegisterSchema schema);

//
//  CredentialInfo.CredentialDetail of(Credential credential);
}

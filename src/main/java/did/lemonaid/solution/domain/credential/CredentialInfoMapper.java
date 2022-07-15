package did.lemonaid.solution.domain.credential;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

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

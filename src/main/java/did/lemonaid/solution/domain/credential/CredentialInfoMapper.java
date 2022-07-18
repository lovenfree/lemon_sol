package did.lemonaid.solution.domain.credential;

import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CredentialInfoMapper {
//  @Mappings({
//    @Mapping(source = "res", target = "CredentialList.credentials"),
//    @Mapping(source = "java(res.isLast())", target = "CredentialList.meta.last"),
//    @Mapping(source = "java(res.getTotalElements())", target = "CredentialList.meta.totalElements"),
//    @Mapping(source = "java(res.getTotalPages())", target = "CredentialList.meta.totalPages")
//  })
//  CredentialInfo.CredentialList of(Page<CredentialInfo.CredentialTRListInfo> res);
//    @Mappings({
//    @Mapping(source = "schema.schemaAttributeList", target = "schemaAttributeList")
//  })
//  Schemas of(CredentialCommand.RegisterSchema schema);

//
//  CredentialInfo.CredentialDetail of(Credential credential);
}

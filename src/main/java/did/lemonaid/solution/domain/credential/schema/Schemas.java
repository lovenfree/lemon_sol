package did.lemonaid.solution.domain.credential.schema;

import did.lemonaid.solution.domain.BaseEntity;
import lombok.*;
import org.assertj.core.util.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name="SCHEMA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schemas extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

  @Column(name="SCHEMA_ID", nullable = false)
  private String schemaId;

  @Column(name="CREDENTIAL_DEFINITION_ID", nullable = false)
  private String credentialDefinitionId;

  @Column(name="SCHEMA_NAME", nullable = false)
  private String schemaName;

  @OneToMany(mappedBy = "schema", cascade = CascadeType.ALL)
  private List<SchemaAttribute> schemaAttributeList = Lists.newArrayList();

//  @OneToMany(mappedBy = "schema", cascade = CascadeType.ALL)
//  private List<Credential> credentialList = Lists.newArrayList();

//  @Column(name="SCHEMA_TYPE", nullable = false)
//  private schemaType schemaType;

//  @Getter
//  @AllArgsConstructor
//  public enum schemaType {
//    ID("신분증"), EMPLOYEE_ID("사원증"), DRIVER_LICENSE("운전면허증");
//
//    private final String description;
//  }

  @Builder
  public Schemas(String schemaId, String credentialDefinitionId, String schemaName){
    this.schemaId = schemaId;
    this.credentialDefinitionId = credentialDefinitionId;
    this.schemaName = schemaName;
  }
}





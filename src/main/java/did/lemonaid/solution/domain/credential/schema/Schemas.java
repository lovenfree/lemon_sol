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

  @Column(name="SCHEMA_NAME", nullable = false)
  private String schemaName;

  @OneToMany(mappedBy = "schema", cascade = CascadeType.ALL)
  private List<SchemaAttribute> schemaAttributeList = Lists.newArrayList();

  @Builder
  public Schemas(String schemaId, String schemaName, List<SchemaAttribute> schemaAttributeList){
    this.schemaId = schemaId;
    this.schemaName = schemaName;
    this.schemaAttributeList = schemaAttributeList;

  }
}





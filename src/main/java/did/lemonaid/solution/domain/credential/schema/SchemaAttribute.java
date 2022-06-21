package did.lemonaid.solution.domain.credential.schema;

import did.lemonaid.solution.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name="SCHEMA_ATTRIBUTE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchemaAttribute extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SCHEMA_ID")
  private Schemas schema;
  @Column(name="ATTRIBUTE_CODE", nullable = false)
  private String attributeCode;

  @Column(name="ATTRIBUTE_NAME", nullable = false)
  private String attributeName;

  @Column(name="MIME_TYPE", nullable = false)
  @Enumerated(EnumType.STRING)
  private MimeType mimeType;

  @Getter
  @AllArgsConstructor
  public enum MimeType {
    INTEGER("int"), STRING("string");
    private final String description;
  }

  @Builder
  public SchemaAttribute(Schemas schema, String attributeCode, String attributeName, MimeType mimeType) {
    this.schema = schema;
    this.attributeCode = attributeCode;
    this.attributeName = attributeName;
    this.mimeType = mimeType;
  }
}

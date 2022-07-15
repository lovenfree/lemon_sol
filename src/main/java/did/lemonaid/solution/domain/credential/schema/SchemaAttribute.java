package did.lemonaid.solution.domain.credential.schema;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;

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
  @Convert(converter = MimeTypeConverter.class)
  private MimeType mimeType;

  @Getter
  @AllArgsConstructor
  public enum MimeType {
    TEXT_PLAIN("text/plain", "text/plain"),
    IMAGE_PNG("image/png","image/png"),
    IMAGE_GIF("image/gif","image/gif"),
    IMAGE_JPEG("image/jpeg","image/jpeg");

    private final String description;
    private final String standardCode;

    public static MimeType ofStandardCode(String standardCode){
      //todo : param 전달
      return Arrays.stream(MimeType.values())
        .filter(v -> v.getStandardCode().equals(standardCode.toLowerCase()))
        .findAny()
        .orElseThrow(()-> new InvalidValueException(ErrorCode.INVALID_MIME_TYPE_EXCEPTION));
    }

  }

  @Builder
  public SchemaAttribute(Schemas schema, String attributeCode, String attributeName, String mimeType) {
    this.schema = schema;
    this.attributeCode = attributeCode;
    this.attributeName = attributeName;
    this.mimeType = MimeType.ofStandardCode(mimeType);
  }
}

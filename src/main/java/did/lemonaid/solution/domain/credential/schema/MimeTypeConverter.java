package did.lemonaid.solution.domain.credential.schema;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MimeTypeConverter implements AttributeConverter<String, SchemaAttribute.MimeType> {

  @Override
  public SchemaAttribute.MimeType convertToDatabaseColumn(String attribute) {
    return SchemaAttribute.MimeType.ofStandardCode(attribute);
  }

  @Override
  public String convertToEntityAttribute(SchemaAttribute.MimeType dbData) {
    return dbData.getStandardCode();
  }
}

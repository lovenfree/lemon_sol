package did.lemonaid.solution.interfaces.credential;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class SchemaDto {
  @Getter
  @Builder
  @ToString
  public static class Main {
    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0")
    private  final String schemaId;
    @Schema(description = "Schema Name", example = "사원증")
    private final String schemaName;
    @Schema(description = "Schema attribute List")
    private final List<SchemaDto.SchemaAttributeDto> schemaAttributeList;
  }


  @Getter
  @Builder
  @ToString
  public static class SchemaAttributeDto {
    @Schema(description = "Schema Attribute Code", example = "name")
    private final String attributeCode;
    @Schema(description = "Schema attribute Name", example = "name")
    private final String attributeName;
    @Schema(description = "Schema attribute Mime Type", example = "TEXT/PLAIN")
    private final String mimeType;
  }
}

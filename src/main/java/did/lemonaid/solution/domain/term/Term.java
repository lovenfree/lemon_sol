package did.lemonaid.solution.domain.term;

import did.lemonaid.solution.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="TERM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

  @Column(name="TERM_CONTENT", nullable = false)
  private String termContent;

}

package did.lemonaid.solution.domain.qna;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="TENANT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnA {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

}





package did.lemonaid.solution.infrastructure.notification;

import did.lemonaid.solution.domain.notification.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {
  @Override
  public void sendEmail(String email, String title, String description) {

  }
}

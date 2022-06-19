package did.lemonaid.solution.domain.notification;

public interface NotificationService {
  void sendEmail(String email, String title, String description);
}

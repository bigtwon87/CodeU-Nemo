package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import java.time.Instant;

public class NotificationServlet extends HttpServlet{
  private UserStore userStore;
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
  }
  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

//TODO Decide if other notification types beyond messages are needed
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
        String username = request.getParameter("username");
        User user = userStore.getUser(username);
        Instant userLookedAtPage = Instant.now();

        System.out.println(user.getLastSeenNotificationsTimestamp());
        user.setLastSeenNotificationTimestamp(userLookedAtPage);
        request.getRequestDispatcher("/WEB-INF/view/notifications.jsp").forward(request, response);
      }

}

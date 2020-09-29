package al.ikubinfo.healthometer.users.controller;

import static org.junit.Assert.assertEquals;

import al.ikubinfo.healthometer.HealthometerApp;
import al.ikubinfo.healthometer.HealthometerTestSupport;
import al.ikubinfo.healthometer.users.dto.AuthDto;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HealthometerApp.class)
public class AuthRestControllerTest extends HealthometerTestSupport {

  private static final String URL = "/auth";

  @Test
  void validAuthentication() throws Exception {
    val authDto = AuthDto.builder().username("admin").password("password").build();
    val response = createPost(URL, authDto, AuthDto.class);
    assertEquals(true, response.isValid());
  }

  @Test
  void invalidAuthentication() throws Exception {
    val authDto = AuthDto.builder().username("admin").password("password1").build();
    val response = createPost(URL, authDto, AuthDto.class);
    assertEquals(false, response.isValid());
  }
}

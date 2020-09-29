package al.ikubinfo.healthometer.activity.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import al.ikubinfo.commons.utils.JsonUtils;
import al.ikubinfo.healthometer.HealthometerApp;
import al.ikubinfo.healthometer.HealthometerTestSupport;
import al.ikubinfo.healthometer.users.dto.AuthDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(
    classes = HealthometerApp.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ActivityControllerTest extends HealthometerTestSupport {

  private static String tokenUser;
  private static String tokenAdmin;
  private static final String baseURL = "/activity";
  private static final String getActivityURL = "/activity" + "/{date}";

  static String getAuth(MockMvc mockMvc, String username, String password) throws Exception {
    AuthDto authDto = AuthDto.builder().username(username).password(password).build();
    MvcResult result =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.toJsonString(authDto))
                    .accept("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn();
    return result.getResponse().getHeader("Authorization");
  }

  @BeforeAll
  static void loginTest(@Autowired MockMvc mockMvc) throws Exception {
    String usernameUser = "user", usernameAdmin = "admin", password = "password";
    tokenUser = getAuth(mockMvc, usernameUser, password);
    tokenAdmin = getAuth(mockMvc, usernameAdmin, password);
  }

  @Test
  void shouldGetActivityByDateTest() {
    MvcResult result = getRequestWithAuthorization(getActivityURL, tokenUser);
    assertEquals(200, result.getResponse().getStatus());
  }
}

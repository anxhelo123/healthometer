package al.ikubinfo.healthometer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import al.ikubinfo.commons.utils.JsonUtils;
import lombok.val;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@AutoConfigureMockMvc
public class HealthometerTestSupport {

  @Autowired protected MockMvc mockMvc;

  protected <INPUT, OUTPUT> OUTPUT createPost(String url, INPUT dto, Class<OUTPUT> objectType) {
    OUTPUT resultDto = null;

    try {
      val result =
          mockMvc
              .perform(
                  MockMvcRequestBuilders.post(url)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(JsonUtils.toJsonString(dto)))
              .andExpect(status().isOk())
              .andReturn();
      resultDto = JsonUtils.toObject(result.getResponse().getContentAsString(), objectType);

    } catch (Exception e) {
      ExceptionUtils.rethrow(e);
    }
    return resultDto;
  }

  protected <INPUT, OUTPUT> OUTPUT createPostWithAuthorization(
      String url, String token, INPUT dto) {
    OUTPUT result = null;

    try {
      result =
          (OUTPUT)
              mockMvc
                  .perform(
                      MockMvcRequestBuilders.post(url)
                          .content(JsonUtils.toJsonString(dto))
                          .contentType(MediaType.APPLICATION_JSON)
                          .accept(MediaType.APPLICATION_JSON)
                          .header("Authorization", token))
                  .andExpect(status().isOk())
                  .andReturn();

    } catch (Exception e) {
      ExceptionUtils.rethrow(e);
    }
    return result;
  }

  protected <OUTPUT> OUTPUT getRequestWithAuthorization(String url, String token) {
    OUTPUT result = null;
    try {
      result =
          (OUTPUT)
              mockMvc
                  .perform(
                      MockMvcRequestBuilders.get(url, 2)
                          .header("Authorization", token)
                          .accept(MediaType.APPLICATION_JSON))
                  .andReturn();

    } catch (Exception e) {
      ExceptionUtils.rethrow(e);
    }

    return result;
  }

  protected <INPUT, OUTPUT> OUTPUT putRequestWithAuthorization(
      String url, String token, INPUT userDto) {
    OUTPUT result = null;

    try {
      result =
          (OUTPUT)
              mockMvc
                  .perform(
                      MockMvcRequestBuilders.put(url, 1)
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(JsonUtils.toJsonString(userDto))
                          .accept("application/json;charset=UTF-8")
                          .header("Authorization", token))
                  .andReturn();

    } catch (Exception e) {
      ExceptionUtils.rethrow(e);
    }

    return result;
  }

  protected <OUTPUT> OUTPUT deletePost(String token) {
    OUTPUT result = null;
    String baseURL = "/users";
    String getUserUrl = baseURL + "/{id}";

    try {
      result =
          (OUTPUT)
              mockMvc
                  .perform(
                      MockMvcRequestBuilders.delete(getUserUrl, 1)
                          .header("Authorization", token)
                          .contentType(MediaType.APPLICATION_JSON)
                          .accept(MediaType.APPLICATION_JSON))
                  .andReturn();

    } catch (Exception e) {
      ExceptionUtils.rethrow(e);
    }

    return result;
  }
}

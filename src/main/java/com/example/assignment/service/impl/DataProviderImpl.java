package com.example.assignment.service.impl;

import com.example.assignment.service.DataProvider;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataProviderImpl implements DataProvider {

  @Value("${dataUrl}")
  private String dataUrl;

  @Override
  public List<Object> getOriginalPosts() {
    RestTemplate restTemplate = new RestTemplate();

    List<Object> objects = restTemplate.getForObject(dataUrl, List.class);

    return objects;
  }
}

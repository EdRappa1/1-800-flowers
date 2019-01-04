package com.example.assignment.controller;

import com.example.assignment.pojo.CountResponse;
import com.example.assignment.pojo.Post;
import com.example.assignment.service.DataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

  private static final String NEW_VALUE = "1800Flowers";
  private DataProvider dataProvider;

  @Autowired
  public SampleController(DataProvider dataProvider) {
    this.dataProvider = dataProvider;
  }

  @GetMapping("/count")
  @ResponseBody
  public CountResponse getCount() {

    List<Object> posts = dataProvider.getOriginalPosts();
    if (posts == null) {
      return new CountResponse(0);
    }

    Set<Integer> uniqueIds = new HashSet<>();
    ObjectMapper mapper = new ObjectMapper();

    for (Object obj: posts) {
      Post post = mapper.convertValue(obj, Post.class);
      uniqueIds.add(post.getUserId());
    }

    int size = uniqueIds.size();
    return new CountResponse(size);
  }

  @GetMapping("/posts")
  @ResponseBody
  public List<Object> getPosts() {

    List<Object> posts = dataProvider.getOriginalPosts();

    if (posts.size() < 4) {
      return posts;
    }

    ObjectMapper mapper = new ObjectMapper();
    Post post = mapper.convertValue(posts.get(3), Post.class);
    post.setTitle(NEW_VALUE);
    post.setBody(NEW_VALUE);
    posts.set(3, post);
    return posts;
  }

}

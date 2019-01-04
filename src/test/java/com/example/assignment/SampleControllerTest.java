package com.example.assignment;

import com.example.assignment.controller.SampleController;
import com.example.assignment.pojo.CountResponse;
import com.example.assignment.pojo.Post;
import com.example.assignment.service.DataProvider;
import com.example.assignment.service.impl.DataProviderImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SampleControllerTest {

  @Test
  public void testCountNull() {

    DataProvider provider = EasyMock.createMock(DataProvider.class);

    List<Object> emptyList = null;

    EasyMock.expect(provider.getOriginalPosts()).andReturn(emptyList);
    EasyMock.replay(provider);

    SampleController controller = new SampleController(provider);

    CountResponse count = controller.getCount();

    Assert.assertEquals(0, count.getCount());
  }

  @Test
  public void testCountZero() {

    DataProvider provider = EasyMock.createMock(DataProvider.class);

    List<Object> emptyList = new ArrayList<>();

    EasyMock.expect(provider.getOriginalPosts()).andReturn(emptyList);
    EasyMock.replay(provider);

    SampleController controller = new SampleController(provider);

    CountResponse count = controller.getCount();

    Assert.assertEquals(0, count.getCount());
  }

  @Test
  public void testCountOne() {

    DataProvider provider = EasyMock.createMock(DataProvider.class);

    List<Object> emptyList = new ArrayList<>();
    emptyList.add(new Post(1, 2, "Title", "Body"));

    EasyMock.expect(provider.getOriginalPosts()).andReturn(emptyList);
    EasyMock.replay(provider);

    SampleController controller = new SampleController(provider);

    CountResponse count = controller.getCount();

    Assert.assertEquals(1, count.getCount());
  }

  @Test
  public void testCountMany() {

    DataProvider provider = EasyMock.createMock(DataProvider.class);

    List<Object> emptyList = new ArrayList<>();
    emptyList.add(new Post(1, 2, "Title1", "Body1"));
    emptyList.add(new Post(1, 3, "Title2", "Body2"));

    EasyMock.expect(provider.getOriginalPosts()).andReturn(emptyList);
    EasyMock.replay(provider);

    SampleController controller = new SampleController(provider);

    CountResponse count = controller.getCount();

    Assert.assertEquals(1, count.getCount());
  }

  @Test
  public void testPosts() {

    DataProvider provider = EasyMock.createMock(DataProvider.class);

    List<Object> list = new ArrayList<>();
    list.add(new Post(1, 2, "Title1", "Body1"));
    list.add(new Post(1, 3, "Title2", "Body2"));
    list.add(new Post(1, 3, "Title3", "Body3"));
    list.add(new Post(1, 3, "Title4", "Body4"));

    EasyMock.expect(provider.getOriginalPosts()).andReturn(list);
    EasyMock.replay(provider);

    SampleController controller = new SampleController(provider);

    String NEW_VALUE = "1800Flowers";

    List<Object> posts = controller.getPosts();
    ObjectMapper mapper = new ObjectMapper();
    Post post = mapper.convertValue(posts.get(3), Post.class);
    Assert.assertEquals(NEW_VALUE, post.getTitle());
    Assert.assertEquals(NEW_VALUE, post.getBody());
  }
}

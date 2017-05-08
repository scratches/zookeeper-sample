/**
 * 
 */
package com.dianrong.springboot.boot.client;

import java.sql.Timestamp;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.zookeeper.discovery.ZookeeperInstance;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongtao
 *
 */
@RestController
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
//@EnableCircuitBreaker
public class HelloServer {

  @Autowired
  private CuratorFramework curator;

  @Autowired
  SpringClientFactory springClientFactory;

  @Autowired
  InstanceSerializer<ZookeeperInstance> instanceSerializer;

  @RequestMapping("/serviceCheck")
  String serviceCheck() {
    return "true";
  }

  @RequestMapping("/hello")
  String hello() {
    System.out.println(
        new Timestamp(System.currentTimeMillis()) + ":" + "this is hello world from hello server");
    return "Hello World!";
  }

  @RequestMapping(value = "/testPost", method = RequestMethod.POST,
      consumes = {"text/*", "application/*"})
  void testPost(@RequestBody IDNameDto idNameDto) {
    System.out.println(idNameDto);
  }


  public static void main(String[] args) throws Exception {
    SpringApplication.run(HelloServer.class, args);
  }
}

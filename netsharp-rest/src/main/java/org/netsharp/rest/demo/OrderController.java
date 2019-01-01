package org.netsharp.rest.demo;

import java.util.Date;

import org.netsharp.rest.core.annotation.ApiVersion;
import org.netsharp.util.DateManage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/order")
@ApiVersion(1)
public class OrderController {
	
	
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name) {
    	
      return "hello " + name + " "+ DateManage.toMillisecondsString(new Date());
    }
    
    
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2(@RequestParam("name") String name) {
    	
      return "hello2 " + name + " "+ DateManage.toMillisecondsString(new Date());
    }

}

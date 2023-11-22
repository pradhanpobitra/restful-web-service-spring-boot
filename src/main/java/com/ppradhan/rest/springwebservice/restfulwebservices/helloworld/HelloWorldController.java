package com.ppradhan.rest.springwebservice.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    @Autowired
    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
    public HelloWorldBean helloWorld() {
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWordPathVariable(@PathVariable("name") String name) {
        return new HelloWorldBean("Hello " + name);
    }

    @GetMapping("/hello-world-i18n")
    public String helloWorldi18n() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
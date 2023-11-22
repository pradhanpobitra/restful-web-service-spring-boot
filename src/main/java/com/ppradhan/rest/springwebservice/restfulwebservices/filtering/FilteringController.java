package com.ppradhan.rest.springwebservice.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/*
    Dynamic JSON filtering example
*/

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue getBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue getBeanList() {
        SomeBean bean1 = new SomeBean("value1", "value2", "value3");
        SomeBean bean2 = new SomeBean("value4", "value5", "value6");
        List<SomeBean> beanList = Arrays.asList(bean1, bean2);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanList);
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value2", "value3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}

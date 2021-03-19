package com.prajyot.webServices.WebServices.Filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
       SomeBean someBean =  new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
       FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);


        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);
        return mapping;

    }
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBean(){

        List<SomeBean> SomeBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value12", "value33", "value32"));
        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(SomeBeanList);
        mapping.setFilters(filters);
        return mapping ;
    }
}

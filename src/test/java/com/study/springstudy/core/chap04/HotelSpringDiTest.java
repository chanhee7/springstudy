package com.study.springstudy.core.chap04;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class HotelSpringDiTest {


    @Test
    void diTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HotelConfig.class);

        Hotel hotel = context.getBean(Hotel.class);
        System.out.println("hotel = " + hotel.getRestaurant());
        hotel.inform();


    }

}
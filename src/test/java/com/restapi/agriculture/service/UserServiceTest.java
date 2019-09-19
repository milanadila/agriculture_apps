//package com.restapi.agriculture.service;
//
//import com.restapi.agriculture.dto.UserReqDto;
//import com.restapi.agriculture.dto.UserRespDto;
//import com.restapi.agriculture.entity.User;
//import com.restapi.agriculture.exception.DataNotFoundException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@Slf4j
//public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void findAll() {
//        List<User> userRespDtos = userService.findAll();
//        assertThat(userRespDtos.size()).isNotNull();
//        for (int i = 0; i < userRespDtos.size(); i++) {
//        }
//    }
//
//    @Test
//    @Transactional
//    public void findById() {
//
//        Long id = 1;
//        User user = userService.findById(id);
//
//        assertThat(user).hasFieldOrProperty("idUser")
//                        .hasFieldOrProperty("userName")
//                        .hasFieldOrProperty("balanceUser");
//
//    }
//
//    @Test
//    public void create() {
//        UserReqDto userReqDto = new UserReqDto();
//
//        String userName = "Eleven";
//        BigDecimal balanceUser = BigDecimal.valueOf(350000);
//
//        userReqDto.setUserName(userName);
//        userReqDto.setBalanceUser(balanceUser);
//
//        UserRespDto userRespDto = userService.create(userReqDto);
//        assertThat(userReqDto.getUserName()).isEqualTo(userName);
//        assertThat(userReqDto.getBalanceUser()).isEqualTo(balanceUser);
//    }
//
//    @Test
//    @Transactional
//    public void update() {
//        UserReqDto userReqDto = new UserReqDto();
//
//        Long id = 1;
//        String userName = "milan";
//        BigDecimal balanceUser = BigDecimal.valueOf(150000);
//
//        userReqDto.setUserName(userName);
//        userReqDto.setBalanceUser(balanceUser);
//
//        UserRespDto userRespDto = userService.update(id, userReqDto);
//        assertThat(userRespDto.getUserName()).isEqualTo(userName);
//        assertThat(userRespDto.getBalanceUser()).isEqualTo(balanceUser);
//    }
//
//    @Test
//    public void delete() {
//        Long id = 4;
//        List<User> users = userService.findAll();
//        userService.delete(id);
//
//        List<User> userServiceUpdate = userService.findAll();
//        assertThat(users.size()).isGreaterThan(userServiceUpdate.size());
//    }
//
//    @Test (expected = DataNotFoundException.class)
//    @Transactional
//    public void failedById() {
//        Long id = 40;
//        User user = userService.findById(id);
//        assertThat(user.getIdUser()).isNotEqualTo(id);
//    }
//
//}

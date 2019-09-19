package com.restapi.agriculture.service;


import com.restapi.agriculture.config.AES;
import com.restapi.agriculture.dto.UserReqDto;
import com.restapi.agriculture.dto.UserRespDto;
import com.restapi.agriculture.entity.User;
import com.restapi.agriculture.exception.DataNotFoundException;
import com.restapi.agriculture.repostory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AES aes;

    public List<UserRespDto> findAll() {

        List<User> listUser = userRepository.findAll();
        List<UserRespDto> listUserDto = new ArrayList<>();
        for (User user : listUser) {
            UserRespDto userRespDto = new UserRespDto();
            userRespDto.setUserName(user.getUserName());
            userRespDto.setBalanceUser(user.getBalanceUser());
            userRespDto.setIdUser(user.getIdUser());
            userRespDto.setIdUserEncrypt(aes.encrypt(user.getIdUser().toString()));

            listUserDto.add(userRespDto);
        }

        return listUserDto;
    }


    public UserRespDto findById(Integer id) {

        User user = userRepository.findByIdUser(id);
        UserRespDto userRespDto = new UserRespDto();
        userRespDto.setUserName(user.getUserName());
        userRespDto.setIdUser(user.getIdUser());
        userRespDto.setBalanceUser(user.getBalanceUser());
        userRespDto.setIdUserEncrypt(aes.encrypt(user.getIdUser().toString()));

        return userRespDto;
    }


    public UserRespDto create(UserReqDto userReqDto) {
        User user = new User();
        user.setUserName(userReqDto.getUserName());
        user.setBalanceUser(userReqDto.getBalanceUser());

        userRepository.save(user);

        UserRespDto userRespDto = new UserRespDto();
        userRespDto.setIdUser(user.getIdUser());
        userRespDto.setUserName(user.getUserName());
        userRespDto.setBalanceUser(user.getBalanceUser());

        return userRespDto;
    }

    public UserRespDto update(Integer id, UserReqDto userReqDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("Data Not Found");

        User user = new User();
        user.setIdUser(id);
        user.setUserName(userReqDto.getUserName());
        user.setBalanceUser(userReqDto.getBalanceUser());

        UserRespDto userRespDto = new UserRespDto();
        userRespDto.setIdUser(id);
        userRespDto.setUserName(user.getUserName());
        userRespDto.setBalanceUser(user.getBalanceUser());

        userRepository.save(user);
        return userRespDto;
    }

    public void delete (Integer id) {
        userRepository.deleteById(id);
    }

    public void save (User user) {
        userRepository.save(user);
    }
}


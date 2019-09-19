//package com.restapi.agriculture.service;
//
//
//import com.restapi.agriculture.dto.TransactionReqDto;
//import com.restapi.agriculture.dto.TransactionRespDto;
//import com.restapi.agriculture.entity.Transaction;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Date;
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@Slf4j
//public class TransactionServiceTest {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @Test
//    public void findAll() {
//        List<Transaction> transactionRespDtos = transactionService.findAll();
//        assertThat(transactionRespDtos.size()).isNotNull();
//        for (int i = 0; i < transactionRespDtos.size(); i++) {
//        }
//    }
//
//    @Test
//    @Transactional
//    public void findById() {
//
//        Integer id = 1;
//        Transaction transaction = transactionService.findById(id);
//
//        assertThat(transaction).hasFieldOrProperty("idTransaction")
//                .hasFieldOrProperty("idUser")
//                .hasFieldOrProperty("idProduct")
//                .hasFieldOrProperty("totalTransaction")
//                .hasFieldOrProperty("createdDate")
//                .hasFieldOrProperty("quantityProduct")
//                .hasFieldOrProperty("balanceUser");
//    }
//
//    @Test
//    public void create() {
//        TransactionReqDto transactionReqDto = new TransactionReqDto();
//
//        Integer idUser = 1;
//        Integer idProduct = 1;
//        int quantityProduct = 2;
//        Date createdDate = Date.valueOf("2019-06-17");
//
//        transactionReqDto.setIdUser(idUser);
//        transactionReqDto.setIdProduct(idProduct);
//        transactionReqDto.setQuantityProduct(quantityProduct);
//        transactionReqDto.setCreatedDate(createdDate);
//
//        TransactionRespDto transactionRespDto = transactionService.create(transactionReqDto);
//        assertThat(transactionRespDto.getIdUser()).isEqualTo(idUser);
//        assertThat(transactionRespDto.getIdProduct()).isEqualTo(idProduct);
//        assertThat(transactionRespDto.getQuantityProduct()).isEqualTo(quantityProduct);
//        assertThat(transactionRespDto.getCreatedDate()).isEqualTo(createdDate);
//    }
//}

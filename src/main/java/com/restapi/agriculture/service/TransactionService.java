package com.restapi.agriculture.service;

import com.restapi.agriculture.dto.ProductRespDto;
import com.restapi.agriculture.dto.TransactionReqDto;
import com.restapi.agriculture.dto.TransactionRespDto;
import com.restapi.agriculture.dto.UserRespDto;
import com.restapi.agriculture.entity.Product;
import com.restapi.agriculture.entity.Transaction;
import com.restapi.agriculture.entity.User;
import com.restapi.agriculture.repostory.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Integer id) {return transactionRepository.findById(id).orElseThrow(RuntimeException::new);}

    public TransactionRespDto create (TransactionReqDto transactionReqDto) {

        ProductRespDto product = productService.findById(transactionReqDto.getIdProduct());
        BigDecimal getProductPrice = product.getProductPrice();
        int getQuantityProduct = transactionReqDto.getQuantityProduct();
        BigDecimal a = getProductPrice.multiply(new BigDecimal(getQuantityProduct));

        //Update stock product
        int stockProduct = product.getStockProduct()-transactionReqDto.getQuantityProduct();
        if (stockProduct < 0 ) {
            throw new IllegalArgumentException("Stock kurang, transaksi tidak bisa dilakukan");
        }


        //Update balance
        UserRespDto users = userService.findById(transactionReqDto.getIdUser());
        BigDecimal balanceUserStart = users.getBalanceUser();
        BigDecimal balanceUserNew = balanceUserStart.subtract(a);
        int balanceUserNewInt = balanceUserNew.intValue();
        if (balanceUserNewInt < 0) {
            throw new IllegalArgumentException("Saldo tidak cukup, transaksi tidak bisa dilakukan");
        }

        UserRespDto user = userService.findById(transactionReqDto.getIdUser());
        String transactionUser = user.getUserName();

        ProductRespDto products = productService.findById(transactionReqDto.getIdProduct());
        String transactionProduct = products.getProductName();

        Transaction transaction = new Transaction();
        transaction.setCreatedDate(transactionReqDto.getCreatedDate());
        product.setStockProduct(stockProduct);
        transaction.setTotalTransaction(a);
        transaction.setIdUser(transactionReqDto.getIdUser());
        transaction.setIdProduct(transactionReqDto.getIdProduct());
        transaction.setQuantityProduct(transactionReqDto.getQuantityProduct());
        users.setBalanceUser(balanceUserNew);
        transaction.setBalanceUser(balanceUserNew);
        transaction.setUserName(transactionUser);
        transaction.setProductName(transactionProduct);

        Product productEntity = new Product();
        productEntity.setStockProduct(product.getStockProduct());
        productEntity.setIdProduct(product.getIdProduct());
        productEntity.setProductName(product.getProductName());
        productEntity.setProductPrice(product.getProductPrice());

        productService.save(productEntity);

        User userEntity = new User();
        userEntity.setUserName(user.getUserName());
        userEntity.setIdUser(user.getIdUser());
        userEntity.setBalanceUser(balanceUserNew);

        userService.save(userEntity);

        transactionRepository.save(transaction);

        TransactionRespDto transactionRespDto = new TransactionRespDto();

        transactionRespDto.setProductName(transaction.getProductName());
        transactionRespDto.setUserName(transaction.getUserName());
        transactionRespDto.setCreatedDate(transaction.getCreatedDate());
        transactionRespDto.setTotalTransaction(transaction.getTotalTransaction());
        transactionRespDto.setIdUser(transaction.getIdUser());
        transactionRespDto.setIdProduct(transaction.getIdProduct());
        transactionRespDto.setStockProduct(product.getStockProduct());
        transactionRespDto.setIdTransaction(transaction.getIdTransaction());
        transactionRespDto.setQuantityProduct(transaction.getQuantityProduct());
        transactionRespDto.setBalanceUser(balanceUserNew);

        return transactionRespDto;
    }
}

package com.example.itrum.web.contoller;

import com.example.itrum.service.QueueManager;
import com.example.itrum.service.WalletService;
import com.example.itrum.web.controller.WalletController;
import com.example.itrum.web.mapper.WalletMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class WalletControllerTest {

    @Mock
    WalletService walletService;
    MockMvc mockMvc;
    @Mock
    QueueManager queueManager;
    @Mock
    WalletMapper walletMapper;
    @InjectMocks
    WalletController walletController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

    UUID walletId = UUID.randomUUID();

    BigDecimal amount = BigDecimal.valueOf(3000);

    @Test
    @DisplayName("GET /api/v1/wallets/{WALLET_UUID}, принимает UUID и возвращает баланс пользователя")
    void getBalance_ReturnsValidAmountTest() throws Exception {
        var requestBuild = get("/api/v1/wallets/{WALLET_UUID}", walletId);
        when(walletService.getBalance(walletId)).thenReturn(BigDecimal.valueOf(3000));
        mockMvc.perform(requestBuild)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(amount));
    }
}

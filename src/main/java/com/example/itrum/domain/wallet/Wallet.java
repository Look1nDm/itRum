package com.example.itrum.domain.wallet;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "wallets")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID walletId;
    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;
    private BigDecimal amount;
}

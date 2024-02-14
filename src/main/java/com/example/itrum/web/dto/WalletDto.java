package com.example.itrum.web.dto;

import com.example.itrum.domain.wallet.TypeWallet;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@Schema(description = "Wallet Dto")
public class WalletDto {

    @NotNull(message = "Id must be not null.")
    @Schema(description = "Wallet id") //дописать example из бд
    private UUID walletId;

    @NotNull(message = "Must select the type of operation.")
    @Schema(description = "Operation type", example = "DEPOSIT")
    private TypeWallet typeWallet;

    @NotNull(message = "Need to select the magnitude of the change.")
    @Schema(description = "Amount operation", example = "1000")
    private BigDecimal amount;
}

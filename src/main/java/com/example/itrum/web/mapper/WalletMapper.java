package com.example.itrum.web.mapper;

import com.example.itrum.domain.wallet.Wallet;
import com.example.itrum.web.dto.WalletDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDto toDto (Wallet wallet);
    Wallet toEntity(WalletDto walletDto);
}

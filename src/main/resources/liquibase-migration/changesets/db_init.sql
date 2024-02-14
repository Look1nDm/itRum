create table if not exists wallets
(
    wallet_id       uuid primary key,
    type_wallet     varchar(255) not null,
    amount          bigserial not null
)
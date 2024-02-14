create table if not exists wallets
(
    wallet_id      uuid primary key,
    operation_type varchar(255) not null,
    amount         bigserial    not null
)
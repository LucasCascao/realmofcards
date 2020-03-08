
CREATE TABLE administrador (
    adm_id         INT NOT NULL,
    adm_pessoa_id  INT NOT NULL
);

CREATE TABLE bandeira (
    ban_id    INT NOT NULL,
    ban_nome  VARCHAR(50) NOT NULL
);

CREATE TABLE carta (
    car_id            INT NOT NULL,
    car_nome          VARCHAR(50) NOT NULL,
    car_descricao     VARCHAR(250) NOT NULL,
    car_valor         DECIMAL(4, 2) NOT NULL,
    car_jogo_id       INT NOT NULL,
    car_categoria_id  INT NOT NULL,
    car_status_id     INT NOT NULL
);

CREATE TABLE cartao (
    crt_id                INT NOT NULL,
    crt_numero            VARCHAR(16) NOT NULL,
    crt_codigo_seguranca  VARCHAR(3) NOT NULL,
    crt_bandeira_id       INT NOT NULL,
    crt_clientei_id       INT NOT NULL
);

CREATE TABLE categoria_carta (
    cat_id    SERIAL NOT NULL,
    cat_nome  VARCHAR(40) NOT NULL
);

CREATE TABLE cidade (
    cid_id      serial NOT NULL,
    cid_nome    VARCHAR(60) NOT NULL,
    cid_est_id  INT NOT NULL
);

CREATE TABLE cliente (
    cli_id         SERIAL NOT NULL,
    cli_pessoa_id  INT NOT NULL
);

CREATE TABLE endereco (
    end_id          SERIAL NOT NULL,
    end_logradouro  VARCHAR(80) NOT NULL,
    end_numero      VARCHAR(6) NOT NULL,
    end_bairro      VARCHAR(70) NOT NULL,
    end_cep         VARCHAR(8) NOT NULL,
    end_comentario  VARCHAR(200) NOT NULL,
    end_cid_id      INT NOT NULL,
    end_cli_id      INT NOT NULL
);

CREATE TABLE estado (
    est_id     SERIAL NOT NULL,
    est_sigla  VARCHAR(2),
    est_nome   VARCHAR(50) NOT NULL
);

CREATE TABLE estoque (
    est_id          SERIAL NOT NULL,
    est_quantidade  INT NOT NULL,
    est_carta_id    INT NOT NULL
);

CREATE TABLE item_pedido (
    ipd_id          SERIAL NOT NULL,
    ipd_quantidade  INT NOT NULL,
    ipd_carta_id    INT NOT NULL,
    ipd_pedido_id   INT NOT NULL
);

CREATE TABLE jogo (
    jog_id    SERIAL NOT NULL,
    jog_nome  VARCHAR(50) NOT NULL
);

CREATE TABLE jogo_categoria_carta (
    jct_carta_id  SERIAL NOT NULL,
    jct_jogo_id   INT NOT NULL
);

CREATE TABLE log_pedido (
    log_id         SERIAL NOT NULL,
    log_descricao  VARCHAR(250) NOT NULL,
    log_pedido_id  INT NOT NULL
);

CREATE TABLE pedido (
    ped_id                SERIAL NOT NULL,
    ped_administrador_id  INT NOT NULL,
    ped_clientei_id       INT NOT NULL,
    ped_pedido_id         INT NOT NULL
);

CREATE TABLE pessoa (
    pes_id               SERIAL NOT NULL,
    pes_nome             VARCHAR(30) NOT NULL,
    pes_sobrenome        VARCHAR(60),
    pes_sexo             VARCHAR(1) NOT NULL,
    pes_data_nascimento  DATE NOT NULL,
    pes_cpf              VARCHAR(11) NOT NULL,
    pes_email            VARCHAR(80) NOT NULL,
    pes_nome_usuario     VARCHAR(30) NOT NULL,
    pes_senha            VARCHAR(30) NOT NULL
);

CREATE TABLE status_carta (
    scr_id      SERIAL NOT NULL,
    scr_status  INT NOT NULL
);

CREATE TABLE status_pedido (
    spd_id      SERIAL NOT NULL,
    spd_status  INT NOT NULL
);

ALTER TABLE administrador ADD CONSTRAINT administrador_pk PRIMARY KEY ( adm_id );

ALTER TABLE bandeira ADD CONSTRAINT bandeira_pk PRIMARY KEY ( ban_id );

ALTER TABLE carta ADD CONSTRAINT carta_pk PRIMARY KEY ( car_id );

ALTER TABLE cartao ADD CONSTRAINT cartao_pk PRIMARY KEY ( crt_id );

ALTER TABLE categoria_carta ADD CONSTRAINT categoria_carta_pk PRIMARY KEY ( cat_id );

ALTER TABLE cidade ADD CONSTRAINT cidade_pk PRIMARY KEY ( cid_id );

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( cli_id );

ALTER TABLE endereco ADD CONSTRAINT endereco_pk PRIMARY KEY ( end_id );

ALTER TABLE estado ADD CONSTRAINT estado_pk PRIMARY KEY ( est_id );

ALTER TABLE estoque ADD CONSTRAINT estoque_pk PRIMARY KEY ( est_id );

ALTER TABLE item_pedido ADD CONSTRAINT item_pedido_pk PRIMARY KEY ( ipd_id );

ALTER TABLE jogo ADD CONSTRAINT jogo_pk PRIMARY KEY ( jog_id );

ALTER TABLE log_pedido ADD CONSTRAINT log_pedido_pk PRIMARY KEY ( log_id );

ALTER TABLE pedido ADD CONSTRAINT pedido_pk PRIMARY KEY ( ped_id );

ALTER TABLE pessoa ADD CONSTRAINT pessoa_pk PRIMARY KEY ( pes_id );

ALTER TABLE status_carta ADD CONSTRAINT carta_status_pk PRIMARY KEY ( scr_id );

ALTER TABLE status_pedido ADD CONSTRAINT status_pedido_pk PRIMARY KEY ( spd_id );

ALTER TABLE administrador
    ADD CONSTRAINT administrador_pessoa_fk FOREIGN KEY ( adm_pessoa_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE carta
    ADD CONSTRAINT carta_carta_status_fk FOREIGN KEY ( car_status_id )
        REFERENCES status_carta ( scr_id );

ALTER TABLE carta
    ADD CONSTRAINT carta_categoria_carta_fk FOREIGN KEY ( car_categoria_id )
        REFERENCES categoria_carta ( cat_id );

ALTER TABLE carta
    ADD CONSTRAINT carta_jogo_fk FOREIGN KEY ( car_jogo_id )
        REFERENCES jogo ( jog_id );

ALTER TABLE cartao
    ADD CONSTRAINT cartao_bandeira_fk FOREIGN KEY ( crt_bandeira_id )
        REFERENCES bandeira ( ban_id );

ALTER TABLE cartao
    ADD CONSTRAINT cartao_cliente_fk FOREIGN KEY ( crt_clientei_id )
        REFERENCES cliente ( cli_id );

ALTER TABLE cidade
    ADD CONSTRAINT cidade_estado_fk FOREIGN KEY ( cid_est_id )
        REFERENCES estado ( est_id );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_pessoa_fk FOREIGN KEY ( cli_pessoa_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_cidade_fk FOREIGN KEY ( end_cid_id )
        REFERENCES cidade ( cid_id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_cliente_fk FOREIGN KEY ( end_cli_id )
        REFERENCES cliente ( cli_id );

ALTER TABLE estoque
    ADD CONSTRAINT estoque_carta_fk FOREIGN KEY ( est_carta_id )
        REFERENCES carta ( car_id );

ALTER TABLE item_pedido
    ADD CONSTRAINT item_pedido_carta_fk FOREIGN KEY ( ipd_carta_id )
        REFERENCES carta ( car_id );

ALTER TABLE item_pedido
    ADD CONSTRAINT item_pedido_pedido_fk FOREIGN KEY ( ipd_pedido_id )
        REFERENCES pedido ( ped_id );

ALTER TABLE jogo_categoria_carta
    ADD CONSTRAINT jogo_categoria_carta_fk FOREIGN KEY ( jct_carta_id )
        REFERENCES categoria_carta ( cat_id );

ALTER TABLE jogo_categoria_carta
    ADD CONSTRAINT jogo_categoria_jogo_fk FOREIGN KEY ( jct_jogo_id )
        REFERENCES jogo ( jog_id );

ALTER TABLE log_pedido
    ADD CONSTRAINT log_pedido_pedido_fk FOREIGN KEY ( log_pedido_id )
        REFERENCES pedido ( ped_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_administrador_fk FOREIGN KEY ( ped_administrador_id )
        REFERENCES administrador ( adm_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_cliente_fk FOREIGN KEY ( ped_clientei_id )
        REFERENCES cliente ( cli_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_status_pedido_fk FOREIGN KEY ( ped_pedido_id )
        REFERENCES status_pedido ( spd_id );
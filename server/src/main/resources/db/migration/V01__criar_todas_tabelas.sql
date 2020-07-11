CREATE TABLE bandeira (
    ban_id    SERIAL NOT NULL,
    ban_nome  VARCHAR(50) NOT NULL
);

CREATE TABLE carta (
    car_id                       SERIAL NOT NULL,
    car_codigo                   VARCHAR(50) NOT NULL UNIQUE,
    car_nome                     VARCHAR(50) NOT NULL UNIQUE,
    car_descricao                VARCHAR(500) NOT NULL,
    car_data_cad                 DATE NOT NULL,
    car_valor_compra             DECIMAL(4, 2) NOT NULL,
    car_quantidade_disponivel    INT NOT NULL,
    car_quantidade_estoque       INT NOT NULL,
    car_imagem_path              VARCHAR(250) NOT NULL,
    car_jogo_id                  INT NOT NULL,
    car_categoria_id             INT NOT NULL,
    car_status_id                INT NOT NULL,
    car_grupo_precificacao_id    INT NOT NULL
);

CREATE TABLE cartao_credito (
    crt_id                SERIAL NOT NULL,
    crt_numero            VARCHAR(16) NOT NULL UNIQUE,
    crt_codigo_seguranca  VARCHAR(3) NOT NULL,
    crt_titular_nome      VARCHAR(16) NOT NULL,
    crt_cpf_titular       VARCHAR(11) NOT NULL,
    crt_vencimento_mes    VARCHAR(2) NOT NULL,
    crt_vencimento_ano    VARCHAR(4) NOT NULL,
    crt_bandeira_id       INT NOT NULL,
    crt_pessoa_id         INT NOT NULL,
    crt_status_id         INT NOT NULL
);

CREATE TABLE categoria_carta (
    cat_id    SERIAL NOT NULL,
    cat_nome  VARCHAR(40) NOT NULL
);

CREATE TABLE item (
    itm_id                      SERIAL NOT NULL,
    itm_quantidade              INT NOT NULL,
    itm_quantidade_troca        INT NOT NULL,
    itm_quantidade_devolucao    INT NOT NULL,
    itm_carta_id                INT NOT NULL,
    itm_status_id               INT NOT NUll
);

CREATE TABLE status_transicao(
    stt_id              SERIAL NOT NULL,
    stt_status_nome     VARCHAR(20) NOT NULL
);

CREATE TABLE tipo_transicao(
    tit_id              SERIAL NOT NULL,
    tit_tipo_nome     VARCHAR(20) NOT NULL
);

CREATE TABLE transicao (
    tsc_id                  SERIAL NOT NULL,
    tsc_codigo              VARCHAR(60) NOT NULL,
    tsc_subtotal            DECIMAL(8,2) NOT NULL,
    tsc_cupom_id            INT,
    tsc_rastreio_id         INT,
    tsc_pedido_id           INT NOT NULL,
    tsc_status_trasicao_id  INT NOT NULL,
    tsc_tipo_transicao_id   INT NOT NULL
);

CREATE TABLE item_transicao (
    itc_id              SERIAL NOT NULL,
    itc_quantidade      INT NOT NULL,
    itc_item_id         INT NOT NULL,
    itc_transicao_id    INT NOT NULL
);

CREATE TABLE rastreio (
    rto_id                  SERIAL NOT NULL,
    rto_codigo_rastreio     VARCHAR(20) NOT NULL
);

CREATE TABLE cidade (
    cid_id         SERIAL NOT NULL,
    cid_nome       VARCHAR(60) NOT NULL,
    cid_estado_id  INT NOT NULL
);

CREATE TABLE cupom (
    cup_id              SERIAL NOT NULL,
    cup_codigo          VARCHAR(60) NOT NULL,
    cup_valor           DECIMAL(8, 2) NOT NULL,
    cup_data_criacao    DATE NOT NULL,
    cup_pessoa_id       INT,
    cup_status_id       INT NOT NULL,
    cup_tipo_cupom_id   INT NOT NULL
);

CREATE TABLE tipo_cupom (
    tcp_id          SERIAL NOT NULL,
    tcp_tipo_nome   VARCHAR(60) NOT NULL
);

CREATE TABLE endereco (
    end_id              SERIAL NOT NULL,
    end_logradouro      VARCHAR(80) NOT NULL,
    end_numero          VARCHAR(6) NOT NULL,
    end_bairro          VARCHAR(70) NOT NULL,
    end_cep             VARCHAR(8) NOT NULL,
    end_complemento     VARCHAR(200) NOT NULL,
    end_cidade_id       INT NOT NULL,
    end_pessoa_id       INT NOT NULL,
    end_status_id       INT NOT NULL
);

CREATE TABLE telefone (
    tel_id                  SERIAL NOT NULL,
    tel_ddd                 VARCHAR(3),
    tel_numero              VARCHAR(9),
    tel_pessoa_id           INT NOT NULL,
    tel_tipo_telefone_id    INT NOT NULL
);

CREATE TABLE tipo_telefone (
    ttl_id       SERIAL NOT NULL,
    ttl_tipo     VARCHAR(20)
);

CREATE TABLE estado (
    est_id     SERIAL NOT NULL,
    est_sigla  VARCHAR(2),
    est_nome   VARCHAR(50) NOT NULL
);

CREATE TABLE carrinho (
    crr_id            SERIAL NOT NULL,
    crr_pessoa_id     INT NOT NULL
);

CREATE TABLE carrinho_item (
    cri_id            SERIAL NOT NULL,
    cri_carrinho_id   INT NOT NULL,
    cri_item_id       INT NOT NULL
);

CREATE TABLE jogo (
    jog_id    SERIAL NOT NULL,
    jog_nome  VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE jogo_categoria_carta (
    jct_carta_id  SERIAL NOT NULL,
    jct_jogo_id   INT NOT NULL
);

CREATE TABLE pedido (
    ped_id                     SERIAL NOT NULL,
    ped_codigo_pedido          VARCHAR(50) NOT NULL,
    ped_administrador_id       INT,
    ped_cliente_id             INT NOT NULL,
    ped_status_pedido_id       INT NOT NULL,
    ped_valor_total            DECIMAL(8,2) NOT NULL,
    ped_valor_frete            DECIMAL(8,2) NOT NULL,
    ped_data_compra            DATE NOT NULL,
    ped_data_estimada          DATE NOT NULL,
    ped_endereco_id            INT NOT NULL,
    ped_rastreio_id            INT
);

CREATE TABLE pessoa (
    pes_id               SERIAL NOT NULL,
    pes_nome             VARCHAR(30) NOT NULL,
    pes_sobrenome        VARCHAR(60),
    pes_sexo             VARCHAR(1) NOT NULL,
    pes_data_nascimento  DATE NOT NULL,
    pes_cpf              VARCHAR(11) NOT null UNIQUE,
    pes_usuario_id       INT NOT NULL
);

CREATE TABLE status (
    sts_id      SERIAL NOT NULL,
    sts_status  VARCHAR(20) NOT NULL
);

CREATE TABLE status_pedido (
    spd_id      SERIAL NOT NULL,
    spd_status  VARCHAR(50) NOT NULL
);

CREATE TABLE tipo_usuario (
    tus_id         SERIAL NOT NULL,
    tus_nome_tipo  VARCHAR(30) NOT NULL
);

CREATE TABLE forma_pagamento (
    fpa_id                  SERIAL NOT NULL,
    fpa_valor_pagamento     DECIMAL(8,2) NOT NULL,
    fpa_cartao_credito_id   INT,
    fpa_cupom_id            INT
);

CREATE TABLE forma_pagamento_pedido (
    fpp_id                  SERIAL NOT NULL,
    fpp_pedido_id           INT NOT NULL,
    fpp_forma_pagamento_id  INT NOT NULL
);

CREATE TABLE item_pedido (
    itp_id            SERIAL NOT NULL,
    itp_item_id     INT NOT NULL,
    itp_pedido_id     INT NOT NULL
);

CREATE TABLE usuario (
    usu_id				 SERIAL NOT NULL,
    usu_codigo           VARCHAR(9) NOT NULL,
    usu_email            VARCHAR(80) NOT NULL UNIQUE,
    usu_senha            VARCHAR(60) NOT NULL,
    usu_tipo_usuario_id     INT NOT NULL,
    usu_status_id        INT NOT NULL
);

CREATE TABLE transacao_status_carta (
    tsc_id          SERIAL NOT NULL,
    tsc_motivo      VARCHAR(60) NOT NULL,
    tsc_status_id   INT NOT NULL,
    tsc_carta_id    INT NOT NULL
);

CREATE TABLE grupo_precificacao (
    gpr_id                  SERIAL NOT NULL,
    gpr_nome                VARCHAR(60) NOT NULL,
    gpr_valor_percentual    DECIMAL(8,2) NOT NULL
);

ALTER TABLE bandeira ADD CONSTRAINT bandeira_pk PRIMARY KEY ( ban_id );

ALTER TABLE carta ADD CONSTRAINT carta_pk PRIMARY KEY ( car_id );

ALTER TABLE cartao_credito ADD CONSTRAINT cartao_pk PRIMARY KEY ( crt_id );

ALTER TABLE categoria_carta ADD CONSTRAINT categoria_carta_pk PRIMARY KEY ( cat_id );

ALTER TABLE cidade ADD CONSTRAINT cidade_pk PRIMARY KEY ( cid_id );

ALTER TABLE endereco ADD CONSTRAINT endereco_pk PRIMARY KEY ( end_id );

ALTER TABLE estado ADD CONSTRAINT estado_pk PRIMARY KEY ( est_id );

ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ( itm_id );

ALTER TABLE item_pedido ADD CONSTRAINT item_pedido_pk PRIMARY KEY ( itp_id );

ALTER TABLE carrinho ADD CONSTRAINT carrinho_pk PRIMARY KEY (crr_id);

ALTER TABLE carrinho_item ADD CONSTRAINT carrinho_item_pk PRIMARY KEY (cri_id);

ALTER TABLE jogo ADD CONSTRAINT jogo_pk PRIMARY KEY ( jog_id );

ALTER TABLE pedido ADD CONSTRAINT pedido_pk PRIMARY KEY ( ped_id );

ALTER TABLE pessoa ADD CONSTRAINT pessoa_pk PRIMARY KEY ( pes_id );

ALTER TABLE status ADD CONSTRAINT status_pk PRIMARY KEY ( sts_id );

ALTER TABLE status_pedido ADD CONSTRAINT status_pedido_pk PRIMARY KEY ( spd_id );

ALTER TABLE status_transicao ADD CONSTRAINT status_transicao_pk PRIMARY KEY ( stt_id );

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( usu_id );

ALTER TABLE tipo_usuario ADD CONSTRAINT user_type_pk PRIMARY KEY ( tus_id );

ALTER TABLE telefone ADD CONSTRAINT telefone_pk PRIMARY KEY ( tel_id );

ALTER TABLE tipo_telefone ADD CONSTRAINT tipo_telefone_pk PRIMARY KEY ( ttl_id );

ALTER TABLE forma_pagamento ADD CONSTRAINT forma_pagamento_pk PRIMARY KEY ( fpa_id );

ALTER TABLE forma_pagamento_pedido ADD CONSTRAINT forma_pagamento_pedido_pk PRIMARY KEY ( fpp_id );

ALTER TABLE transicao ADD CONSTRAINT troca_pk PRIMARY KEY ( tsc_id );

ALTER TABLE item_transicao ADD CONSTRAINT item_troca_pk PRIMARY KEY ( itc_id );

ALTER TABLE cupom ADD CONSTRAINT cupom_pk PRIMARY KEY ( cup_id );

ALTER TABLE tipo_cupom ADD CONSTRAINT tipo_cupom_pk PRIMARY KEY ( tcp_id );

ALTER TABLE rastreio ADD CONSTRAINT rastreio_pk PRIMARY KEY ( rto_id );

ALTER TABLE tipo_transicao ADD CONSTRAINT tipo_transicao_pk PRIMARY KEY ( tit_id );

ALTER TABLE transacao_status_carta ADD CONSTRAINT transacao_status_carta_pk PRIMARY KEY (tsc_id);

ALTER TABLE grupo_precificacao ADD CONSTRAINT grupo_precificacao_pk PRIMARY KEY (gpr_id);

ALTER TABLE carta
    ADD CONSTRAINT carta_status_fk FOREIGN KEY ( car_status_id )
        REFERENCES status ( sts_id );

ALTER TABLE carta
    ADD CONSTRAINT carta_categoria_carta_fk FOREIGN KEY ( car_categoria_id )
        REFERENCES categoria_carta ( cat_id );

ALTER TABLE carta
    ADD CONSTRAINT carta_jogo_fk FOREIGN KEY ( car_jogo_id )
        REFERENCES jogo ( jog_id );

ALTER TABLE carta
    ADD CONSTRAINT carta_grupo_precificacao_fk FOREIGN KEY ( car_grupo_precificacao_id )
        REFERENCES grupo_precificacao ( gpr_id );

ALTER TABLE cartao_credito
    ADD CONSTRAINT cartao_bandeira_fk FOREIGN KEY ( crt_bandeira_id )
        REFERENCES bandeira ( ban_id );

ALTER TABLE cartao_credito
    ADD CONSTRAINT cartao_pessoa_fk FOREIGN KEY ( crt_pessoa_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE cartao_credito
    ADD CONSTRAINT cartao_credito_status_fk FOREIGN KEY ( crt_status_id )
        REFERENCES status ( sts_id );

ALTER TABLE cidade
    ADD CONSTRAINT cidade_estado_fk FOREIGN KEY ( cid_estado_id )
        REFERENCES estado ( est_id );

ALTER TABLE pessoa
    ADD CONSTRAINT pessoa_usuario_fk FOREIGN KEY ( pes_usuario_id )
        REFERENCES usuario ( usu_id );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_type_fk FOREIGN KEY ( usu_tipo_usuario_id )
        REFERENCES tipo_usuario ( tus_id );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_status_fk FOREIGN KEY ( usu_status_id )
        REFERENCES status ( sts_id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_cidade_fk FOREIGN KEY ( end_cidade_id )
        REFERENCES cidade ( cid_id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_status_fk FOREIGN KEY ( end_status_id )
        REFERENCES status ( sts_id );

ALTER TABLE endereco
    ADD CONSTRAINT endereco_pessoa_fk FOREIGN KEY ( end_pessoa_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE item
    ADD CONSTRAINT item_carta_fk FOREIGN KEY ( itm_carta_id )
        REFERENCES carta ( car_id );

ALTER TABLE item_pedido
    ADD CONSTRAINT item_pedido_item_fk FOREIGN KEY ( itp_item_id )
        REFERENCES item ( itm_id );

ALTER TABLE item_pedido
    ADD CONSTRAINT item_pedido_pedido_fk FOREIGN KEY ( itp_pedido_id )
        REFERENCES pedido ( ped_id );

ALTER TABLE carrinho
    ADD CONSTRAINT carrinho_pessoa_fk FOREIGN KEY ( crr_pessoa_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE carrinho_item
    ADD CONSTRAINT carrinho_item_carrinho_fk FOREIGN KEY ( cri_carrinho_id )
        REFERENCES carrinho ( crr_id );

ALTER TABLE carrinho_item
    ADD CONSTRAINT carrinho_item_item_fk FOREIGN KEY ( cri_item_id )
        REFERENCES item ( itm_id );

ALTER TABLE jogo_categoria_carta
    ADD CONSTRAINT jogo_categoria_carta_fk FOREIGN KEY ( jct_carta_id )
        REFERENCES categoria_carta ( cat_id );

ALTER TABLE jogo_categoria_carta
    ADD CONSTRAINT jogo_categoria_jogo_fk FOREIGN KEY ( jct_jogo_id )
        REFERENCES jogo ( jog_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_administrador_fk FOREIGN KEY ( ped_administrador_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_cliente_fk FOREIGN KEY ( ped_cliente_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_status_pedido_fk FOREIGN KEY ( ped_status_pedido_id )
        REFERENCES status_pedido ( spd_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_endereco_fk FOREIGN KEY ( ped_endereco_id )
        REFERENCES endereco ( end_id );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_rastreio_fk FOREIGN KEY ( ped_rastreio_id )
        REFERENCES rastreio ( rto_id );

ALTER TABLE forma_pagamento_pedido
    ADD CONSTRAINT pedido_forma_pagamento_fk FOREIGN KEY ( fpp_forma_pagamento_id )
        REFERENCES forma_pagamento ( fpa_id );

ALTER TABLE forma_pagamento_pedido
    ADD CONSTRAINT forma_forma_pagamento_fk FOREIGN KEY ( fpp_pedido_id )
        REFERENCES pedido ( ped_id );

ALTER TABLE forma_pagamento
    ADD CONSTRAINT forma_pagamento_cartao_credito_fk FOREIGN KEY ( fpa_cartao_credito_id )
        REFERENCES cartao_credito ( crt_id );

ALTER TABLE telefone
    ADD CONSTRAINT telefone_tipo_telefone_fk FOREIGN KEY ( tel_tipo_telefone_id )
        REFERENCES tipo_telefone ( ttl_id );

ALTER TABLE telefone
    ADD CONSTRAINT telefone_pessoa_fk FOREIGN KEY ( tel_pessoa_id )
        REFERENCES pessoa ( pes_id );

ALTER TABLE transicao
    ADD CONSTRAINT transicao_pedido_fk FOREIGN KEY ( tsc_pedido_id )
        REFERENCES pedido ( ped_id );

ALTER TABLE item_transicao
    ADD CONSTRAINT transicao_item_troca_fk FOREIGN KEY ( itc_transicao_id )
        REFERENCES transicao ( tsc_id );

ALTER TABLE item
    ADD CONSTRAINT item_status_fk FOREIGN KEY ( itm_status_id )
        REFERENCES status ( sts_id );

ALTER TABLE cupom
    ADD CONSTRAINT cupom_status_fk FOREIGN KEY ( cup_status_id )
        REFERENCES status ( sts_id );

ALTER TABLE cupom
    ADD CONSTRAINT cupom_tipo_cupom_fk FOREIGN KEY ( cup_tipo_cupom_id )
        REFERENCES tipo_cupom ( tcp_id );

ALTER TABLE transicao
    ADD CONSTRAINT transicao_cupom_fk FOREIGN KEY ( tsc_cupom_id )
        REFERENCES cupom ( cup_id );

ALTER TABLE transicao
    ADD CONSTRAINT transicao_rastreio_fk FOREIGN KEY ( tsc_rastreio_id )
        REFERENCES rastreio ( rto_id );

ALTER TABLE transicao
    ADD CONSTRAINT transicao_status_transicao_fk FOREIGN KEY ( tsc_status_trasicao_id )
        REFERENCES status_transicao ( stt_id );

ALTER TABLE transicao
    ADD CONSTRAINT transicao_tipo_transicao_fk FOREIGN KEY ( tsc_tipo_transicao_id )
        REFERENCES tipo_transicao ( tit_id );

ALTER TABLE transacao_status_carta
    ADD CONSTRAINT transacao_status_carta_status_fk FOREIGN KEY ( tsc_status_id )
        REFERENCES status ( sts_id );

ALTER TABLE transacao_status_carta
    ADD CONSTRAINT transacao_status_carta_carta_fk FOREIGN KEY ( tsc_carta_id )
        REFERENCES carta ( car_id );
insert into status_pedido (spd_status) values 
('PAGAMENTO PENDENTE DE APROVAÇÂO'),
('PAGAMENTO NEGADO'),
('PAGAMENTO PROCESSADO'),
('PEDIDO RECUSADO'),
('EM TRANSPORTE'), 
('ENTREGUE'), 
('TROCA PENDETENTE DE APROVAÇÂO'),
('TROCA RECUSADA'),
('AGUARDANDO RECEBIMENTO DO PRODUTO'),
('TROCA FINALIZADA'),
('DEVOLUÇÃO PENDETENTE DE APROVAÇÂO'),
('DEVOLUÇÃO RECUSADA'),
('DEVOLUÇÃO FINALIZADA')
;

insert into status (sts_status) values ('Ativo'), ('Inativo');

insert into status_transicao (stt_status_nome) values ('Em andamento'), ('Finalizado');
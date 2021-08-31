package br.com.zup.desafiotransacao.repository;

import br.com.zup.desafiotransacao.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransacaoRepository extends PagingAndSortingRepository<Transacao, String> {

    Page<Transacao> findByCartao_Id(String cartao_id, Pageable paginacao);

}

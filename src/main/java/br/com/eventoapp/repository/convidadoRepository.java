package br.com.eventoapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.eventoapp.models.Convidado;
import br.com.eventoapp.models.Evento;

public interface convidadoRepository extends CrudRepository<Convidado, String>{
	List<Convidado> findByEvento(Evento evento);
}

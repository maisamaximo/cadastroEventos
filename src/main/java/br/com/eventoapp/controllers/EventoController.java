package br.com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventoapp.models.Convidado;
import br.com.eventoapp.models.Evento;
import br.com.eventoapp.repository.EventoRepository;
import br.com.eventoapp.repository.convidadoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private convidadoRepository convidadoRepository;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(Evento evento) {
		
		eventoRepository.save(evento);
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping(value = "/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		mv.addObject("eventos", eventos);
		
		return mv;
	}
	
	@GetMapping(path = "/{codigo}")
	public ModelAndView detalharEvento(@PathVariable("codigo") long codigo) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalharEvento");
		mv.addObject("evento", evento);

		return mv;
	}
	
	@PostMapping(path = "/{codigo}")
	public String detalharEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);

		return "redirect:/{codigo}";
	}
//	
}

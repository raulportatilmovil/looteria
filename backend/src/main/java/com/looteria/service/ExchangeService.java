package com.looteria.service;

import com.looteria.entity.Exchange;
import com.looteria.entity.ListingPost;
import com.looteria.entity.User;
import com.looteria.repository.ExchangeRepository;
import com.looteria.repository.ListingPostRepository;
import com.looteria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Exchange createExchange(Long publicacionId, Long solicitanteId, String mensaje) {
        ListingPost listing = listingPostRepository.findById(publicacionId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        User solicitante = userRepository.findById(solicitanteId)
                .orElseThrow(() -> new RuntimeException("Usuario solicitante no encontrado"));

        // El solicitado es el vendedor de la publicación
        User solicitado = listing.getUsuario();

        Exchange exchange = new Exchange();
        exchange.setPublicacion(listing);
        exchange.setSolicitante(solicitante);
        exchange.setSolicitado(solicitado);
        exchange.setMensaje(mensaje);
        exchange.setEstado(Exchange.ExchangeStatus.PENDIENTE);
        exchange.setFechaCreacion(LocalDateTime.now());

        return exchangeRepository.save(exchange);
    }

    @Transactional(readOnly = true)
    public Optional<Exchange> getExchangeById(Long id) {
        return exchangeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Iterable<Exchange> getExchangesBySolicitante(Long solicitanteId) {
        return exchangeRepository.findBySolicitante_IdUsuario(solicitanteId);
    }

    @Transactional(readOnly = true)
    public Iterable<Exchange> getExchangesBySolicitado(Long solicitadoId) {
        return exchangeRepository.findBySolicitado_IdUsuario(solicitadoId);
    }

    @Transactional(readOnly = true)
    public Iterable<Exchange> getExchangesByPublicacion(Long publicacionId) {
        return exchangeRepository.findByPublicacion_IdPublicacion(publicacionId);
    }

    @Transactional
    public Exchange updateExchangeStatus(Long exchangeId, String newStatus) {
        Exchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Intercambio no encontrado"));

        exchange.setEstado(Exchange.ExchangeStatus.valueOf(newStatus));
        return exchangeRepository.save(exchange);
    }
}

package com.looteria.service;

import com.looteria.dto.DashboardStatsDTO;
import com.looteria.entity.ListingPost;
import com.looteria.entity.Transaction;
import com.looteria.repository.ListingPostRepository;
import com.looteria.repository.TransactionRepository;
import com.looteria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public DashboardStatsDTO getDashboardStats() {
        long totalUsuarios = userRepository.count();
        long usuariosVerificados = userRepository.countByVerificadoIdentidad(true);
        long publicacionesActivas = listingPostRepository.countByEstadoPublicacion(ListingPost.PublicationStatus.ACTIVA);
        long transaccionesCompletadas = transactionRepository.countByEstado(Transaction.TransactionStatus.COMPLETADA);

        return new DashboardStatsDTO(totalUsuarios, usuariosVerificados, publicacionesActivas, transaccionesCompletadas);
    }
}

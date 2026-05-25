package com.looteria.service;

import com.looteria.entity.PointsRedemption;
import com.looteria.entity.User;
import com.looteria.repository.PointsRedemptionRepository;
import com.looteria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PointsService {

    private static final Map<String, Integer> COSTES_CANJE = Map.of(
            "DESCUENTO_5", 500,
            "ENVIO_GRATIS", 1000
    );

    private static final Map<String, String> DESCRIPCION_CANJE = Map.of(
            "DESCUENTO_5", "Descuento 5€ en tu próxima compra",
            "ENVIO_GRATIS", "Envío gratis en tu próximo pedido"
    );

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PointsRedemptionRepository pointsRedemptionRepository;

    @Transactional
    public Map<String, Object> canjearPuntos(Long userId, String tipoCanje) {
        if (!COSTES_CANJE.containsKey(tipoCanje)) {
            throw new RuntimeException("Tipo de canje no válido: " + tipoCanje);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        int coste = COSTES_CANJE.get(tipoCanje);
        if (user.getPuntosAcumulados() < coste) {
            throw new RuntimeException("Puntos insuficientes. Necesitas " + coste + " puntos.");
        }

        String codigo = generarCodigo(tipoCanje);

        PointsRedemption canje = new PointsRedemption();
        canje.setUsuario(user);
        canje.setPuntosUsados(coste);
        canje.setTipoCanje(tipoCanje);
        canje.setCodigo(codigo);
        canje.setUsado(false);
        pointsRedemptionRepository.save(canje);

        user.setPuntosAcumulados(user.getPuntosAcumulados() - coste);
        userRepository.save(user);

        return Map.of(
                "codigo", codigo,
                "descripcion", DESCRIPCION_CANJE.get(tipoCanje),
                "puntosUsados", coste,
                "puntosRestantes", user.getPuntosAcumulados()
        );
    }

    @Transactional(readOnly = true)
    public List<PointsRedemption> getCanjesByUser(Long userId) {
        return pointsRedemptionRepository.findByUsuario_IdUsuario(userId);
    }

    private String generarCodigo(String tipoCanje) {
        String prefix = tipoCanje.equals("DESCUENTO_5") ? "DESC" : "SHIP";
        String unique = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        return prefix + "-" + unique;
    }
}

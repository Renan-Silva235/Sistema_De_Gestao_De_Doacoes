package com.colaborativos_gestao_sistema_api.DTOs;

import java.time.LocalDateTime;

public record RecentDonationDTO(
        LocalDateTime data,
        String doador,
        String item,
        Integer quantidade,
        String status
) {
}

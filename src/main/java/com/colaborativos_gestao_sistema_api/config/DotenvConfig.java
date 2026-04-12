package com.colaborativos_gestao_sistema_api.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig {
    public static void load() {
        Dotenv.configure()
                .ignoreIfMissing()
                .load();
    }
}
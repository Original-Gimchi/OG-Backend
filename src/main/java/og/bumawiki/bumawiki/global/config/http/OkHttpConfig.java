package og.bumawiki.bumawiki.global.config.http;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;

public class OkHttpConfig {
    @Bean("okHttpClient")
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}

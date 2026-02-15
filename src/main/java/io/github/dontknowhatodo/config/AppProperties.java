package io.github.dontknowhatodo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "application")
@Getter
public class AppProperties {
    private String profile;
    private final Security security = new Security();

    public Boolean isProduction() {
        return "production".equalsIgnoreCase(this.profile);
    }
    
    public static class Security {
        private final Jwt jwt = new Jwt();
        private final Cors cors = new Cors();
        private final Oauth2 oauth2 = new Oauth2();

        @Getter
        @Setter
        public static class Jwt {
            private String secret;
            private long expiration;
        }

        @Getter
        @Setter
        public static class Cors {
            private List<String> allowedOrigins = new ArrayList<>();
        }

        @Getter
        public static class Oauth2 {
            private List<String> authorizedRedirectUris = new ArrayList<>();

            public Oauth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
                this.authorizedRedirectUris = authorizedRedirectUris;
                return this;
            }        
        }

        public Jwt getJwt() { return jwt; }
        public Cors getCors() { return cors; }
        public Oauth2 getOauth2() { return oauth2; }
    }
}
package pl.bartlomiejstepien.drzewicacraftweb.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.bartlomiejstepien.drzewicacraftweb.config.security.AuthenticatedUser;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public final class JwtService {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();

    private static final Map<String, BlackListedJwt> BLACK_LISTED_JWTS = new HashMap<>();
    private static final ScheduledExecutorService BLACK_LISTED_JWTS_CLEARER_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    static {
        BLACK_LISTED_JWTS_CLEARER_EXECUTOR_SERVICE.scheduleAtFixedRate(JwtService::clearBlacklistedJwts, 1, 1, TimeUnit.HOURS);
    }
    private static void clearBlacklistedJwts()
    {
        for (final BlackListedJwt blackListedJwt : List.copyOf(BLACK_LISTED_JWTS.values()))
        {
            if (ZonedDateTime.now().isAfter(blackListedJwt.expirationTime()))
            {
                BLACK_LISTED_JWTS.remove(blackListedJwt.jwt());
            }
        }
    }

//    private final InvalidJwtTokenRepository invalidJwtTokenRepository;

    @Value("${drzewicacraft.security.jwt.issuer}")
    private String jwtIssuer;
    @Value("${drzewicacraft.security.jwt.expiration-time}")
    private Duration jwtExpirationTime;
    @Value("${drzewicacraft.security.jwt.longer-expiration-time}")
    private Duration jwtLongerExpirationTime;

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event)
    {
//        invalidJwtTokenRepository.findAll()
//                .stream()
//                .map(invalidJwtTokenEntity -> new BlackListedJwt(invalidJwtTokenEntity.getJwt(), invalidJwtTokenEntity.getExpirationDateTime()))
//                .forEach(blackListedJwt -> BLACK_LISTED_JWTS.put(blackListedJwt.jwt(), blackListedJwt));
    }

    public String createJwt(AuthenticatedUser authenticatedUser, boolean rememberMe) {
        ClaimsBuilder claimsBuilder = Jwts.claims()
                .subject(authenticatedUser.getUsername())
                .issuer(jwtIssuer)
                .notBefore(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(jwtExpirationTime)))
                .issuedAt(Date.from(Instant.now()));

        setExpirationTime(claimsBuilder, rememberMe);

        claimsBuilder.add("authorities", authenticatedUser.getAuthorities());

        return Jwts.builder()
                .signWith(KEY)
                .issuer(jwtIssuer)
                .compressWith(Jwts.ZIP.DEF)
                .issuedAt(Date.from(Instant.now()))
                .claims(claimsBuilder.build())
                .compact();
    }

    private void setExpirationTime(ClaimsBuilder claimsBuilder, boolean rememberMe)
    {
        if (rememberMe) {
            claimsBuilder.expiration(Date.from(Instant.now().plus(jwtLongerExpirationTime)));
        } else {
            claimsBuilder.expiration(Date.from(Instant.now().plus(jwtExpirationTime)));
        }
    }

    public Jws<Claims> validateJwt(String jwt) {
        if (BLACK_LISTED_JWTS.containsKey(jwt))
            throw new IllegalStateException();

        try {
            return Jwts.parser()
                    .verifyWith(KEY)
                    .requireIssuer(jwtIssuer)
                    .build()
                    .parseSignedClaims(jwt);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw exception;
        }
    }

    public String getJwtToken(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION_HEADER))
                .filter(header -> header.startsWith(BEARER))
                .map(header -> header.substring(BEARER.length()))
                .orElse(null);
    }

    public void invalidate(String jwt) {
        Jws<Claims> jws = validateJwt(jwt);
        ZonedDateTime expirationDateTime = jws.getPayload().getExpiration().toInstant().atZone(ZoneId.systemDefault());

        BLACK_LISTED_JWTS.put(jwt, new BlackListedJwt(jwt, expirationDateTime));

//        InvalidJwtTokenEntity invalidJwtTokenEntity = new InvalidJwtTokenEntity();
//        invalidJwtTokenEntity.setJwt(jwt);
//        invalidJwtTokenEntity.setInvalidatedDateTime(ZonedDateTime.now());
//        invalidJwtTokenEntity.setExpirationDateTime(expirationDateTime);

//        try
//        {
//            invalidJwtTokenRepository.save(invalidJwtTokenEntity);
//        }
//        catch (Exception exception)
//        {
//            log.error("Could not save invalid jwt '{}'", jwt, exception);
//        }
    }

    private record BlackListedJwt(String jwt, ZonedDateTime expirationTime)
    {
    }
}
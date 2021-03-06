package com.hy.spring.cloud.account.client;

import com.hy.spring.cloud.account.domain.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author as_hy
 */

@FeignClient(value = "spring-cloud-auth/auth",fallbackFactory = AuthServiceClientFallbackFactory.class)
public interface AuthServiceClient {
    @RequestMapping(value = "api/users/current", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User getUserBySession();
}

@Component
class AuthServiceClientFallbackFactory implements FallbackFactory<AuthServiceClient> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public AuthServiceClient create(Throwable throwable) {
        return new AuthServiceClient() {
            @Override
            public User getUserBySession() {
                logger.error("调用 getUserBySession 失败");
                return null;
            }
        };
    }
}

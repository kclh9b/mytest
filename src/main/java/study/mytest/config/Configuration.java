package study.mytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import study.mytest.dto.account.AccountDto;
import study.mytest.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Override
            public Optional getCurrentAuditor() {
                return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .map(r -> r.getRequest())
                        .map(r -> r.getSession(false))
                        .map(s -> (AccountDto) s.getAttribute("account"))
                        .map(a -> a.getAccountUserId());
//                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                        .getRequestAttributes()).getRequest();
//                HttpSession session = request.getSession(false);
//                return  Optional.ofNullable(session)
//                        .map(s -> (AccountDto) s.getAttribute("account"))
//                        .map(a -> a.getAccountUserId());
            }
        };
    }
}

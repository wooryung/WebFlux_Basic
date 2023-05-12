package com.wrsungwebflux;

import com.wrsungwebflux.service.UserService;
import com.wrsungwebflux.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class OnMethodsTest {

    @Autowired
    UserService userService;

//    @PostConstruct
    public void postConstruct() {
        // onErrorComplete -> 에러가 발생하면 cancel로 바뀌고 이후 stream은 실행 x. 바로 complete 신호로 바뀌고 log() 메서드도 동작하지 않음
        // onErrorComplete 메서드는 에러 발생 이후 동작을 붙여줘야 함. 에러 발생 전에 붙여주면 에러 처리 x
        log.info("-----onErrorComplete Start-----");
        Mono.just("Hello World")
                .log()
                .flatMap(data -> Mono.error(new RuntimeException("onErrorComplete test.")))
                .onErrorComplete()
                .doOnTerminate(() -> System.out.println("doOnTerminate()"))
                .doFinally(signalType -> System.out.println(signalType.toString()))
                .subscribe(System.out::println);

        // onErrorContinue
        log.info("-----onErrorContinue Start-----");
        Flux.just(3, 1, 2)
                .log()
                .flatMap(i -> userService.getUserById(i.longValue()))
                .onErrorContinue((throwable, o) -> log.error("Error occurred at: {} with {}", o, throwable.getLocalizedMessage()))
                .switchIfEmpty(Mono.error(new RuntimeException("onErrorContinue test.")))
                .subscribe(userVo -> log.info(userVo.toString()));

        // onErrorMap
        log.info("-----onErrorMap Start-----");
        Mono.just(3)
                .log()
                .onErrorMap(throwable -> {
                    log.error("Error occurred: {}", throwable.getLocalizedMessage());
                    return new RuntimeException("onErrorMap test.");
                })
                .flatMap(i -> userService.getUserById(i.longValue()))
                .subscribe(userVo -> log.info(userVo.toString()));

        // onErrorReturn
        log.info("-----onErrorReturn Start-----");
        Flux.just(1, 3, 2)
                .log()
                .flatMap(i -> userService.getUserById(i.longValue()))
                .onErrorReturn(new UserVo("wooryung", "wrsung", null, null, null, null, null, null))
                .subscribe(userVo -> log.info(userVo.toString()));

        // onErrorStop
        log.info("-----onErrorStop Start-----");
        Flux.just(1, 3, 2)
                .log()
                .flatMap(i -> userService.getUserById(i.longValue()))
                .onErrorStop()
                .onErrorContinue((throwable, o) -> log.error("Error occurred at: {} with {}", o, throwable.getLocalizedMessage()))
                .switchIfEmpty(Mono.error(new RuntimeException("onErrorContinue test.")))
                .subscribe(userVo -> log.info(userVo.toString()));
    }

}

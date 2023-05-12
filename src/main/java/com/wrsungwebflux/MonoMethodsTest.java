package com.wrsungwebflux;

import com.wrsungwebflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@Component
public class MonoMethodsTest {

    @Autowired
    UserService userService;

//    @PostConstruct
    public void postConstruct() throws InterruptedException {
        Mono<String> mono1 = Mono.just("Hello World").delayElement(Duration.ofSeconds(1)).log();
        Mono<String> mono2 = Mono.just("Hello World 2").delayElement(Duration.ofSeconds(2)).log();
        Mono<String> mono7 = Mono.just("Hello World 3").delayElement(Duration.ofSeconds(3)).log();
        Mono<String> mono8 = Mono.just("Hello World 4").delayElement(Duration.ofSeconds(4)).log();

        // and
//        System.out.println("-----and-----");
//        Mono<Void> mono3 = mono2.and(mono1).and(mono7).and(mono8).log();
//        mono3.subscribe(System.out::println);

        // as
//        System.out.println("-----as-----");
//        Mono<String> i = mono1.as(mono -> mono.doOnNext(System.out::println));
//        i.subscribe();
//        Flux<String> flux = mono1.as(Flux::from);
//        flux.log().subscribe(System.out::println);

        // block
//        System.out.println("-----block-----");
//        String result = mono2.block();
//        System.out.println(result);

        // blockOptional
//        System.out.println("-----blockOptional-----");
//        Mono<Object> mono4 = Mono.empty();
//        Object result = mono4.block();
//        System.out.println(result);
//        Optional<String> result2 = mono1.blockOptional();
//        Optional<Object> result3 = mono4.blockOptional();
//        System.out.println(result2.orElse("default"));
//        System.out.println(result3.orElse("default"));

        // cache
//        System.out.println("-----cache-----");
        Mono<Integer> mono5 = Mono.fromCallable(() -> {
                    System.out.println("Go!");
                    return 5;
                })
                .map(i -> {
                    System.out.println("Double!");
                    return i * 2;
                })
//                .delayElement(Duration.ofSeconds(1))
                .log();

//        Mono<Integer> cached = mono5.cache().log();
//        Flux<Integer> cached = Flux.just(1, 2, 3)
//                .map(i -> {
//                    System.out.println("Double!");
//                    return i * 2;
//                })
//                .cache();

//        System.out.println("<Using cached>");
//        System.out.println("1. " + cached.blockLast());
//        System.out.println("2. " + cached.blockLast());
//        System.out.println("3. " + cached.blockLast());

//        System.out.println("<NOT Using cached>");
//        System.out.println("1. " + mono5.block());
//        System.out.println("2. " + mono5.block());
//        System.out.println("3. " + mono5.block());

        // cacheInvalidateIf
//        System.out.println("-----cacheInvalidateIf-----");
//        Mono<Integer> invalidatedCache = mono5.cacheInvalidateIf(value -> value == 5)
//                .log();
//        System.out.println(invalidatedCache.block());
//        System.out.println(invalidatedCache.block());

        // cacheInvalidateWhen
//        System.out.println("-----cacheInvalidateWhen-----");
//        Mono<Integer> cachedMono = mono5
//                .cacheInvalidateWhen(
//                        val -> Mono.just(1).delayElement(Duration.ofSeconds(3)).then(),
//                        val -> System.out.println("cache invalidated: " + val)
//                )
//                .delayElement(Duration.ofSeconds(1))
//                .log();
//        System.out.println("1. " + cachedMono.block());
//        System.out.println("2. " + cachedMono.block());
//        System.out.println("3. " + cachedMono.block());
//        System.out.println("4. " + cachedMono.block());
//        System.out.println("5. " + cachedMono.block());
//        System.out.println("6. " + cachedMono.block());

        // cancelOn
//        System.out.println("-----cancelOn-----");
//        Mono<String> mono6 = Mono.fromCallable(() -> {
//                    Thread.sleep(4000);
//                    return "Hello";
//                })
//                .subscribeOn(Schedulers.single());
//
//        Mono<String> cancellableMono = mono6
//                .cancelOn(Schedulers.single()); // 취소 작업을 실행할 스레드 지정
//
//        cancellableMono.log().subscribe(System.out::println); // Hello 출력
//
//        Thread.sleep(2000); // 2000ms 후 작업 취소
//        cancellableMono.log().subscribe(System.out::println).dispose(); // cancel되고 Hello 출력 x


        // cast
        // 캐스팅될 타입은 원래 Mono 객체의 타입을 상속하거나 구현하는 타입이어야 한다. 그렇지 않으면 ClassCastException 발생
//        System.out.println("-----cast-----");
//        Mono<Object> objectMono = Mono.just(1);
//        Mono<Integer> integerMono = objectMono.cast(Integer.class);
//        objectMono.subscribe(System.out::println);
//        integerMono.subscribe(System.out::println);

        // checkpoint
        // 오류가 발생했을 때 디버깅을 도와주는 메서드 - 어떤 부분에서 오류가 발생했는지를 파악할 수 있다.
//        System.out.println("-----checkpoint-----");
//        Mono.just("hello")
//                .map(String::toUpperCase)
//                .map(s -> s.charAt(10)) // StringIndexOutOfBoundsException
//                .map(Character::getClass)
////                .checkpoint("")
//                .subscribe(System.out::println);

        // concatWith
        // 현재 Mono와 다른 Publisher를 연결하여 새로운 Flux를 생성. 병렬 진행 x
//        System.out.println("-----concatWith-----");
//        Flux.just("hello", 1)
//                .delayElements(Duration.ofSeconds(3))
//                .concatWith(Mono.just("world").delayElement(Duration.ofSeconds(3)))
//                .log()
//                .subscribe(System.out::println);

//        Mono.just("hello")
//                .delayElement(Duration.ofSeconds(3))
//                .concatWith(Flux.just("world", "everyone").delayElements(Duration.ofSeconds(3)))
//                .log()
//                .subscribe(System.out::println);

        // contextCapture - x
        // 현재 실행 중인 스레드의 context를 Mono에 적용
//        System.out.println("-----contextCapture-----");
//        ThreadLocal<String> context = new ThreadLocal<>();
//        context.set("value");
//
//        Mono.just(1)
//                .context
//        Mono<Object> monoCapturedContext = Mono.deferContextual(Mono::just);
////                .contextCapture();
//
//        monoCapturedContext.log().subscribe(System.out::println);

        // contextWrite
        // Mono의 context를 변환
//        System.out.println("-----contextWrite-----");
//        Mono<String> monoWithContext = Mono.just("Hello")
//                .contextWrite(Context.of("key", "value"))
//                .log();
//
//        monoWithContext.subscribe(System.out::println);

        // create
        // subscriber를 직접 관리하고 데이터를 동적으로 생성
//        System.out.println("-----create-----");
//        Mono<Object> mono9 = Mono.create(monoSink -> {
//                    String str = "Hello";
//                    monoSink.success(str); // Mono가 subscriber에게 데이터 전달
//                })
//                .log();
//
//        mono9.subscribe(System.out::println);
//        mono9.subscribe(System.out::println);
//        mono9.subscribe(System.out::println);

        // defaultIfEmpty
//        System.out.println("-----defaultIfEmpty-----");
//        Mono.empty()
//                .defaultIfEmpty("default")
//                .log()
//                .subscribe(System.out::println);

        // defer
        // 매번 새로운 Mono 인스턴스를 생성. Mono.just나 Mono.empty와 달리 Mono를 생성하기 위한 로직을 지연시킬 수 있다.
        // 지연 로딩 및 미리 정의된 값을 갖는 Mono를 생성하는 등의 상황에서 사용
//        System.out.println("-----defer-----");
//        Mono<String> deferredMono = Mono.defer(() -> Mono.just("Hello World"))
//                .log();
//        System.out.println(deferredMono.block());
//        deferredMono.subscribe(System.out::println);

        // deferContextual
        // 이전 연산자에게서 전달된 context를 유지하면서 새로운 Mono를 생성 -> context를 필요로 하는 연산자가 나중에 호출되는 경우 유용
//        System.out.println("-----deferContextual-----");
//        Mono<Integer> integerMono = Mono.just(1)
//                .flatMap(integer -> Mono.deferContextual(contextView -> Mono.just(integer + contextView.getOrDefault("offset", 0))))
//                .contextWrite(Context.of("offset", 10))
//                .log();
//
//        integerMono.subscribe(System.out::println);

        // delay
        // onNext signal을 duration만큼 지연시키고 0을 emit
//        System.out.println("-----delay-----");
//        Mono.delay(Duration.ofSeconds(3))
//                .log()
//                .subscribe(value -> System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName())); // default Scheduler는 parallel
//
//        Mono.delay(Duration.ofSeconds(4), Schedulers.single())
//                .log()
//                .subscribe(value -> System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName()));

        // delayElement
        // onNext signal을 duration만큼 지연시키고 element를 emit. Empty Mono나 error signal은 지연되지 않는다.
//        System.out.println("-----delayElement-----");
//        Mono.just(1)
//                .delayElement(Duration.ofSeconds(2))
//                .log()
//                .subscribe(value -> System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName())); // default Scheduler는 parallel

//        Mono.empty()
//                .delayElement(Duration.ofSeconds(2))
//                .log()
//                .subscribe(value -> System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName()));

//        Mono.just(1)
//                .delayElement(Duration.ofSeconds(2), Schedulers.single())
//                .log()
//                .subscribe(value -> System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName()));

        // delaySubscription
        // subscription을 duration만큼 지연시킨다.
//        System.out.println("-----delaySubscription-----");
//        Mono<Integer> mono11 = Mono.just(1)
//                .doOnNext(data -> log.info("doOnNext"))
//                .delayElement(Duration.ofSeconds(3))
//                .log();
//        mono11.subscribe(System.out::println);

//        Mono<Integer> subscriptionDelayedMono = Mono.just(2)
//                .doOnNext(data -> log.info("onNext"))
//                .delaySubscription(Duration.ofSeconds(3), Schedulers.single()) // default Scheduler는 parallel
//                .log();
//        subscriptionDelayedMono.subscribe(value -> System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName())); // delayElement와 차이점이 보이지 않음

//        Mono<Integer> subscriptionDelayedMono2 = Mono.just(3)
//                .delaySubscription(mono11) // 다른 Publisher가 value나 complete signal을 낼 때까지 delay
//                .log();
//        subscriptionDelayedMono2.subscribe(value -> System.out.println("value: " + value + ", thread: " + Thread.currentThread().getName()));

        // delayUntil
        // Publisher가 발행될 때까지 delay
//        System.out.println("-----delayUntil-----");
//        Mono.just("completed")
//                .delayUntil(s -> Mono.just("mono" + s).delayElement(Duration.ofSeconds(3)))
//                .log()
//                .subscribe(System.out::println);

        // dematerialize
        // Mono<Signal<T>> 타입을 Mono<T> 타입으로 변환
//        System.out.println("-----dematerialize-----");
//        Mono<Signal<Integer>> signalMono = Mono.just(Signal.next(1));
//        System.out.println("Before dematerialize: " + signalMono.block());
//
//        Mono<Integer> dematerializedMono = signalMono.dematerialize();
//        System.out.println("After dematerialize: " + dematerializedMono.block());

        // elapsed
        // Mono가 subscribe 되고 나서 첫 번째 next signal까지 걸린 시간(ms)과 data를 Tuple<Long, T> 형태로 emit
//        System.out.println("-----elapsed-----");
//        Mono.just("elapsed mono")
//                .delayElement(Duration.ofSeconds(2))
//                .elapsed(Schedulers.single()) // Parameter Scheduler는 파악 못함
//                .log()
////                .subscribe(System.out::println);
//                .subscribe(objects -> System.out.println("Elapsed time: " + objects.get(0) + ", data: " + objects.get(1) + ", thread: " + Thread.currentThread().getName())); // default Scheduler는 parallel

        // empty
        // 아무것도 emit 하지 않고 complete 되는 mono를 발행 -> completed Mono
//        System.out.println("-----empty-----");
//        Mono.empty()
//                .log()
//                .subscribe(System.out::println);

        // error
        // subscribe 후 바로 error와 함께 terminate 되는 mono를 발행 -> failed Mono
//        System.out.println("-----error-----");
//        Mono.error(new RuntimeException())
//                .log()
//                .subscribe();

        // expand
        // 주어진 함수를 사용하여 Mono 시퀀스를 재귀적으로 확장. capacityHint는 파악 못함
//        System.out.println("-----expand-----");
//        Mono.just(0)
//                .expand(integer -> (integer < 4) ? Mono.just(integer + 1) : Mono.empty())
//                .subscribe(System.out::println);

//        Mono.just("A")
//                .expand(s -> {
//                    switch (s) {
//                        case "A":
//                            return Flux.just("AA", "AB", "a1");
//                        case "AA":
//                            return Mono.just("aa1");
//                        case "AB":
//                            return Mono.just("ab1");
//                        default:
//                            return Mono.empty();
//                    }
//                })
//                .subscribe(System.out::println);

        // expandDeep
//        System.out.println("-----expandDeep-----");
//        Mono.just("A")
//                .expandDeep(s -> {
//                    switch (s) {
//                        case "A":
//                            return Flux.just("AA", "AB", "a1");
//                        case "AA":
//                            return Mono.just("aa1");
//                        case "AB":
//                            return Mono.just("ab1");
//                        default:
//                            return Mono.empty();
//                    }
//                })
//                .subscribe(System.out::println);

        // filter
        // Predicate가 true를 반환하는 값만 발행
//        System.out.println("-----filter-----");
//        Flux.just(1, 5, 3, 4, 2)
//                .filter(integer -> integer < 4)
//                .log()
//                .subscribe(System.out::println);

        // filterWhen
        // asyncPredicate 함수가 반환하는 Mono의 결과에 따라 Mono를 필터링 -> Mono<Boolean>이 true를 방출하면 해당 Mono의 원래 값이 유지되고, false를 방출하면 해당 Mono의 값은 필터링된다.
//        System.out.println("-----filterWhen-----");
//        Mono.just(1)
//                .filterWhen(integer -> Mono.just(integer < 0))
//                .subscribe(System.out::println);

//        Flux.just(1, 5, 3, 4, 2)
//                .filterWhen(integer -> Mono.just(integer >= 4))
//                .subscribe(System.out::println);

        // first
        // deprecated
        // 주어진 두 개 이상의 Mono 중 먼저 발행된 값 또는 에러를 발행

        // firstWithSignal
        // 주어진 두 개 이상의 Mono 중 먼저 signal을 방출하는 Mono의 값 또는 empty, 에러를 발행
//        System.out.println("-----firstWithSignal-----");
//        Mono.firstWithSignal(Mono.just(1).delayElement(Duration.ofSeconds(1)).log(), Mono.just(2).log())
//                .log()
//                .subscribe(System.out::println);

        // firstWithValue
        // 주어진 두 개 이상의 Mono 중 먼저 값을 방출하는 Mono의 값을 발행. empty나 error가 먼저 방출되더라도 값이 있는 Mono의 값을 발행한다.
//        System.out.println("-----firstWithValue-----");
//        Mono.firstWithValue(Mono.just("hello").delayElement(Duration.ofSeconds(1)).log(), Mono.empty().log())
//                .log()
//                .subscribe(System.out::println);

        // flatMap
        // Mono를 다른 Mono로 변환
//        System.out.println("-----flatMap-----");
//        Mono.just(1)
//                .flatMap(integer -> Mono.just(integer * 2))
//                .log()
//                .subscribe(System.out::println);

        // flatMapIterable
        // Mono를 Iterable로 변환하여 Flux로 반환
//        System.out.println("-----flatMapIterable-----");
//        Mono.just("hello")
//                .flatMapIterable(s -> Arrays.asList(s.split(""))) // Iterable로 변환하고, Iterable을 순회하면서 값을 방출
//                .log()
//                .subscribe(System.out::println);

        // flatMapMany
        // Mono의 값을 이용하여 Flux로 반환
//        System.out.println("-----flatMapMany-----");
//        Mono.just(10)
//                .flatMapMany(integer -> Flux.just(integer / 2, integer / 5))
//                .log()
//                .subscribe(System.out::println);

        // flux
        // Mono를 Flux로 변환
//        System.out.println("-----flux-----");
//        Mono.just(2)
//                .flux()
//                .log()
//                .subscribe(System.out::println);

        // from
        // Publisher를 Mono로 변환하고 첫 번째 방출되는 값만 발행
//        System.out.println("-----from-----");
//        Flux<Integer> numbers = Flux.just(1, 2, 3);
//        Mono.from(numbers)
//                .subscribe(System.out::println);

        // fromCallable
        // 주어진 Callable로 생성된 값을 발행하는 Mono를 생성한다. Callable이 null을 반환하면 Mono는 empty로 complete된다.
//        System.out.println("-----fromCallable-----");
//        Mono.fromCallable(() -> "null")
//                .log()
//                .subscribe(System.out::println);

        // fromCompletionStage
        // 주어진 CompletionStage를 이용하는 값을 발행하는 Mono를 생성한다.
        // CompletionStage interface를 CompletableFuture가 implement
//        System.out.println("-----fromCompletionStage-----");
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
//        Mono.fromCompletionStage(completableFuture)
//                .subscribe(System.out::println);

        // fromDirect
        // Publisher를 cardinility check 없이 Mono로 변환
//        System.out.println("-----fromDirect-----");
//        Flux<Integer> numbers = Flux.just(1, 2, 3).doOnDiscard(Integer.class, System.out::println).log();
//        Mono.from(numbers)
//                .log()
//                .subscribe(System.out::println);
//
//        Mono.fromDirect(numbers)
//                .log()
//                .subscribe(System.out::println);

        // handle
        // Mono의 결과에 대해 새로운 값을 계산하거나 예외 처리를 수행할 때 사용
        // SynchronousSink: next(), error(), complete() 메서드를 호출해서 값을 생성하거나 오류 혹은 완료를 전달
//        System.out.println("-----handle-----");
//        Flux.just(1, 3, 5)
//                .handle((integer, synchronousSink) -> {
//                    if (integer != 5)
//                        synchronousSink.next(integer);
//                    else
//                        synchronousSink.complete();
//                })
//                .log()
//                .subscribe(System.out::println);

        // hasElement
        // Mono가 element를 가지고 있는지 Mono<Boolean> 타입으로 반환
//        System.out.println("-----hasElement-----");
//        Mono.empty()
//                .hasElement()
//                .subscribe(System.out::println);
//
//        Mono.just(1)
//                .hasElement()
//                .subscribe(System.out::println);

        // hide
        // Mono의 identity를 숨긴다. identity-based optimization을 방지하기 위함 -> source Mono가 수정되지 않도록 보호하기 위한 메서드인 것 같다.
//        System.out.println("-----hide-----");
//        Mono<Integer> originalMono = Mono.just(1);
//        Mono<Integer> hiddenMono = originalMono.hide();
//
//        // originalMono를 직접적으로 참조하지 않고 숨길 수 있다.
//        Mono<String> resultMono = hiddenMono.map(integer -> "The number is " + integer).log();
//        resultMono.subscribe(System.out::println);

        // ignoreElement
        // onNext signal을 무시하고 종료한다. source element를 discard -> empty Mono를 반환
//        System.out.println("-----ignoreElement-----");
//        Mono.just(1)
//                .ignoreElement()
//                .doOnDiscard(Integer.class, i -> System.out.println("discarded value: " + i))
//                .log()
//                .subscribe(System.out::println);

        // ignoreElements
        // source Publisher의 결과를 무시하고 source가 완료될 때까지 대기 후 complete signal을 내보낸다.
//        System.out.println("-----ignoreElements-----");
//        Flux<Integer> discardedFlux = Flux.range(1, 5).log();
//        Mono.ignoreElements(discardedFlux)
//                .doOnDiscard(Integer.class, integer -> System.out.println("discarded value: " + integer))
//                .log()
//                .subscribe(System.out::println);

        // just
        // 지정된 값을 발행하는 Mono를 생성

        // justOrEmpty
        // Parameters: Optional<T> data / T data
        // Optional이 비어있거나 data가 null이면 empty Mono를 반환하고, 값이 있으면 그 값을 반환
//        System.out.println("-----justOrEmpty-----");
////        Optional<String> optional = Optional.of("Hello World");
//        Optional<String> optional = Optional.empty();
//        Mono.justOrEmpty(optional)
//                .log()
//                .subscribe(System.out::println);

        // log
        // Mono의 동작을 로깅 -> Mono가 실행되는 순서와 결과값, 에러 등을 확인 가능
        // String category: default는 "reactor.Mono" + operator suffix. ex) "reactor.Mono.Map". "reactor."과 같이 .으로 끝나도록 지정하면 operator suffix를 붙여서 완성해준다.
        // Level level: default는 INFO. FINEST, FINE, INFO, WARNING, SEVERE
        // SignalType options: ex) SignalType.ON_NEXT, SignalType.ON_ERROR
        // boolean showOperatorLine:
        // Logger logger:
//        System.out.println("-----log-----");
//        Mono.just("Hello World")
//                .log("category.", Level.WARNING, true)
//                .subscribe(System.out::println);

        // map
        // Mono가 방출한 값을 동기적 함수를 이용하여 다른 형태로 변환 (T 타입 -> R 타입)
//        System.out.println("-----map-----");
//        Mono.just("hello")
//                .delayElement(Duration.ofSeconds(2))
//                .map(String::length)
//                .log()
//                .subscribe(System.out::println);

        // mapNotNull
        // mapper 함수가 반환하는 값이 null이면 complete로 종료하고, null이 아니면 그 값을 반환
//        System.out.println("-----mapNotNull-----");
//        Mono.just("hello")
//                .mapNotNull(s -> s.equals("hello") ? null : s.toUpperCase())
//                .log()
//                .subscribe(System.out::println);
//
//        Flux.just(1, 2, 3)
//                .mapNotNull(integer -> integer == 2 ? null : integer)
//                .log()
//                .subscribe(System.out::println);

        // materialize
        // Mono<T> 타입을 Mono<Signal<T>> 타입으로 변환
//        System.out.println("-----materialize-----");
//        Mono<Integer> mono12 = Mono.just(1);
////        Mono<Object> mono12 = Mono.empty();
//        System.out.println("Before materialize: " + mono12.block());
//
//        Mono<Signal<Integer>> materializedMono = mono12.materialize();
//        System.out.println("After materialize: " + materializedMono.block());

        // mergeWith
        // Mono가 방출한 값과 Publisher의 값을 합쳐서 하나의 Flux로 반환
//        System.out.println("-----mergeWith-----");
//        Flux<Integer> mergedFlux = Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1));
//        Mono.just(4)
//                .delayElement(Duration.ofSeconds(2))
//                .mergeWith(mergedFlux)
//                .log()
//                .subscribe(System.out::println);

        // metrics - deprecated
        // Mono의 성능 지표를 수집 - Mono의 생성 시간, 총 수행 시간, 에러 발생 횟수 등을 추적 가능 -> 지속적인 성능 모니터링 및 튜닝에 유용하게 사용

        // name
        // Mono에서 생성된 Metrics를 위한 이름을 지정할 수 있도록 해준다.
//        System.out.println("-----name-----");
//        Mono.just("hello")
//                .name("example.mono")
//                .metrics()
//                .log()
//                .subscribe(System.out::println);

        // never
        // Returns: a never completing Mono
//        System.out.println("-----never-----");
//        Mono.never()
//                .log()
//                .subscribe(
//                        value -> System.out.println("Value received: " + value),
//                        error -> System.err.println("Error occurred: " + error),
//                        () -> System.out.println("Completed!")
//                );
//
//        System.out.println("This line will be printed immediately.");

    }

}

package com.wrsungwebflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@Component
public class FluxMethodsTest {

    @PostConstruct
    public void postConstruct() throws InterruptedException {
        // zipWithIterable
        // Flux의 elements와 Iterable의 값들을 하나씩 짝지어서 Tuple2 형태의 Flux로 반환
//        System.out.println("-----zipWithIterable-----");
//        List<Integer> list = Arrays.asList(1, 2);
//
//        // zipWithIterable(Iterable<? extends T2> iterable)
//        Flux.just("A", "B", "C")
//                .zipWithIterable(list)
//                .log()
//                .subscribe(System.out::println);
//
//        // zipWithIterable(Iterable<? extends T2> iterable, BiFunction<? super T,? super T2,? extends V> zipper)
//        Flux.just("A", "B", "C")
//                .zipWithIterable(list, (c, integer) -> c + ":" + integer)
//                .log()
//                .subscribe(System.out::println);

        // zipWith
        // Flux와 Publisher의 값들을 하나씩 짝지어서 Tuple2 형태의 Flux로 반환
        // prefetch: the request size to use for this Flux and the other Publisher. 내부 버퍼의 크기 (?)
//        System.out.println("-----zipWith-----");
//        Flux<String> stringFlux = Flux.just("A", "B", "C", "D", "E");
//
//        // zipWith(Publisher<? extends T2> source2, int prefetch, BiFunction<? super T,? super T2,? extends V> combinator)
//        Flux.range(1, 4)
//                .zipWith(stringFlux, 2, (a, b) -> a + ":" + b)
//                .log()
//                .subscribe(System.out::println);

        // withLatestFrom
        // Flux의 값들과 Publisher에서 가장 최근에 방출된 값을 BiFunction을 이용하여 Flux로 발행 -> Publisher가 모두 발행될 때까지 기다린다.
//        System.out.println("-----withLatestFrom-----");
//        Flux<String> other = Flux.just("A", "B", "C", "D").log();
//        Flux<Object> other2 = Flux.empty().log();
//
//        // withLatestFrom(Publisher<? extends U> other, BiFunction<? super T,? super U,? extends R> resultSelector)
////        Flux.range(1, 3)
////                .withLatestFrom(other, (a, b) -> a + b)
////                .log()
////                .subscribe(System.out::println);
//
//        // other이 empty인 경우 -> Publisher complete 후 Flux complete
//        Flux.range(1, 3)
//                .withLatestFrom(other2, (a, b) -> a + b.toString())
//                .log()
//                .subscribe(System.out::println);
//
//        // Flux가 empty인 경우 -> Publisher는 발행되지만 Flux에서 complete로 종료
//        Flux.empty()
//                .withLatestFrom(other, (a, b) -> a + b)
//                .log()
//                .subscribe(System.out::println);

        // windowWhile
        // Flux를 multiple Flux windows로 분할 - predicate에 맞으면 window를 열어두고, false를 반환하면 window를 닫고 새 window를 열며 그 element는 discard된다.
        // seperator(predicate가 false를 반환하는 값)와 함께 empty window가 발생할 수 있다. but seperator와 함께 끝나는 경우에는 empty window 발생 x
//        System.out.println("-----windowWhile-----");
//
//        // windowWhile(Predicate<T> inclusionPredicate)
//        // windowWhile(Predicate<T> inclusionPredicate, int prefetch)
//        Flux.range(1, 11)
//                .windowWhile(integer -> integer % 2 == 0, 2)
//                .doOnDiscard(Integer.class, val -> System.out.println("discarded: " + val))
//                .log()
//                .subscribe(window -> window.collectList().subscribe(System.out::println));

        // windowWhen
        // bucketOpening: 이 Publisher가 값을 emit할 때마다 window open signal을 보낸다.
        // closeSelector: opening signal을 받아서 Publisher를 반환. Publisher가 값을 emit할 때 window close signal을 보낸다.
        // open signal과 close signal에 따라 window 생성
//        System.out.println("-----windowWhen-----");
//
//        // windowWhen(Publisher<U> bucketOpening, Function<? super U,? extends Publisher<V>> closeSelector)
//        Flux.interval(Duration.ofMillis(100)) // 0.1초마다 숫자 생성
//                .windowWhen(
//                        Flux.interval(Duration.ofMillis(200)).take(5), // 0.2초마다 bucket을 열고 총 5개의 데이터만을 받도록 제한
//                        integer -> {
//                            System.out.println("Closing bucket with key " + integer);
//                            return Mono.just(-1).delayElement(Duration.ofMillis(500));
//                        }
//                )
//                .flatMap(Flux::collectList) // 각각의 Flux에서 발생한 데이터를 리스트로 묶음
//                .log()
//                .subscribe(System.out::println);

        // windowUntilChanged
        // Flux가 발행하는 값이 같은 동안 window를 유지한다. -> 연속된 같은 값들을 windowFlux로 발행
//        System.out.println("-----windowUntilChanged-----");
//
//        // windowUntilChanged()
//        Flux.just(1, 1, 2, 3, 3, 3, 2)
//                .windowUntilChanged()
//                .flatMap(Flux::collectList)
//                .subscribe(System.out::println);
//
//        // windowUntilChanged(Function<? super T,? extends V> keySelector)
////        Flux.just(3, 6, 5, 2, 1, 4, 3, 2)
////                .windowUntilChanged(integer -> integer % 3)
////                .flatMap(Flux::collectList)
////                .subscribe(System.out::println);
//
//        // windowUntilChanged(Function<? super T,? extends V> keySelector, BiPredicate<? super V,? super V> keyComparator)
////        Flux.just("aa", "bb", "ccc", "d", "ee")
////                .windowUntilChanged(String::length, ((integer, integer2) -> integer < 3))
////                .flatMap(Flux::collectList)
////                .subscribe(System.out::println);

// --------------------------------------------------------------------------------------------
        // windowTimeout(int maxSize, Duration maxTime, boolean fairBackpressure)
        // 각 windowFlux는 maxSize 또는 maxTime에 도달하면 닫히고 새로운 windowFlux가 열린다.
        // fairBackpressure를 true로 설정하면 다른 프로듀서의 스트림과 공정하게 배압신호를 보내기 때문에 백프레셔가 발생했을 때 스트림을 방출하는 속도가 일관적으로 유지될 수 있다. false로 설정하면 최대한 빠르게 백프레셔 신호를 보내므로 스트림 방출 속도가 불안정할 수 있다.
//        System.out.println("-----windowTimeout-----");
//        Flux<Flux<Integer>> windowedFlux = Flux.range(1, 10)
//                .windowTimeout(4, Duration.ofMillis(1), false);
//
//        windowedFlux.subscribe(integerFlux -> integerFlux.collectList().subscribe(System.out::println));
//
//        try {
//            Thread.sleep(3);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        windowedFlux.subscribe(integerFlux -> integerFlux.collectList().subscribe(System.out::println));

        // toStream - toStream(), toStream(int batchSize)
        // Flux를 Stream으로 변환 -> 각각의 onNext call을 blocking
        // 반환된 stream은 최종 연산을 수행하기 전까지는 어떤 작업도 수행하지 않는다.
        // batchSize: the bounded capacity to prefetch
//        System.out.println("-----toStream-----");
//        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
//        Stream<Integer> stream = flux.toStream();
//        stream.forEach(System.out::println);

        // toIterable
        // Flux를 Iterable로 변환 -> Iterator.next() call을 blocking
//        System.out.println("-----toIterable-----");
//        // toIterable(int batchSize, Supplier<Queue<T>> queueProvider)
//        // queueProvider: Flux 시퀀스의 요소를 버퍼링할 Queue 객체를 제공하는 함수
//        Flux<Integer> flux2 = Flux.just(1, 2, 3, 4, 5);
//        Iterable<Integer> iterable = flux2.toIterable(3, ConcurrentLinkedQueue::new);
//        System.out.println("next: " + iterable.iterator().next());
//        iterable.forEach(System.out::println);

        // timeout(Publisher<U> firstTimeout, Function<? super T,? extends Publisher<V>> nextTimeoutFactory, Publisher<? extends T> fallback)
        // firstTimeout Publisher가 emit하기 전에 Flux의 첫 번째 요소가 emit되지 않으면 fallback Publisher로 대체된다.
        // 첫 번째 이후의 요소들은 nextTimeoutFactory로 반환된 Publisher를 timeout으로 적용
        // firstTimeout: 첫 번째 timeout을 위한 Publisher
        // nextTimeoutFactory: Flux가 emit한 요소를 인자로 받아서 다음 timeout을 위한 Publisher를 반환
        // fallback: timeout이 발생했을 때 subscribe할 fallback Publisher
//        System.out.println("-----timeout-----");
//        Flux.range(1, 10)
//                .delayElements(Duration.ofSeconds(2))
//                .timeout(
//                        Mono.just("timeout").delayElement(Duration.ofSeconds(3)).log(),
//                        integer -> Mono.just("timeout2").delayElement(Duration.ofSeconds(2)).log(),
//                        Mono.just(-1)
//                )
//                .log()
//                .subscribe(System.out::println);

        // takeWhile
        // predicate가 true를 반환하는 동안만 Flux의 요소를 연속해서 발행하고 false를 반환하면 terminate 된다.
//        System.out.println("-----takeWhile-----");
//        Flux.range(1, 5)
//                .takeWhile(integer -> integer != 4)
//                .log()
//                .subscribe(System.out::println);

        // takeUntil
        // predicate가 true를 반환할 때까지 Flux의 요소를 연속해서 발행.
//        System.out.println("-----takeUntil-----");
//        Flux.range(1, 5)
//                .takeUntil(integer -> integer == 4)
//                .log()
//                .subscribe(System.out::println);

        // takeLast
        // completion되기 전에 방출된 n개의 요소들을 emit
//        System.out.println("-----takeLast-----");
//        Flux.range(1, 10)
//                .takeLast(3)
//                .log()
//                .subscribe(System.out::println);

        // take(long n, boolean limitRequest)
        // 처음 n개의 요소들을 emit
        // limitRequest: backpressure와 관련. true - 취한 항목 수가 요청된 버퍼 크기보다 크면 소스에 추가 요청을 보내지 않음. false - 추가 요청을 보냄
//        System.out.println("-----take-----");
//        Flux.range(1, 10)
//                .take(3)
//                .log()
//                .subscribe(System.out::println);

        // switchOnNext
        // Publisher<Publisher<T>> 타입을 Flux<T> 타입으로 변환
        // 두 번째 Flux가 subscribe 될 때 첫 번째 Flux는 cancel된다.
//        System.out.println("-----switchOnNext-----");
//        Flux<Flux<Integer>> fluxFlux = Flux.just(
//                Flux.range(1, 3).delayElements(Duration.ofSeconds(1)).log(),
//                Flux.range(4, 3).log()
//        );
//        Flux.switchOnNext(fluxFlux)
//                .log()
//                .subscribe(System.out::println);

        // switchOnFirst
        // Flux의 첫 번째 요소를 받아서 그 요소를 이용하여 filtering
//        System.out.println("-----switchOnFirst-----");
//        Flux.just("hello", 1, 2, 3, "world", 4).log()
//                .switchOnFirst(((signal, flux) -> {
//                    if (signal.hasValue()) {
//                        Class<?> type = Objects.requireNonNull(signal.get()).getClass();
////                        return flux.filter(serializable -> !serializable.getClass().equals(type));
//                        return Flux.just("a", "b").log();
//                    }
//                    return flux;
//                }))
//                .subscribe(System.out::println);

        // switchMap

// ---------------------------------------------------------------------------------------
        // startWith
        // Flux sequence 전에 주어진 values나 publisher, iterable을 붙인다. 같은 타입의 값만 가능, 병렬 x
//        System.out.println("-----startWith-----");
////        Flux.range(1, 5)
////                .startWith(6, 7)
////                .log()
////                .subscribe(System.out::println);
//
//        Flux<Integer> publisher = Flux.just(6, 7).delayElements(Duration.ofSeconds(1));
//        Flux.range(1, 5)
//                .startWith(publisher)
//                .log()
//                .subscribe(System.out::println);

        // sort
        // sequence가 complete된 후 정렬된 sequence를 emit. Flux의 요소들은 Comparable을 implement한 타입이어야 한다.
//        System.out.println("-----sort-----");
//        Flux.just(7, 3, 5, 2, 67, 1)
//                .delayElements(Duration.ofSeconds(1))
//                .sort()
//                .log()
//                .subscribe(System.out::println);

        // skipWhile
        // predicate가 false를 반환하기 전까지 skip. discard support
//        System.out.println("-----skipWhile-----");
//        Flux.just(5, 3, 4, 8, 2, 1, 7)
//                .skipWhile(integer -> integer > 2)
//                .doOnDiscard(Integer.class, integer -> System.out.println("discarded: " + integer))
//                .log()
//                .subscribe(System.out::println);

        // skipUntilOther
        // parameter로 주어진 publisher가 onNext나 onComplete signal을 낼 때까지 Flux의 값을 skip. discard support
//        System.out.println("-----skipUntilOther-----");
//        Flux<Integer> source = Flux.range(1, 10)
//                .delayElements(Duration.ofSeconds(1))
//                .log();
//
////        Mono<String> other = Mono.just("one")
////                .delayElement(Duration.ofSeconds(5))
////                .log();
//
//        Flux<String> other = Flux.just("one", "two")
//                .delayElements(Duration.ofSeconds(3))
//                .log();
//
//        source.skipUntilOther(other)
//                .log()
//                .subscribe(System.out::println);

        // skipUntil
        // predicate가 true를 return할 때까지 skip. discard support
//        System.out.println("-----skipUntil-----");
//        Flux.just(1, 4, 56, 2, 3, 4, 7, 23)
//                .skipUntil(integer -> integer >= 56)
//                .log()
//                .subscribe(System.out::println);

        // skipLast
        // 뒤에서부터 n개의 요소들을 skip. discard support
//        System.out.println("-----skipLast-----");
//        Flux.range(1, 10)
//                .delayElements(Duration.ofSeconds(1))
//                .skipLast(3)
//                .log()
//                .subscribe(System.out::println);

        // skip
        // skip(long skipped): 앞에서부터 n개의 요소들을 skip
        // skip(Duration timespan): duration 동안 emit된 요소들을 skip
        // skip(Duration timespan, Scheduler timer): duration 동안 emit된 요소들을 skip, 주어진 scheduler로 측정
        // discard support
//        System.out.println("-----skip-----");
//        Flux.range(1, 10)
//                .delayElements(Duration.ofSeconds(1))
//                .skip(Duration.ofSeconds(5))
//                .log()
//                .subscribe(System.out::println);

        // singleOrEmpty
        // single item, no item, IndexOutOfBoundsException - Mono로 반환
//        System.out.println("-----singleOrEmpty-----");
//        Flux.just(1, 2)
//                .singleOrEmpty()
//                .log()
//                .subscribe(System.out::println);
//
////        Flux.just(1)
////                .singleOrEmpty()
////                .log()
////                .subscribe(System.out::println);
//
////        Flux.empty()
////                .singleOrEmpty()
////                .log()
////                .subscribe(System.out::println);

        // single
        // single(): 값이 하나일 때만 emit. 2개 이상이면 IndexOutOfBoundsException, 0개이면 NoSuchElementException
        // single(T defaultValue): empty일 때 defaultValue로 emit
//        System.out.println("-----single-----");
//        Flux.empty()
//                .single(1)
//                .log()
//                .subscribe(System.out::println);

        // shareNext
        // Flux의 첫 번째 값을 여러 구독자가 공유하도록 하는 Mono를 생성
//        System.out.println("-----shareNext-----");
//        Mono<Integer> shared = Flux.range(1, 5)
//                .delayElements(Duration.ofSeconds(3))
//                .log()
//                .shareNext()
//                .log();
//
//        shared.subscribe(i -> System.out.println("Subscriber 1: " + i));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        shared.subscribe(i -> System.out.println("Subscriber 2: " + i));

        // scanWith(Supplier<A> initial, BiFunction<A,? super T,A> accumulator)
        // 이전까지 처리한 요소들의 결과와 현재 요소를 이용하여 새로운 결과를 반환 -> 값을 축적
        // initial: 연산의 초기 상태를 제공하는 supplier
        // accumulator: 연산을 수행하는 BiFunction. 이전까지 처리한 요소들의 결과와 현재 처리 중인 요소를 받아 새로운 결과를 반환
//        System.out.println("-----scanWith-----");
//        Flux.range(1, 5)
//                .scanWith(() -> 0, (Integer::sum)) // 0 + 1 -> 1 + 2 -> 3 + 3 -> 6 + 4 -> 10 + 5
//                .log()
//                .subscribe(System.out::println);

        // scan(A initial, BiFunction<A,? super T,A> accumulator)
        // initial 값이 없으면 첫 번째 값을 initial로 사용
        // scan은 고정된 초기값과 함께 누적 계산을 수행하지만, scanWith는 각 구독마다 다르게 설정된 초기값으로 누적 계산 수행 가능
//        System.out.println("-----scan-----");
//        Flux.range(1, 5)
//                .scan((Integer::sum))
//                .log()
//                .subscribe(System.out::println);

        // sampleTimeout
        // 일정 시간마다 최근 값들 중 가장 마지막 값을 발행. discard support
//        System.out.println("-----sampleTimeout-----");
//        Flux.interval(Duration.ofMillis(500))
//                .sampleTimeout(integer -> Mono.just(1).delayElement(Duration.ofSeconds(2)).log())
//                .log()
//                .subscribe(System.out::println);

        // sampleFirst(Duration timespan): 일정 시간 간격으로 방출된 값 중 첫 번째 값을 발행
        // sampleFirst(Function<? super T,? extends Publisher<U>> samplerFactory): samplerFactory가 반환한 publisher가 next signal을 내기 전까지 방출된 값 중 첫번째 값을 발행
//        System.out.println("-----sampleFirst-----");
//        Flux.interval(Duration.ofMillis(500)) // 0.5초마다 값을 방출
//                .log()
//                .sampleFirst(Duration.ofMillis(1000)) // 1초마다 첫 번째 값 하나만 방출
//                .take(5) // 방출할 값의 개수를 5개로 제한
//                .log()
//                .subscribe(System.out::println);

        // sample(Duration timespan): 일정 시간 간격으로 방출된 값 중 마지막 값을 발행
        // sample(Publisher<U> sampler)
//        System.out.println("-----sample-----");
//        Flux.interval(Duration.ofMillis(500)) // 0.5초마다 값을 방출
//                .log()
//                .sample(Duration.ofMillis(1000)) // 1초마다 마지막 값 하나만 방출
//                .take(5) // 방출할 값의 개수를 5개로 제한
//                .log()
//                .subscribe(System.out::println);

        // replay
        // 마지막으로 방출된 signal을 cache해두고 replay. completion과 error도 replay된다. connectableFlux로 반환
        // int history: 개수 지정
        // Duration ttl: 시간 지정
        // Scheduler timer: scheduler 지정
//        System.out.println("-----replay-----");
//        ConnectableFlux<Long> source = Flux.interval(Duration.ofMillis(1000))
//                .log()
//                .take(5)
//                .replay(2);
//
//        // 첫 번째 Subscriber
//        source.log().subscribe(value -> System.out.println("Subscriber 1: " + value));
//        source.connect();
//
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException();
//        }
//
//        // 두 번째 Subscriber
//        source.log().subscribe(value -> System.out.println("Subscriber 2: " + value));
//
//        // 연결
////        source.connect();

        // reduceWith(Supplier<A> initial, BiFunction<A,? super T,A> accumulator)
        // Flux의 값들을 조합하여 하나의 결과값을 생성 -> Mono로 반환
        // scanWith와 같은 과정, 결과값은 하나
//        System.out.println("-----reduceWith-----");
//        Flux.range(1, 5)
//                .reduceWith(() -> 0, (Integer::sum)) // 0 + 1 -> 1 + 2 -> 3 + 3 -> 6 + 4 -> 10 + 5 -> 15 발행
//                .log()
//                .subscribe(System.out::println);

        // reduce
        // initial 값이 없으면 첫 번째 값을 initial로 사용
//        System.out.println("-----reduce-----");
//        Flux.range(1, 5)
//                .scan((Integer::sum))
//                .log()
//                .subscribe(System.out::println);

        // range(int start, int count)
        // start부터 1씩 증가시켜서 count 개수만큼 발행 -> start부터 (start + count -1)까지 발행
//        System.out.println("-----range-----");
//        Flux.range(13, 5)
//                .subscribe(System.out::println);

        // push
        // 프로그래머가 직접 요소를 생성하는 새로운 Flux를 만들 수 있도록 해준다.
        // create: multi-threaded, push: single-threaded
//        System.out.println("-----push-----");
//        Flux.push(fluxSink -> {
//                    fluxSink.next(1);
//                    fluxSink.next(2);
//                    fluxSink.complete();
//                })
//                .log()
//                .subscribe(System.out::println);

    }
}

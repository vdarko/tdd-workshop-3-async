package com.vdarko.workshop.tdd.async.counter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

public class SynchedCounterAtomicIntegerTest {

  private static final int THREAD_COUNT = 8;

  /**
   * <b>GIVEN</b> synched counter</br>
   * <b>AND</b> counter invokers</br>
   * <b>WHEN</b> counter invokers are invoking synched counter using
   * {@value #THREAD_COUNT} threads for 1 second</br>
   * <b>THEN</b> counter is incremented correctly since it is not synched</br>
   * <b>AND</b> actual and expected counter are equal</br>
   */
  @Test
  public void testNotSynchedHugeDifference() throws Exception {

    // GIVEN
    int invokersCount = 100;
    Long executionTime = 1_000L; // 1 second in millis

    // WHEN
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    Counter counter = new SynchedCounterAtomicInteger();

    List<CounterInvoker> invokers;
    try {
      invokers = new ArrayList<>(invokersCount);
      for (int index = 0; index < invokersCount; index++) {
        CounterInvoker invoker = new CounterInvoker(counter);
        CompletableFuture.runAsync(invoker, executorService);
        invokers.add(invoker);
      }

      Thread.sleep(executionTime);// NOSONAR:S2925 not using Awaitility in tests
    } finally {
      executorService.shutdownNow();
    }

    Integer actualCounterInvocations = invokers.stream()//
            .map(CounterInvoker::getInvokedCount)//
            .reduce(0, Math::addExact);
    Integer expectedCounterInvocations = counter.getCount();

    assertThat(actualCounterInvocations).isEqualTo(expectedCounterInvocations);
  }
}

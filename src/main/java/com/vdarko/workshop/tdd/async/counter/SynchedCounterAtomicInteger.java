package com.vdarko.workshop.tdd.async.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchedCounterAtomicInteger implements Counter {

  private AtomicInteger count;

  public SynchedCounterAtomicInteger() {
    this.count = new AtomicInteger(0);
  }

  @Override
  public void increment() {
    count.incrementAndGet();
  }

  @Override
  public Integer getCount() {
    return count.get();
  }
}

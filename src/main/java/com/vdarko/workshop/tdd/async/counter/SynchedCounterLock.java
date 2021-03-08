package com.vdarko.workshop.tdd.async.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchedCounterLock implements Counter {

  private Integer count;
  private Lock lock;

  public SynchedCounterLock() {
    this.count = 0;
    this.lock = new ReentrantLock();
  }

  @Override
  public void increment() {
    try {
      lock.lock();
      count++;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public Integer getCount() {
    return count;
  }
}

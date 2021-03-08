package com.vdarko.workshop.tdd.async.counter;

public class CounterInvoker implements Runnable {

  private Counter counter;
  private Integer invokedCount;

  public CounterInvoker(Counter counter) {
    this.counter = counter;
    this.invokedCount = 0;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      counter.increment();
      invokedCount++;
    }
  }

  public Integer getInvokedCount() {
    return invokedCount;
  }
}

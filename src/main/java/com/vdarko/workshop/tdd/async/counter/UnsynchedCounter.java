package com.vdarko.workshop.tdd.async.counter;

public class UnsynchedCounter implements Counter {

  private Integer count;

  public UnsynchedCounter() {
    this.count = 0;
  }

  @Override
  public void increment() {
    count++;
  }

  @Override
  public Integer getCount() {
    return count;
  }
}

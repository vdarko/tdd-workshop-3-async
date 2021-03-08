# tdd-workshop-3-async

## Counters

Counters are used as an example for demonstrating how multiple threads which are accessing unsynchronized resource can lead to unpredictable results. Example contains following counters:
1. UnsynchedCounter uses Integer field for counting invocations and increments using `++` operator
1. SynchedCounterLock uses Integer field with for counting invocations and protects it with lock mechanism
1. SynchedCounterAtomicInteger uses AtomicInteger field for counting invocations

Tests for UnsynchedCounter indicate that increment calls overlap and overwrite the value incremented using `++` operator. This problem is fixed using `java.concurrent.Lock` for accessing Integer counter in SynchedCounterLock.

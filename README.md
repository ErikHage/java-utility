# Java Utility

Includes utility classes for math, collections, operations, scheduling

### Collections
- Heap
- Quad Tree
- Ring Buffer
- Trie (2 ways)


### Either
- Left/Right
- Utility Methods


### Lazy
- Lazily loaded fields


### Math
- Expression Evaluation
- Points
- Vectors
- Trig
- Probability


### Monad
- State
- Writer


### Operation Chain
- Basic
  - Wraps an object and provides methods to perform Transformations and Validations on that object. Keeps an audit trail of the results of all of these actions and their results.
- Short Circuit


## Executors  
- SturdyScheduler  
  - A wrapper around java.util.concurrent.ScheduledExecutorService that will continue to invoke the scheduled process as configured even if an exception is thrown during an invocation. The default behavior of ScheduledExecutorService will not invoke the process again in that scenario. 


### Testing
- Test Utils
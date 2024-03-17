# Java Utility

Includes utility classes for math, collections, operations, scheduling

### Collections
- Heap
  - MaxHeap
    - A complete binary tree in which the value in each internal node is greater than or equal to the values in the children of that node
  - MinHeap
    - A complete binary tree in which the value in each internal node is smaller than or equal to the values in the children of that node.
- Quad Tree
  - A tree in which each node can have up to 4 children
- Ring Buffer
  - A fixed size array that overwrites itself in a circular sequence
- Trie (2 ways)
  - A type of search tree used for locating specific keys from within a set (strings)


### Either
- Left/Right
  - An Either can be either a left or a right, and has methods that covers both cases
- Utility Methods
  - A collection of methods to help use Eithers in streams 


### Lazy
- Lazily loaded fields
  - Lazily loads a value of type T using a Supplier provided upon instantiation


### Math
- Expression Evaluation
- Points
  - Representation of a point in space, with x, y, and z coordinates
- Vectors
  - Representation of a vector, with x, y, and z magnitudes and a direction
- Trig
  - Some trigonometric functions for working with vectors
- Probability
  - Weighted Probability
    - Functionality to use weighted probabilities 


### Monad
- State
- Writer


### Operation Chain
- Basic
  - Wraps an object and provides methods to perform Transformations and Validations on that object. Keeps an audit trail of the results of all of these actions and their results.
- Short Circuit
  - If a failure occurs, a ShortCircuitOperationChain is substituted for the BasicOperationChain, which skips all transformations and validations to the end of the chain.  


## Executors  
- SturdyScheduler  
  - A wrapper around java.util.concurrent.ScheduledExecutorService that will continue to invoke the scheduled process as configured even if an exception is thrown during an invocation. The default behavior of ScheduledExecutorService will not invoke the process again in that scenario. 


### Testing
- Test Utils
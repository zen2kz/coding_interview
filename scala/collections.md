## Collections and their complexity

## Immutable 

| Collection | Head   | Tail   | Access (index) | Update | Prepend | Append | Notes                                                            |
|------------|--------|--------|----------------|--------|---------|--------|------------------------------------------------------------------|
| List       | O(1)   | O(1)   | O(n)           | O(n)   | O(1)    | O(n)   | Single-linked list with boundaries                               |
| Stream     | O(1)   | O(1)   | O(n)           | O(n)   | O(1)    | O(n)   | Same as list, but lazy computed and no boundaries. Can cause OOM |
| Vector     | e.O(1) | e.O(1) | e.O(1)         | e.O(n) | e.O(1)  | e.O(1) | Tree with a high branching factor with 6 layers of arrays.       |
| Queue      | a.O(1) | a.O(1) | O(n)           | O(n)   | O(1)    | O(1)   | Internally as 2 lists - one for enqueuing values and one for deq |  
| Range      | O(1)   | O(1)   | O(1)           | -      | -       | -      | Internally - 3  values: start, end, stepping                     |
| String     | O(1)   | O(n)   | O(1)           | O(n)   | O(n)    | O(n)   | Immutable array of characters                                    |

## Mutable

| Collection    | Access (index) | Update    | Prepend | Append   | Notes                                               |
|---------------|----------------|-----------|---------|----------|-----------------------------------------------------|
| ArrayBuffer   | O(1)           | O(1)      | O(n)    |a.O(1)    | Efficient for random access and writes              |
| ListBuffer    | O(n)           | O(1)      | O(1)    |          | Efficient for prepend and append                    |
| Array         | O(1)           | O(1)      | -       | -        | Same as Java T[] array. Good for primitive types.   |
| ArraySeq      | O(1)           | O(1)      | -       | -        | Stores everything as objects (even primitive types) | 
| ArrayStack    | O(1)           | O(1)      | a.O(1)  | O(n)     | LIFO. Implements pop and push.                      |
| HashSet       | e.O(1)         | e.O(1)    | -       | -        | Backed by HashTable                                 |
| TreeSet       | O(log n)       | O(log n)  | -       | -        | Backed by red-black tree. Self-balancing.           |
| LinkedHashSet | e.O(1)         | e.O(1)    | -       | -        | Iterates in an order of insertion                   |
| BitSet        | O(1)           | a.O(1)    | -       | -        | Stores Ints as 64-bit words in an array             |
| HashMap       | e.O(1)         | e.O(1)    | -       | -        | Backed by HashTable                                 |
| TreeMap       | O(log n)       | O(log n)  | -       | -        | Backed by red-black tree. Self-balancing.           |             
| LinkedHashMap | e.O(1)         | e.O(1)    | -       | -        | Iterates in an order of insertion                   |
| ListMap       | O(n)           | O(n)      | O(n)    | -        | Stored as a list of tuples                          |
| OpenHashMap   | e.O(1)         | e.O(1)    | -       | -        |                                                     |
| LongMap       | e.O(1)         | e.O(1)    | -       | -        | Similar to HashMap but faster on contains and get   |
| Queue         | O(n)           | O(n)      |         | O(1)     | Enqueue and dequeue are O(1)                        |
| PriorityQueue | O(n)           | O(n)      |         | O(log n) | Heap. Biggest first                                 |
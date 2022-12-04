# Design Patterns
### Creational
- *Factory Method*: Creates objects with a common interface and lets a class defer instantiation to subclasses.
  - Use cases: multiple implementations (for example logger, database connector and etc)
- *Abstract Factory*: Creates a family of related objects.
- *Builder*: A step-by-step pattern for creating complex objects, separating construction and representation.
  - Use cases: a lot of attributes (often optional), unit tests, database & other clients initialization
  - Example `Pizza pizza = new Pizza.Builder(Size.medium).onion(true).olives(true).build();`
- *Prototype*: Supports the copying of existing objects without code becoming dependent on classes.
  - Use cases: creating a chess board for each game, 
- *Singleton*: Restricts object creation for a class to only one instance

### Structural
- *Adapter*: How to change or adapt an interface to that of another existing class to allow incompatible interfaces to work together.
- *Bridge*: A method to decouple an interface from its implementation.
- *Composite*: Leverages a tree structure to support manipulation as one object.
- *Decorator*: Dynamically extends (adds or overrides) functionality.
  - Use cases: add border or shadow feature to a shape object
- *Façade*: Defines a high-level interface to simplify the use of a large body of code.
- *Flyweight*: Minimize memory use by sharing data with similar objects.
- *Proxy*: How to represent an object with another object to enable access control, reduce cost and reduce complexity.

### Behavioral 
- *Chain of Responsibility*: A method for commands to be delegated to a chain of processing objects.
- *Command*: Encapsulates a command request in an object.
- *Interpreter*: Supports the use of language elements within an application.
- *Iterator*: Supports iterative (sequential) access to collection elements.
- *Mediator*: Articulates simple communication between classes.
- *Memento*: A process to save and restore the internal/original state of an object.
- *Observer*: Defines how to notify objects of changes to other object(s).
  - Use cases: notifications of all kind (messaging & events, games, sending an email and etc)
- *State*: How to alter the behavior of an object when its stage changes.
- *Strategy*: Encapsulates an algorithm inside a class.
  - Use cases: e-commerce payments (selecting one or multiple payment methods and follow their requirements)
- *Visitor*: Defines a new operation on a class without making changes to the class.
- *Template Method*: Defines the skeleton of an operation while allowing subclasses to refine certain steps.
  - Use cases: credit card validation (common steps, but different implementation)
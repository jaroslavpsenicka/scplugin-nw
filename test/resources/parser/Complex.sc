// Lovely process

process Test {

    // Name
    String name = "Hello";
    Integer value;

    // Load from separate files
    task task1 from "./task1.sc";
    task task2 from "./task2.sc";

}
# Final Reflection — Library System Abstraction

1. **Which class is the strongest abstraction in your design? Explain why.**  
   Book. It maps cleanly to a real thing in the library, and it protects its own availability. Other classes ask it to check out or return instead of flipping a public flag. That feels like a real abstraction, not just a data bag.

2. **Give one example of implementation detail that your client code does not need to understand.**  
   LibraryApp doesn't need to know that Library stores loans in an ArrayList or how findActiveLoanForBook loops. It just calls checkoutBook / returnBook and shows the result.

3. **Describe one place where encapsulation protects your system from an invalid state.**  
   `available` on Book is private. The only way to mark a book checked out is `checkout()`, which returns false if it's already out. So you can't accidentally set the same book to checked out twice from the menu code.

4. **What responsibility were you tempted to place in the wrong class? Where did you put it instead, and why?**  
   I almost put all the checkout if-statements only in LibraryApp. Instead Library coordinates the lookup + Loan creation, and Book still owns the availability change. LibraryApp just reads ISBN/student ID and prints messages.

5. **If this system grew to 10,000 books and thousands of students, what part of your design would you want to improve next?**  
   Searching with linear ArrayList scans would get slow. I'd switch to something like a HashMap from ISBN → Book and studentId → Student, and maybe store only active loans in a separate structure so listing loans doesn't walk history every time.

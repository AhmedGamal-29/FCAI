#ifndef BOOKLIST_H
#define BOOKLIST_H
#include <iostream>
#include <cstring>
#include "User.h"
#include "Book.h"
using namespace std;

class BookList
{
  private:
       Book* books;
       int capacity;
       int booksCount;


  public:
    void addBook(Book b);
    BookList(const BookList& anotherList);
    BookList(int capacity1);
    Book& searchBook(string name);
    Book& searchBook(int id);
    int getCapacity();
    void Display();
    void deleteBook(int id); //delete a book
    Book getTheHighestRatedBook();
    Book* getBooksForUser(User); // get all books of this author
    Book & operator [ ] (int position);
    friend ostream &operator<<( ostream &output, BookList &booklist ); //to display all books
    ~BookList();




};

#endif // BOOKLIST_H

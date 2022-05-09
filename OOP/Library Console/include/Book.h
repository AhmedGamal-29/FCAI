#ifndef BOOK_H
#define BOOK_H
#include <iostream>
#include <cstring>
#include "User.h"
using namespace std;

class Book
{
    private:
        string title;
        string isbn;
        int id;
        string category;
        double sum = 0.0, numrat = 0.0; // Using to calculate average rating of the books
        double averageRating = 0;
        User author; // Assign user to be the author of the book

    public:

     static int counter; //Used to increment the id
     Book();
     Book(string title, string isbn, string category);
     Book(const Book& book);

     // Used to access private attributes
     void setTitle(string title);
     string getTitle();

     void setISBN(string isbn);
     string getISBN();

     void setId(int id);
     int getId();

     void setCategory(string category);
     string getCategory();

     void setAuthor(User user);
     User getAuthor();

     void rateBook(int rating);
     double getRate();

     bool operator==(const Book& book);

     friend ostream &operator<<(ostream &output, const Book &book );
     friend istream &operator>>( istream &input, Book &book );


};

#endif // BOOK_H

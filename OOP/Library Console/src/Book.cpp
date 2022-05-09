#include "Book.h"
#include "User.h"
#include <iostream>
#include <cstring>
using namespace std;
// Default Constructor
Book::Book():title(""),category(""),id(counter),isbn("")
{
counter++;
}
// Parametrized Constructor
Book::Book(string t,string i,string c)
{
    title=t;
    isbn=i;
    category=c;
    id = counter;
    counter++;
}
//Copy Constructor
Book::Book(const Book &book)
{
    title = book.title;
    isbn = book.isbn;
    category = book.category;
    id = book.id;

}

void Book::setTitle(string T1)
{
    title=T1;
}

string Book::getTitle()
{
    return title;
}

void Book::setISBN(string isbn1)
{
    isbn=isbn1;
}


string Book::getISBN()
{
    return isbn;
}

void Book::setId(int id1)
{
    id=id1;
}

int Book::getId()
{
    return id;
}

void Book::setCategory(string catg1)
{
    category=catg1;
}

string Book::getCategory()
{
    return category;
}

void Book::setAuthor(User user)
{
    author = user;
}

User Book::getAuthor()
{
    return author;
}


void Book::rateBook(int rating)
{
    sum += rating;
    numrat++;
    averageRating = sum / numrat;
}

double Book::getRate()
{
    return averageRating;
}


bool Book::operator==(const Book& book)
{
    bool flag;
    if (title == book.title && category == book.category && isbn
        == book.isbn && author == book.author && id==book.id)
    {
        flag = true;
    }
    else flag = false;
    return flag;
}

ostream& operator<<(ostream& output, const Book& book)
{
    output << "========Book " << book.id << " info========" << endl;
    output << "Title: " << book.title <<endl<< "ISBN: " << book.isbn <<endl<< "ID: " << book.id <<endl<< "Category: " << book.category
        <<endl<< "Avg rating: " << book.averageRating << endl<<endl;
    return output;
}

istream& operator>>(istream& input, Book& book)
{
    input >> book.title >> book.isbn >> book.category;
    return input;
}


int Book::counter=1;


#include "BookList.h"
#include "Book.h"

 BookList::BookList(const BookList& anotherList):  capacity(anotherList.capacity), booksCount(anotherList.booksCount)
 {
    books = new Book[capacity];
    for(int i=0;i<booksCount;i++){
    books[i] = anotherList.books[i];
    }


 }

BookList::BookList(int capacity1):capacity(capacity1),booksCount(0)
    {

    books = new Book[capacity];




    }


    void BookList::addBook(Book b)
{
    booksCount++;
    for (int i = 0; i < capacity; i++) {
        if (books[i].getTitle() == "") {
            books[i].setCategory(b.getCategory());
            books[i].setISBN(b.getISBN());
            books[i].setTitle(b.getTitle());
            break;
        }
    }
}

Book& BookList::searchBook(string name)
    {

        for(int i=0;i<capacity;i++)
        {
            if(books[i].getTitle()==name)
            {
                return books[i];
            }

        }



    }
   Book& BookList::searchBook(int id)
   {
       for(int i=0;i<capacity;i++)
       {
           if(books[i].getId()==id)
           {
               return books[i];
           }
       }

   }

     void BookList::Display()
     {
         for(int i=0;i<booksCount;i++)
         {
             cout<<books[i];
         }
     }


     int BookList::getCapacity()
     {
         return capacity;
     }


     void BookList::deleteBook(int id) //delete a book
{

}


    Book BookList::getTheHighestRatedBook()
    {
        int mx = books[0].getRate();
        int j=0;
        for(int i=1;i<booksCount;i++)
        {
            if(books[i].getRate()>=mx)
            {
                mx=books[i].getRate();
                j=i;
            }
        }
        return books[j];
    }

    /*Book* getBooksForUser(User)  // get all books of this author
    {
        }
        */
    Book &BookList:: operator [ ] (int position)
    {
        if(position<0 || position>=capacity) exit(1);
        return books[position];
    }

     ostream &operator<<( ostream &output, BookList &booklist ) //to display all books
   {
              for(int i=0;i<booklist.getCapacity();i++)
         {
             output<<booklist[i];
         }
         return output;
   }


    BookList::~BookList()
    {
        delete [] books;
    }




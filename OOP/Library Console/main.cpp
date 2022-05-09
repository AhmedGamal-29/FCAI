#include <iostream>
#include "Book.h"
#include "BookList.h"
#include "User.h"
#include "UserList.h"
#include <cstring>
using namespace std;

int main()
{


while(true){
   cout<<"Select one of the following :\n";
   cout<<"1- Books Menu\n";
   cout<<"2- Users Menu\n";
   cout<<"3- Exit\n";
   int c1 ;
   cin>>c1;

if (c1 == 1 )
{

    cout<<"How many books will be added?"<<endl;
    int booksNum;
    cin>> booksNum;


    BookList bookList(booksNum);
    while(true){
            int i=0;

            cout<<"BOOKS MENU\n";
            cout<<"1- Create a Book \n";
            cout<<"2- Search for a book\n";
            cout<<"3- Display all books\n";
            cout<<"4- Get the highest rating book\n";
            cout<<"5- Get all books of a user\n";
            cout<<"6- Copy the current book list to a new book list\n";
            cout<<"7- Back to the main menu\n";

            int c2; cin>>c2;



            if(c2 == 1)
            {
                cout<<"Enter the book information in this order"<<endl;
                cout<<"Title ISBN Category (Space separated)"<<endl;
                Book b;
                cin>>b;
                bookList.addBook(b);
                i++;
                while(true)
                {
                    cout<<"1- Assign Author\n"<<"2- Continue\n";
                    int s3; cin>>s3;
                    if(s3==1)
                    {
                        //b.setAuthor()


                        break;
                    }
                    else
                    {
                        break;
                    }
                }


            }
            else if (c2 == 2)
            {
                while(true)
                    {
                    cout<<"SEARCH FOR BOOK\n";
                    cout<<"1- search by name\n";
                    cout<<"2- search by id\n";
                    cout<<"3- Return to Books Menu\n";

                    int s1;   cin>>s1;
                        if(s1==1)
                            {
                                cout<<"Enter book name: \n";
                                string sea;  cin>>sea;
                                Book BB=bookList.searchBook(sea);
                                cout<<BB;
                                while(true)
                                {



                                cout<<"1- Update Name"<<endl;
                                 cout<<"2- Update Category"<<endl;
                                cout<<"3- Delete Book"<<endl;
                                 cout<<"4- Rate Book"<<endl;
                                cout<<"5- Return to Books Menu"<<endl;
                                int s2;
                                cin>>s2;
                                if(s2==1){
                                        cout<<"Enter New Name:\n";
                                        string bName; cin>>bName;
                                        BB.setTitle(bName);
                                        bookList[BB.getId()-1]=BB;
                                        }
                                else if(s2==2){
                                        cout<<"Enter New Category:\n";
                                        string bCategory; cin>>bCategory;
                                        BB.setCategory(bCategory);
                                        bookList[BB.getId()-1]=BB;
                                }
                                else if(s2==3){
                                    bookList.deleteBook(BB.getId());
                                }
                                else if (s2==4){
                                        cout<<"Enter rating value: \n";
                                   int rv;             cin>>rv;
                                    BB.rateBook(rv);
                                    bookList[BB.getId()-1]=BB;
                                }

                                else {
                                        break;
                                            }
                                }
                            }

                        else if(s1==2)
                            {
                                cout<<"Enter book id: \n";
                                int sei;  cin>>sei;
                                Book BB=bookList.searchBook(sei);
                                cout<<BB;

                                while(true)
                                {



                                cout<<"1- Update Name"<<endl;
                                 cout<<"2- Update Category"<<endl;
                                cout<<"3- Delete Book"<<endl;
                                 cout<<"4- Rate Book"<<endl;
                                cout<<"5- Return to Books Menu"<<endl;
                                int s2;
                                cin>>s2;
                                if(s2==1){
                                        cout<<"Enter New Name:\n";
                                        string bName; cin>>bName;
                                        BB.setTitle(bName);
                                        bookList[BB.getId()-1]=BB;
                                        }
                                else if(s2==2){
                                        cout<<"Enter New Category:\n";
                                        string bCategory; cin>>bCategory;
                                        BB.setCategory(bCategory);
                                        bookList[BB.getId()-1]=BB;
                                }
                                else if(s2==3){
                                    bookList.deleteBook(BB.getId());
                                }
                                else if (s2==4){
                                        cout<<"Enter rating value: \n";
                                   int rv;             cin>>rv;
                                    BB.rateBook(rv);
                                    bookList[BB.getId()-1]=BB;
                                }

                                else {
                                        break;
                                            }
                                }


                            }

                        else if(s1==3)
                            {
                                break;
                            }
                    }
            }
        else if (c2 == 3)
            {
                bookList.Display();
            }
        else if (c2 == 4)
            {
                cout<<bookList.getTheHighestRatedBook();
            }
        else if (c2 == 5)
            {

            }
        else if (c2 == 6)
            {
                i=0;
                BookList newbookList(bookList);
                cout<<"Done ,, It is the new list that is copied \n";
                newbookList.Display();

            }
        else if (c2 == 7)
            {
                break;
            }

        }





}

else if (c1 == 2)
{
    cout<<"How many users will be added ?"<<endl;
    int usersNum;
    cin>>usersNum;
    UserList userlist(usersNum);
    while(true){
            int i=0;
        cout<<"Users Menu"<<endl;
        cout<<"1- Create User and add it to the list"<<endl;
        cout<<"2- Search for a user"<<endl;
        cout<<"3- Display all users"<<endl;
        cout<<"4- Back to the main menu"<<endl;
        int umenu1;
        cin>>umenu1;
        if(umenu1==1){
           cout<<"Enter the user information in this order"<<endl;
           cout<<"Name Age Email Password (Space separated)"<<endl;
           User user1;
           cin>>user1;
          userlist.addUser(user1);
            i++;


        }

        else if(umenu1==2){
            while(true){
                cout<<"SEARCH FOR USER"<<endl;
                cout<<"1- Search by name "<<endl;
                cout<<"2- Search by id "<<endl;
                cout<<"3- Return to Users Menu"<<endl;
                int umenu2;
                cin>>umenu2;

                if(umenu2==1){
                    cout<<"Enter Name"<<endl;
                    string name;
                    cin>>name;
                    User W;
                    W=userlist.searchUser(name);
                    cout<<"========== User " << W.getId() <<" info ======="<<endl;
                    cout<<userlist.searchUser(name);
                    cout<<"================================================"<<endl;
                    cout<<"1-Delete user"<<endl;
                    cout<<"2-Return to Users Menu"<<endl;
                    int su;
                    cin>>su;
                    if(su==1){
                        userlist.deleteUser(W.getId());
                        }
                    else if(su==2){
                        continue;
                        }

                    }

                else if(umenu2==2){
                    cout<<"Enter ID"<<endl;
                    int id;
                    cin>>id;
                    cout<<"========== User " << id <<" info ======="<<endl;
                    cout<<userlist.searchUser(id);
                    cout<<"==========================================="<<endl;
                    cout<<"1- Delete user"<<endl;
                    cout<<"2- Return to Users Menu"<<endl;
                    int iu;
                    cin>>iu;
                    if(iu==1){
                        userlist.deleteUser(id);
                    }
                    else if(iu==2){
                        continue;
                    }
                }
                else if(umenu2==3){
                    break;
                }

            }
        }
        else if (umenu1==3){
            userlist.Display();
        }
        else if (umenu1==4){
            break;
        }

    }


}
else
{
  return 0;

}







    }
}




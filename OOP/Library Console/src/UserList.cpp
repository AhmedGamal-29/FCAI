#include"UserList.h"
#include"User.h"
#include<iostream>
#include<cstring>

using namespace std;



UserList::UserList(int capacity)
{

    this->capacity=capacity;
    users=new User[capacity];
    usersCount=0;

}





User& UserList::searchUser(string name){

    for(int i=0; i<capacity; i++){
        if(users[i].getName()==name){
            return users[i];
        }
    }

}

User& UserList::searchUser(int id){

    for(int i=0; i<capacity; i++){
        if(users[i].getId()==id){
            return users[i];
        }
    }

}


 void UserList::addUser(User user) // at the end of the array.
{


   users[usersCount]=user;
   users[usersCount].setId(usersCount+1);
    usersCount++;

}


void UserList::deleteUser(int id)
{


}

int UserList::getCapacity()
{
    return capacity;
}
 User &UserList:: operator [ ] (int position)
    {
        if(position<0 || position>=capacity) exit(1);
        return users[position];
    }

 ostream &operator<<( ostream &output, UserList &userlist ) //to display all users
   {
              for(int i=0;i<userlist.getCapacity();i++)
         {
             output<<userlist[i];
         }
         return output;
   }

   void UserList::Display()
     {
         for(int i=0;i<usersCount;i++)
         {
             cout<<users[i];
         }
     }


UserList::~UserList()
{
    delete[] users;
}

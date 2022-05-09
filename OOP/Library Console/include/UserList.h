#ifndef USERLIST_H
#define USERLIST_H
#include<iostream>
#include<cstring>
#include<User.h>

using namespace std;


class UserList
{
    private:
        User* users;
        int capacity;
        int usersCount;
    public:
        UserList(int capacity);
        void addUser(User user); // at the end of the array.
        User& searchUser(string name);
        User& searchUser(int id);
        int getCapacity();
        User & operator [ ] (int position);
        void deleteUser(int id);
        void Display();
        friend ostream &operator<<( ostream &output, UserList &userList );//to display all books
        ~UserList();
};
#endif // USERLIST_H

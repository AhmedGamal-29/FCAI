#include "User.h"
#include<iostream>
#include<cstring>
using namespace std;

//Default Constructor
User::User()
{
    name=" ";
    id=countt;
    countt++;
}

//Parameterized Constructor
User::User(string n, int a , string e , string p ){
    name=n;
    age=a;
    password=p;
    email=e;
    id=countt;
    countt++;
}

//Copy Constructor
User::User(const User& user){
    name=user.name;
    age=user.age;
    password=user.password;
    email=user.email;
    id=user.id;
    countt++;
}

void User::setName(string n){
    name=n;
}

void User::setPassword(string p){
    password=p;
}

void User::setEmail(string e){
    email=e;
}

void User::setAge(int a){
    age=a;
}

void User::setId(int i){
    id=i;
}

string User::getName()const{
    return name;
}

string User::getPassword(){
    return password;
}

string User::getEmail(){
    return email;
}

int User::getAge(){
    return age;
}

int User::getId(){
    return id;
}
//Overloading Operators

bool User::operator==(const User&user){
    bool same;
    if (name==user.name && email==user.email && age==user.age && id == user.id ){

        same=true;
    }
    else {
        same=false;
    }
    return same;
}


ostream& operator<<(ostream& output, const User& user)
{
	output << "========User " << user.id << " info========" << endl;
    output << "Name: " << user.name <<endl<< "Age:  " << user.age <<endl<< "ID: " << user.id <<endl<< "Email:  " << user.email<< endl<<endl;
    return output;
}
istream& operator>>(istream& input, User& user)
{
	input >> user.name >> user.age >> user.email >> user.password;
	return input;
}

int User::countt=1;

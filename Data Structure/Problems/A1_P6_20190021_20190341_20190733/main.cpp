#include <iostream>
#include<string>
#include<vector>

using namespace std;

class StudentName{
    public: string fullName;

    StudentName(string enteredName){

        int lastName=0,space=0;
        for (int i=0;i<enteredName.length();i++){
            if(enteredName[i]==' '){
                space++;
                lastName=i;
            }
            if (space<2){
                if(space==0){
                    fullName=enteredName+" "+enteredName+" "+enteredName;
                }
                else if (space==1){
                    fullName=enteredName+enteredName.substr(lastName);
                }
            }
            else {
                fullName=enteredName;
            }
        }
    }

    void print(){
        vector <string> arrayOfNames;
        string enteredName=" ";
        for(int i=0; i<fullName.length(); i++){
            if (fullName[i]==' '){
                arrayOfNames.push_back(enteredName);
                enteredName=" ";
            }
            else if(i==fullName.length()-1){
                enteredName=enteredName+fullName[i];
                arrayOfNames.push_back(enteredName);
            }
            else{
                enteredName=enteredName+fullName[i];
            }
        }
        int num=1;
        for(int i=0; i<arrayOfNames.size(); i++){
            cout<<num<<")"<<arrayOfNames[i]<<endl;
            num++;
        }
    }

    bool Replace(int i,int j){
        vector <string> arrayOfNames;
        string enteredName="";
        for(int i=0; i<fullName.length(); i++){
            if (fullName[i]==' '){
                arrayOfNames.push_back(enteredName);
                enteredName="";
            }
            else if(i==fullName.length()-1){
                enteredName=enteredName+fullName[i];
                arrayOfNames.push_back(enteredName);
            }
            else
                enteredName=enteredName+fullName[i];
        }
        if(i > arrayOfNames.size() || j > arrayOfNames.size()){

            return false;
        }
        else{
            string x=arrayOfNames[j-1];
            arrayOfNames[j-1]=arrayOfNames[i-1];
            arrayOfNames[i-1]=x;
            string y;
            for(int i = 0 ; i < arrayOfNames.size() ; i++){
                y=y+arrayOfNames[i] + " ";
            }
            fullName = y;

            cout <<fullName<<endl;
            return true;
        }
    }
};

int main()
{

    StudentName test1("Ahmed Gamal AbdelMoniem");

        if(test1.Replace(1,3)){
        test1.print();
        cout<<endl;
    }

     StudentName test2("Ahmed Gamal Ahmed Mohamed");

        if(test2.Replace(2,4)){
        test2.print();
        cout <<endl;
    }
    StudentName test3("Aly Muhammad Sayed Aly");

        if(test3.Replace(2,4)){
        test3.print();
        cout <<endl;
    }

    StudentName test4("Ahmed Gamal");

        if(test4.Replace(1,3)){
        test4.print();
        cout<<endl;
    }
    StudentName test5("Ahmed");

        if(test5.Replace(1,2)){
        test5.print();
        cout <<endl;
    }







    return 0;
}

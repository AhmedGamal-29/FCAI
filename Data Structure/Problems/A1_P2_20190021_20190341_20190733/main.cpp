#include <iostream>
using namespace std;


class Fraction
{
    int num, dum;

public:
    Fraction(){}
    Fraction(int n,int d)
    {
        num=n;
        dum=d;
    }
    int commonDiv(int x, int y)
    {
        if (y == 0)
            return x;
        return commonDiv(y, x % y);
    }
    void reduce ()
    {
        int x=commonDiv(num,dum);
        num/=x;
        dum/=x;
    }
    bool operator <= (Fraction f)
    {
        if((float)num/dum<=(float)f.num/f.dum)
            return 1;
        else
            return 0;
    }
    bool operator >= (Fraction f)
    {
        if((float)num/dum>=(float)f.num/f.dum)
            return 1;
        else
            return 0;
    }
    bool operator == (Fraction f)
    {
        if(num==f.num && dum==f.dum)
            return 1;
        else
            return 0;
    }
    bool operator != (Fraction f)
    {
        if(num==f.num && dum==f.dum)
            return 0;
        else
            return 1;
    }

    friend istream &operator>> (istream &is,Fraction& f)
    {
        cout<<"Numerator: \n";
        is>>f.num;
        cout<<"Denominator: \n";
        is>>f.dum;
        f.reduce();
        return is;
    }

    friend ostream &operator<< (ostream &os,Fraction f)
    {
        if(f.dum==0)
            os<<"Wrong ,,can't divide by 0\n";
        else if(f.num==0)
            os<<0<<endl;
        else if(f.num==f.dum)
            os<<1<<endl;
        else if(f.dum==1)
            os<<f.num<<endl;
        else
            os<<f.num<<'/'<<f.dum<<endl;
    }



    Fraction operator+ (Fraction f)
    {
        Fraction res;
        res.num= (num*f.dum)+(dum*f.num);
        res.dum= dum*f.dum;
        res.reduce();
        return res;
    }

    Fraction operator- (Fraction f)
    {
        Fraction res;
        res.num= (num*f.dum)-(dum*f.num);
        res.dum= dum*f.dum;
        res.reduce();
        return res;
    }

    Fraction operator* (Fraction f)
    {
        Fraction res;
        res.num= num*f.num;
        res.dum= dum*f.dum;
        res.reduce();
        return res;
    }

    Fraction operator/ (Fraction f)
    {
        Fraction res;
        res.num= num*f.dum;
        res.dum= dum*f.num;
        res.reduce();
        return res;
    }


};


int main()
{
    cout<<"1- Perform Fraction Addition\n";
    cout<<"2- Perform Fraction Subtraction\n";
    cout<<"3- Perform Fraction Multiplication\n";
    cout<<"4- Perform Fraction Division\n";
    cout<<"5- Compare two fractions\n";
    cout<<"6- Exit\n";

    int choice;
    cin>>choice;

    Fraction f1,f2;

    if(choice==1)
    {
        cout<<"1st fraction: \n";
        cin>>f1;
        cout<<"2nd fraction: \n";
        cin>>f2;
        cout<<f1+f2;
    }
    else if(choice==2)
    {
        cout<<"1st fraction: \n";
        cin>>f1;
        cout<<"2nd fraction: \n";
        cin>>f2;
        cout<<f1-f2;
    }
    else if(choice==3)
    {
        cout<<"1st fraction: \n";
        cin>>f1;
        cout<<"2nd fraction: \n";
        cin>>f2;
        cout<<f1*f2;
    }
    else if(choice==4)
    {
        cout<<"1st fraction: \n";
        cin>>f1;
        cout<<"2nd fraction: \n";
        cin>>f2;
        cout<<f1/f2;
    }
    else if(choice==5)
    {
        cout<<"1st fraction: \n";
        cin>>f1;
        cout<<"2nd fraction: \n";
        cin>>f2;
        if(f1==f2)
            cout<<"Two fractions are equal";
        else if(f1<=f2)
            cout<<"1st fraction is smaller";
        else if(f1>=f2)
            cout<<"1st fraction is greater";

    }
    else
    {
        return 0;
    }

}

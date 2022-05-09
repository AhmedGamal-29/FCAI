#include <iostream>
using namespace std;

//Base Class MyList (1)
template <class T >
class MyList
{
protected:
    T *elems;
    int itsSize;
public:
    MyList(int s)
    {
     itsSize=s;
    elems=new T [itsSize];
    }
    int getSize()
    {
        return itsSize;
    }
    ~MyList()
    {
         delete [] elems;
    }

    virtual void addElem(T elm)=0;
    virtual T getElem()=0;
    virtual bool isEmpty()=0;
    virtual bool isFull()=0;
    virtual void clearItems()=0;


};


//Delivered Class MyStack (2)
template <class T>
class MyStack : public MyList <T>
{
public:
    MyStack (int s): MyList<T>::MyList(s)
    {
    }
    void addElem(T elm)
    {
        this->elems[I]=elm;
        I++;
        maxI=I;
    }
    T getElem()
    {
          I--;
        return this->elems[I];
    }
    bool isEmpty()
    {
          if(maxI!=0)
            return false;

        else
            return true;
    }
    bool isFull()
    {
        if(maxI==this->itsSize)
            return true;

        else
            return false;
    }
    void clearItems()
    {
        delete [] this->elems;
    }

    //To control elements in stack
    static int I;
    int maxI=0;
};
template <class T>
int MyStack<T>::I=0;




//Delivered class MyQueue (3)
template <class T>
class MyQueue : public MyList<T>
{
    public:
    MyQueue (int s): MyList<T>::MyList(s)
    {
    }
    void addElem(T elm)
    {
        this->elems[I]=elm;
        I++;
        maxI=I;
    }
    T getElem()
    {
         I--;
        return this->elems[this->itsSize- I-1 ];
    }
    bool isEmpty()
    {
        if(maxI!=0)
            return false;

        else
            return true;
    }
    bool isFull()
    {
        if(maxI==this->itsSize)
            return true;

        else
            return false;
    }
    void clearItems()
    {
        delete [] this->elems;
    }

    //To deal with elements in queue
    static int I;
    int maxI=0;
};
template <class T>
int MyQueue<T>::I=0;


// class rectangle (4)
class Rectangle
{
private:
    int length;
    int width;
public:
    Rectangle():length(0),width(0){}

    Rectangle(int l,int w)
    {
    try
        {
        length=l;
        width=w;
        if(length<0 || width <0)
        {
            throw 0;
        }
        length=l;
        width=w;
        }
    catch(int c)
        {
        cout<<"Wrong length or width ,entering 0 and 0\n";
        length=c;
        width=c;
        }
    }
    int getLength()
    {
        return length;
    }

    int getWidth()
    {
        return width;
    }

    int getArea()
    {
        return length*width;
    }

    friend ostream& operator<<(ostream& output,const Rectangle& Rec)
    {
        output<<"Length="<<Rec.length<<" ,Width="<< Rec.width <<" ,Area="<<Rec.length * Rec.width;
        return output;
    }
};



int main()
{
    char sq;
    cout<<"Would you like to use a stack or a queue(S/Q)? \n";
    cin>>sq;

    int cap;
    cout<<"How many items to store?\n";



    bool flag=0;
    do
    {
        try
        {
            cin>>cap;
            if(cap<0)
            {
                throw 0 ;
            }


            flag=1;
        }
        catch (int e)
        {
            cout<<"Invalid ,, please enter valid capacity\n";
        }
    }
    while (flag==0);

    int isr;
    cout<<"Would you like to store integers (1) , strings (2) , rectangles (3)? \n";
    cin>>isr;

    if(sq=='s' || sq=='S')
    {
        while(true)
        {

        if(isr==1)
        {
            MyStack <int> MS(cap);

            //Taking the data
            for(int i=0;i<cap;i++)
            {
                if(MS.isFull())
                {
                break;
                }
            try
                {
                cout<<"Enter Num: ";
                int in1;
                cin>>in1;
                if(cin.fail())
                {
                    throw 0;
                }
                MS.addElem(in1);
                }
            catch(int e)
                {
                cout<<"Invalid input , entering 0 instead \n";
                MS.addElem(e);
                }
            }

            //Display
            cout<<"All Elements \n";

            for(int i=0;i<cap;i++)
            {
                if(MS.isEmpty())
                {
                break;
                }
               cout<<"Element -->"<< MS.getElem()<<endl;
            }
            break;
        }
        else if (isr==2)
        {
            MyStack<string> MS(cap);

            //Taking the data
            for(int i=0;i<cap;i++)
            {
                if(MS.isFull())
                {
                    break;
                }
                cout<<"Enter text: ";
                string s;
                cin>>s;
                MS.addElem(s);
            }

            //Display
            cout<<"All Elements \n";
            for(int i=0;i<cap;i++)
            {
                if(MS.isEmpty())
                {
                    break;
                }
                cout<<"Element -->"<<MS.getElem()<<endl;
            }
            break;
        }
        else if (isr==3)
        {
            MyStack <Rectangle> MS(cap);

            //Taking data
            for(int i=0;i<cap;i++)
            {
                cout<<"Enter Length and width (space separated): ";
                int len,wid;
                cin>>len>>wid;
                Rectangle R(len,wid);
                MS.addElem(R);
            }

            //Display
            for(int i=0;i<cap;i++)
            {
                cout<<"Element -->"<<MS.getElem()<<endl;
            }
            break;
        }
        else
        {
            cout<<"Invalid type, please enter (1,2 or 3): \n";
            cin>>isr;
        }
        }
    }
    else if(sq=='q' || sq =='Q')
    {
        while(true)
        {

        if(isr==1)
        {
            MyQueue<int> MQ(cap);

            //Taking Data
            for(int i=0;i<cap;i++)
            {
                if(MQ.isFull())
                {
                    break;
                }
            try
            {
                cout<<"Enter Num: ";
                int in1;
                cin>>in1;
                if(cin.fail())
                {
                    throw 0;
                }
                MQ.addElem(in1);
            }
            catch(int e)
            {
                cout<<"Invalid input , entering 0 instead \n";
                MQ.addElem(e);
            }
            }

            //Display
            cout<<"All Elements \n";
            for(int i=0;i<cap;i++)
            {
                if(MQ.isEmpty())
                {
                    break;
                }
                cout<<"Element -->"<<MQ.getElem()<<endl;
            }

            break;

        }


        else if (isr==2)
        {
            MyQueue<string> MQ(cap);

             //Taking the data
            for(int i=0;i<cap;i++)
            {
                if(MQ.isFull())
                {
                    break;
                }
                cout<<"Enter text: ";
                string s;
                cin>>s;
                MQ.addElem(s);
            }

            //Display
            cout<<"All Elements \n";
            for(int i=0;i<cap;i++)
            {
                if(MQ.isEmpty())
                {
                    break;
                }
                cout<<"Element -->"<<MQ.getElem()<<endl;
            }
            break;

        }

        else if(isr==3)
        {
            MyQueue<Rectangle> MQ(cap);


            //Taking data
            for(int i=0;i<cap;i++)
            {
                if(MQ.isFull())
                {
                    break;
                }
                cout<<"Enter Length and width (space separated): ";
                int len,wid;
                cin>>len>>wid;
                Rectangle R(len,wid);
                MQ.addElem(R);
            }

            //Display
            for(int i=0;i<cap;i++)
            {
                if(MQ.isEmpty())
                {
                    break;
                }
                cout<<"Element -->"<<MQ.getElem()<<endl;
            }
            break;
        }
        else
        {
            cout<<"Invalid type, please enter (1,2 or 3): \n";
            cin>>isr;
        }

        }
    }


}














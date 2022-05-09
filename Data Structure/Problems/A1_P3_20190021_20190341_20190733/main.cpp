#include <iostream>


using namespace std;

template <class T>
class Matrix
{
    int numRows,numCols;
    T** data;

    public:
        Matrix()
        {
            numCols=0;
            numRows=0;
        }
       Matrix(int numRows,int numCols)
        {
            this->numRows=numRows;
            this->numCols=numCols;
            data = new T*[this->numRows];
            for(int i=0;i<this->numRows;i++)
            {
                data[i]= new T [this->numCols];
            }
        }


        Matrix operator+(Matrix mat)
        {


            Matrix sum(numRows,numCols);
            for(int i=0;i<numRows;i++)
            {
                for(int j=0;j<numCols;j++)
                {
                    sum.data[i][j]=data[i][j]+mat.data[i][j];
                }
            }
            return sum;

        }

        Matrix operator- (Matrix mat)
        {


            Matrix diff(numRows,numCols);
            for(int i=0;i<numRows;i++)
            {
                for(int j=0;j<numCols;j++)
                {
                    diff.data[i][j]=data[i][j]-mat.data[i][j];
                }
            }
            return diff;

        }

        Matrix operator* (Matrix mat)
        {
            Matrix mult(numRows,mat.numCols);
            for(int i=0;i<numRows;i++)
            {
                for(int j=0;j<mat.numCols;j++)
                {
                    mult.data[i][j]=0;
                    for(int k=0;k<numRows;k++)
                    {
                        mult.data[i][j]+=data[i][k]*mat.data[k][j];
                    }
                }
            }
            return mult;

        }
        friend ostream& operator <<(ostream& os, const Matrix& mat)
        {
            for(int i=0;i<mat.numRows;i++)
            {
                for(int j=0;j<mat.numCols;j++)
                {
                    os<<mat.data[i][j]<<" ";
                }
                os<<endl;
            }
            return os;
        }

         friend istream& operator >>(istream& is, Matrix& mat)
        {
            for(int i=0;i<mat.numRows;i++)
            {
                for(int j=0;j<mat.numCols;j++)
                {
                    is>>mat.data[i][j];
                }
            }
            return is;
        }


        void Transpose ()
        {
            int temp0 = this->numCols;
            this->numCols=this->numRows;
            this->numRows=temp0;

            for(int i=0;i<this->numRows;i++)
            {
                for(int j=i;j<this->numCols;j++)
                {
                    T temp=this->data[j][i];
                    this->data[j][i] = this->data[i][j];
                    this->data[i][j]=temp;

                }
            }

        }
        virtual ~Matrix()
        {
            delete data;
        }
};



int main()
{
cout<<"Welcome to Ahmed Matrix Calculator \n";
cout<<"--------------------------------\n";
cout<<"1-Perform Matrix Addition\n";
cout<<"2-Perform Matrix Subtraction\n";
cout<<"3-Perform Matrix Multiplication\n";
cout<<"4-Matrix Transpose\n";
int choice0;
cin>>choice0;
int r1,c1,r2,c2;
if(choice0==1)
{
    cout<<"Enter dimensions of two matrices: \n";
    cin>>r1>>c1;
   Matrix<double>mat1(r1,c1);
    Matrix<double>mat2(r1,c1);
   cout<<"Enter Matrix1: \n";
   cin>>mat1;

   cout<<"Enter Matrix2: \n";
   cin>>mat2;
   cout<<"RESULT: \n"<<mat1+mat2;
}
else if (choice0==2)
{
    cout<<"Enter dimensions of two matrices: \n";
    cin>>r1>>c1;
   Matrix<float>mat1(r1,c1);
   Matrix<float>mat2(r1,c1);
   cout<<"Enter Matrix: \n";
   cin>>mat1;

   cout<<"Enter Matrix: \n";
   cin>>mat2;
   cout<<"RESULT: \n"<<mat1-mat2;

}
else if (choice0==3)
{
    cout<<"Matrix1 ,,Enter number of rows , columns: \n";
    cin>>r1>>c1;
   Matrix<int>mat1(r1,c1);
   cout<<"Enter Matrix: \n";
   cin>>mat1;
   cout<<"Matrix2 ,,Enter number of rows , columns: \n";
   cin>>r2>>c2;
   if(c1==r2)
   {
       Matrix<int>mat2(r2,c2);
        cout<<"Enter Matrix: \n";
        cin>>mat2;
       cout<<"RESULT: \n"<<mat1*mat2;
   }
   else
   {
       cout<<"Dimensions Error ";
   }



}
else if(choice0==4)
{
    cout<<"Enter number of rows,cols: \n";
    cin>>r1>>c1;
    Matrix<int>m1(r1,c1);
    cout<<"Enter Matrix: \n";
    cin>>m1;
    cout<<"TRANSPOSE: \n";
    m1.Transpose();
    cout<<m1;
}


























}


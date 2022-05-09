#include "Matrix.h"
template <class T>
Matrix::Matrix()
{
    numCols=0;
    numRows=0;
}

Matrix::Matrix(int numRows,int numCols)
{
    this->numRows=numRows;
    this->numCols=numCols;
    data = new T*[this->numRows];
    for(int i=0;i<this->numRows;i++)
    {
        data[i]= new T [this->numCols];
    }
}

Matrix Matrix::operator+(Matrix mat)
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

Matrix::operator- (Matrix mat)
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

Matrix Matrix::operator* (Matrix mat)
{
    Matrix mult(numRows,mat.numCols);
    for(int i=0;i<numRows;i++)
    {
        for(int j=0;j<mat.numCols;j++)
        {
            mult.data[i][j]=0;
            for(int k=0;k<numRows;k++)
            {
                mult.data[i][j]+=data[i][k]*data[k][j];
            }
        }
    }
    return mult;

}

friend ostream& Matrix::operator <<(ostream& os, const Matrix& mat)
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

friend istream& Matrix:: operator >>(istream& is, Matrix& mat)
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
void Matrix::Transpose()
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
Matrix::~Matrix()
{
    delete data;
}

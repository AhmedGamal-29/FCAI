#ifndef MATRIX_H
#define MATRIX_H

template <class T>
class Matrix
{

    int numRows,numCols;
    T** data;

    public:
        Matrix();
        Matrix(int,int);
        Matrix operator+(Matrix);
        Matrix operator-(Matrix);
        Matrix operator*(Matrix);
        //friend ostream& operator <<(ostream& os , const Matrix& mat);
//        friend istream& operator >>(istream& is, Matrix& mat);
        void Transpose();
        virtual ~Matrix();
};

#endif // MATRIX_H

#include "iostream"
#include "algorithm"
#include "vector"
using namespace std;

class BigDecimalInt{
   vector<int> bigIntList;
public:
   BigDecimalInt(const BigDecimalInt& decInt):bigIntList(decInt.bigIntList){}
   BigDecimalInt(string num="0");
   BigDecimalInt operator+(const BigDecimalInt& anotherDec) const{
    return BigDecimalInt(*this) += anotherDec;

}
   BigDecimalInt& operator+=(const BigDecimalInt& anotherDec){
   unsigned int maximumSize;
   unsigned int num = 0;
   unsigned int remain = 0;

   if(bigIntList.size()<anotherDec.bigIntList.size()){
	   maximumSize = anotherDec.bigIntList.size();
	   bigIntList.resize(anotherDec.bigIntList.size());
   }
   else{
	   maximumSize = bigIntList.size();
   }

   for(unsigned int i = 0; i < maximumSize; i++)
   {
	   if(i<anotherDec.bigIntList.size())
          num=bigIntList[i]+anotherDec.bigIntList[i]+remain;
	   else
          num=bigIntList[i]+remain;

      if(num >= 10)
      {
          num = num - 10;
          remain = 1;
      }
      else{
         remain = 0;
      }

	  bigIntList[i] = num;
   }
   if (remain){
	   bigIntList.push_back(1);
   }

   return *this;
}
   BigDecimalInt& operator=(BigDecimalInt anotherDec){
	bigIntList.swap(anotherDec.bigIntList);
    return *this;
}
   friend ostream& operator<<(ostream& out,BigDecimalInt b);


};
struct StringToInt { int operator()(int x) {return x-'0';} };


BigDecimalInt::BigDecimalInt(string num){
   transform(num.rbegin(), num.rend(), back_inserter(bigIntList),StringToInt());
}


ostream& operator <<(ostream& os,BigDecimalInt b)
{
   for(unsigned int i=0; i < b.bigIntList.size(); i++){
      os << b.bigIntList[b.bigIntList.size()-1-i];
   }
   return os;
}

int main(){
   BigDecimalInt B1("123456789012345678901234567890");
   BigDecimalInt B2("113456789011345678901134567890");
   BigDecimalInt B3;
   B3 = B1+B2;
   cout << "B1+B2 is " << B3 << endl;
   cout<<"--------------------------------------------------"<<endl;

   BigDecimalInt B4("45568989533489884655659889");
   BigDecimalInt B5("45458686865655646889553547");
   BigDecimalInt B6;
   B6=B4+B5;
   cout<<"B4+B5 is "<< B6 <<endl;
   cout<<"--------------------------------------------------"<<endl;

   BigDecimalInt B7("15464815615484846515645646");
   BigDecimalInt B8("16551321488486131534656541");
   BigDecimalInt B9;
   B9=B7+B8;
   cout<<"B7+B8 is "<< B9 <<endl;
   cout<<"--------------------------------------------------"<<endl;

   return 0;
}




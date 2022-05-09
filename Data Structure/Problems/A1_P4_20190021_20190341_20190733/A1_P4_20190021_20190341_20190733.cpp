#include <iostream>
using namespace std;
// First Function
int power(int a, int n)
{
// As any number raised to the power 0 = 1
	if (n == 0)
		return 1;
	else
		return (a * power(a, n - 1));
}
// Second Function
int power_(int base, int Power)
{
// As any number raised to the power 0 = 1
	if (Power == 0)
	{
		return 1;
	}
	int result = power_(base, Power / 2);
// In Case of an Odd Power
	if (Power % 2 != 0)
	{
		return base * result * result;
	}
// In Case of an Even Power
	else
		return result * result;
}
int main()
{
	// Test
	cout << "The Result = " << power(2, 3) << endl;  // First Function: Output = 8
	cout << "The Result = " << power_(2, 3) << endl; // Second Function: Output = 8
	cout << "The Result = " << power(3, 2) << endl;  // First Function: Output = 9
	cout << "The Result = " << power_(3, 2) << endl; // Second Function: Output = 8
	cout << "The Result = " << power(2, 4) << endl;  // First Function: Output = 16
	cout << "The Result = " << power_(2, 4) << endl; // Second Function: Output = 16
	return 0;
}
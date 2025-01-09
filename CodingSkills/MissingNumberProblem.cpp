#include <iostream>
#include <algorithm>

using namespace std;

const int nmax = 1e6;

int arr[nmax + 4];
int n;

int main()
{
    cin >> n;
    for(int i = 0; i < n; ++i)
        cin >> arr[i];
    sort(arr, arr + n);

    for(int i = 0; i < n; ++i){
        if(arr[i] != i + 1){
            cout << i + 1;
            return 0;
        }
    }
    cout << n + 1;
    return 0;
}
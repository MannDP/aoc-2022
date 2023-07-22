#include <iostream>
#include <algorithm>
using namespace std;

int main(const int argc, const char* const argv[]) {
    int count = 0;
    string line;
    int first1, end1, first2, end2;
    char muck;
    while(cin >> first1) {
        cin >> muck >> end1 >> muck >> first2 >> muck >> end2;

        if (first1 > first2) {
            swap(first1, first2);
            swap(end1, end2);
        }

        if (first2 <= end1) {
            count += 1;
        }
    }

    cout << count << endl;
}

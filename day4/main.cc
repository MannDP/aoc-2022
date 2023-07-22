#include <iostream>
using namespace std;

int main(const int argc, const char* const argv[]) {
    int count = 0;
    string line;
    int first1, end1, first2, end2;
    char muck;
    while(cin >> first1) {
        cin >> muck;
        cin >> end1;
        cin >> muck;
        cin >> first2;
        cin >> muck;
        cin >> end2;

        if (first1 <= first2 && end1 >= end2) {
            count += 1;
        } else if (first2 <= first1 && end2 >= end1) {
            count += 1;
        }
    }

    cout << count << endl;
}

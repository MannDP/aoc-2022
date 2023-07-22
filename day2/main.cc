#include <iostream>
#include <unordered_map>
using namespace std;

int main(const int argc, const char* const argv[]) {
    unordered_map<char, int> movePoints {
        {'X', 1}, // rock
        {'Y', 2}, // paper
        {'Z', 3} // scissors
    };

    int total = 0;
    char o, m;
    while(cin >> o) {
        cin >> m;
        total += movePoints.at(m);

        if (o - 'A' == m - 'X') {
            // draw
            total += 3;
        } else if (
            (o == 'A' && m == 'Y') ||
            (o == 'B' && m == 'Z') ||
            (o == 'C' && m == 'X')
        ) {
            total += 6;
        }
    }

    cout << total << endl;
}

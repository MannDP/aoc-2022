#include <iostream>
#include <algorithm>
using namespace std;

void top3(int& count, int& first, int& second, int& third) {
    // do the adjustment
    if (count >= first) {
        swap(count, first);
    }

    if (count >= second) {
        swap(count, second);
    }

    if (count >= third) {
        swap(count, third);
    }
}

int main(const int argc, const char* const argv[]) {
    int count = 0, first = 0, second = 0, third = 0;
    string line;
    while(getline(cin, line)) {
        if (line.empty()) {
            top3(count, first, second, third);
            count = 0;
            continue;
        }
        count += stoi(line);
    }

    top3(count, first, second, third);

    cout << first + second + third << endl;
}

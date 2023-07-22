#include <iostream>
#include <unordered_set>
using namespace std;

int main(const int argc, const char* const argv[]) {
    int sum = 0;
    string line;
    while(getline(cin, line)) {
        unordered_set<char> set{line.begin(), line.begin() + line.size() / 2};

        for (size_t i = line.size() / 2; i < line.size(); ++i) {
            char common = line[i];
            if (set.find(common) != set.end()) {
                if (isupper(common)) {
                    sum += common - 'A' + 26;
                } else {
                    sum += common - 'a';
                }
                sum += 1;
                break;
            }
        }
        // add the points of the common letter
    }
    
    cout << sum << endl;
}

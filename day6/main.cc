#include <iostream>
#include <unordered_set>
using namespace std;

int solve(const string& input, const int distinct) {
    unordered_map<char, int> seen;
    for (size_t i = 0; i <= input.size() - distinct; i++) {
        char c = input[i];
        seen[c] += 1;
        if (i > distinct - 1) {
            c = input[i - distinct];
            if (--seen[c] == 0) {
                seen.erase(c);
            }
        }

        if (seen.size() == distinct) {
            return i + 1;
        }
    }

    return input.size() - distinct + 1;
}

int main() {
    string input;

    getline(cin, input);
    cout << solve(input, 4) << endl;
    cout << solve(input, 14) << endl;
}

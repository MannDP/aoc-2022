#include <iostream>
#include <unordered_set>
using namespace std;

unordered_set<char> intersection(const unordered_set<char>& a, const unordered_set<char>& b) {
    unordered_set<char> result;
    for (const auto& c: a) {
        if (b.find(c) != b.end()) {
            result.insert(c);
        }
    }

    for (const auto& c : b) {
        if (a.find(c) != a.end()) {
            result.insert(c);
        }
    }
    return result;
}

int main(const int argc, const char* const argv[]) {
    int sum = 0;
    string line1, line2, line3;
    while(getline(cin, line1) && getline(cin, line2) && getline(cin, line3)) {
        unordered_set<char> set1{line1.begin(), line1.end()};
        unordered_set<char> set2{line2.begin(), line2.end()};
        unordered_set<char> set3{line3.begin(), line3.end()};
        unordered_set<char> set = intersection(set1, intersection(set2, set3));
        assert(set.size() == 1);

        for (const auto& c : set) {
            if (isupper(c)) {
                sum += c - 'A' + 26;
            } else {
                sum += c - 'a';
            }
            sum += 1;
            break;
        }
    }
    
    cout << sum << endl;
}

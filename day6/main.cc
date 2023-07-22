#include <iostream>
#include <unordered_set>
using namespace std;

int main() {
    const int lenPrefix = 14;
    unordered_set<char> characters;
    string input;
    getline(cin, input);
    
    for (size_t i = 0; i <= input.size() - lenPrefix; i++) {
        for (size_t j = 0; j < lenPrefix; j++) {
            characters.insert(input[i + j]);
        }

        if (characters.size() == lenPrefix) {
            cout << i + lenPrefix << endl;
            break;
        }
        characters.clear();
    }
}

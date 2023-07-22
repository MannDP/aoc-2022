#include <iostream>
#include <unordered_set>
using namespace std;

int main() {
    unordered_set<char> characters;
    string input;
    getline(cin, input);
    
    for (size_t i = 0; i <= input.size() - 4; i++) {
        for (size_t j = 0; j < 4; j++) {
            characters.insert(input[i + j]);
        }

        if (characters.size() == 4) {
            cout << i + 4 << endl;
            break;
        }
        characters.clear();
    }
}

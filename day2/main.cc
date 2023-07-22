#include <iostream>
#include <unordered_map>
using namespace std;

// rock, A = 1
// paper, B = 2
// scissors, C = 3

int main(const int argc, const char* const argv[]) {
    unordered_map<char, int> outcomePoints {
        {'X', 0}, // lose
        {'Y', 3}, // draw
        {'Z', 6}, // win
    };

    unordered_map<char, unordered_map<char, char>> moveMap {
        {'A', {{'X', 'C'}, {'Y', 'A'}, {'Z', 'B'}}},
        {'B', {{'X', 'A'}, {'Y', 'B'}, {'Z', 'C'}}},
        {'C', {{'X', 'B'}, {'Y', 'C'}, {'Z', 'A'}}},
    };

    unordered_map<char, int> movePoints {
        {'A', 1}, {'B', 2}, {'C', 3}
    };

    int total = 0;
    char o, m;
    while(cin >> o) {
        cin >> m;
        total += outcomePoints.at(m);

        // determine move based on opponent and outcome
        char move = moveMap.at(o).at(m);
        total += movePoints.at(move);
    }

    cout << total << endl;
}

#include "../competitive.hpp"
using namespace std;

struct PairHash {
    template<class T1, class T2>
    size_t operator () (pair<T1, T2> const& p) const {
        size_t h1 = hash<T1>()(p.first);
        size_t h2 = hash<T2>()(p.second);
        return h1 ^ h2;
    }
};

// helpers
void moveTail(const pair<int, int>& head, pair<int, int>& tail, unordered_set<pair<int, int>, PairHash>& visited) {
    int dx = head.first - tail.first;
    int dy = head.second - tail.second;
    bool isTouch = (abs(dx) + abs(dy) <= 1) || (abs(dx) == 1 && abs(dy) == 1);
    if (isTouch) return;

    if (dx == 0) dy > 0 ? tail.second += 1 : tail.second -= 1;
    else if (dy == 0) dx > 0 ? tail.first += 1 : tail.first -= 1;
    else {
        (dx > 0) ? tail.first += 1 : tail.first -= 1;
        (dy > 0) ? tail.second += 1 : tail.second -= 1;
    }
    visited.insert(tail);
}

// rope following algorithm
int main(const int argc, const char* const argv[]) {
    pair<int, int> head = {0, 0};
    pair<int, int> tail = {0, 0};
    unordered_set<pair<int, int>, PairHash> visited;
    visited.insert(tail);
    
    string cmd;
    char direction;
    int delta;
    while(getline(cin, cmd)) {
        istringstream iss{cmd};
        iss >> direction;
        iss >> delta;
        pair<int, int> deltas{0, 0};

        if (direction == 'U') deltas.second = 1;
        else if (direction == 'D') deltas.second = -1;
        else if (direction == 'L') deltas.first = -1;
        else if (direction == 'R') deltas.first = 1;
        
        for (int i = 0; i < delta; i++) {
            // move head
            head.first += deltas.first;
            head.second += deltas.second;

            moveTail(head, tail, visited);
        }
    }
    cout << visited.size() << endl;
}

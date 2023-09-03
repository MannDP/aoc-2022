#include "../competitive.hpp"

typedef pair<int, int> location;

location& operator+=(location&a, const location& b) {
    a.first += b.first;
    a.second += b.second;
    return a;
}

// helpers
void moveTail(const location& head, location& tail, bool mark, unordered_set<location, PairHash>& visited) {
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

    if (mark) visited.insert(tail);
}

// rope following algorithm
int main(const int argc, const char* const argv[]) {
    const int ROPES = 10;
    location nodes[ROPES];
    unordered_set<location, PairHash> visited;
    visited.insert(nodes[ROPES - 1]);
    
    char direction;
    int delta;
    while(cin >> direction) {
        cin >> delta;
        location deltas{0, 0};

        if (direction == 'U') deltas.second = 1;
        else if (direction == 'D') deltas.second = -1;
        else if (direction == 'L') deltas.first = -1;
        else if (direction == 'R') deltas.first = 1;
        
        for (int i = 0; i < delta; i++) {
            // move head
            nodes[0] += deltas;
            // have each tail follow its head
            for (int j = 1; j < ROPES; j++) {
                moveTail(nodes[j-1], nodes[j], j == delta - 1, visited);
            }
        }
    }
    cout << visited.size() << endl;
}
